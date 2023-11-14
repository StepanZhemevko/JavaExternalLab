package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;

import com.epam.esm.entity.UserOrder;
import com.epam.esm.mapper.TagRowMapper;
import com.epam.esm.mapper.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateDaoImpl giftCertificateDaoImpl;

    public UserDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateDaoImpl giftCertificateDaoImpl) {
        this.jdbcTemplate = jdbcTemplate;
        this.giftCertificateDaoImpl = giftCertificateDaoImpl;
    }


    @Override
    public List<User> findAll(int offset, int limit) {
        String query = "SELECT * FROM user LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new Object[]{limit, offset}, new UserRowMapper(jdbcTemplate));

    }

    @Override
    public User findById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQLQuery.GET_USERS_BY_ID, new Object[]{id}, new UserRowMapper(jdbcTemplate));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    @Override
    public User findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject(SQLQuery.GET_USERS_BY_LOGIN, new Object[]{login}, new UserRowMapper(jdbcTemplate));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void buy(User user, GiftCertificate giftCertificate) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setGiftCertificate(giftCertificate);
        userOrder.setPrice(giftCertificate.getPrice());
        userOrder.setPurchaseTimestamp(LocalDateTime.now());

        jdbcTemplate.update(
                "INSERT INTO user_order (user_id, gift_certificate_id, cost, purchase_timestamp) VALUES (?, ?, ?, ?)",
                userOrder.getUser().getId(),
                userOrder.getGiftCertificate().getId(),
                userOrder.getPrice(),
                userOrder.getPurchaseTimestamp()
        );
        jdbcTemplate.update(
                SQLQuery.BUY_CERTIFICATE,
                userOrder.getUser().getId(),
                userOrder.getGiftCertificate().getId()
        );
    }

    @Override
    public List<UserOrder> getUserOrders(Long userId) {
        String sql = "SELECT id, user_id, gift_certificate_id, cost, purchase_timestamp FROM user_order WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, userOrderRowMapper);
    }



    @Override
    public Tag getMostUsedTagWithHighestCost(Long userId) {
        String query = "SELECT t.* FROM tag t " +
                "JOIN gift_certificate_has_tag gct ON gct.tag_id = t.id " +
                "JOIN gift_certificate gc ON gc.id = gct.gift_certificate_id " +
                "JOIN user_has_gift_certificate ugc ON ugc.gift_certificate_id = gc.id " +
                "JOIN user_order uo ON uo.user_id = ugc.user_id " +
                "WHERE uo.user_id = ? " +
                "GROUP BY t.id " +
                "ORDER BY SUM(uo.cost) DESC, COUNT(DISTINCT ugc.user_id) DESC " +
                "LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{userId}, new TagRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private final RowMapper<UserOrder> userOrderRowMapper = (rs, rowNum) -> {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(rs.getLong("id"));
        userOrder.setUser(findById(rs.getInt("user_id")));
        userOrder.setPrice(rs.getDouble("cost"));
        userOrder.setPurchaseTimestamp(rs.getTimestamp("purchase_timestamp").toLocalDateTime());
        return userOrder;
    };
    @Override
    public User create(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    SQLQuery.INSERT_USER,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getEmail());
            return statement;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
    }

}

