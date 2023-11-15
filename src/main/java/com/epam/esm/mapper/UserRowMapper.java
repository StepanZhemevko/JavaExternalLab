package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRowMapper implements RowMapper<User> {
    private final JdbcTemplate jdbcTemplate;

    public UserRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));



        Long userId = user.getId();
        Set<GiftCertificate> giftCertificates = getGiftCertificatesByUserId(userId);
        user.setGiftCertificates(giftCertificates);


        return user;
    }
    private Set<GiftCertificate> getGiftCertificatesByUserId(Long userId) {
        String sql = "SELECT gc.id AS gift_certificate_id, gc.name AS gift_certificate_name, gc.description AS gift_certificate_description, " +
                "gc.price AS gift_certificate_price, gc.duration AS gift_certificate_duration, " +
                "gc.create_date AS gift_certificate_create_date, gc.last_update_date AS gift_certificate_last_update_date " +
                "FROM third_modul.gift_certificate gc " +
                "JOIN third_modul.user_has_gift_certificate ugc ON gc.id = ugc.gift_certificate_id " +
                "JOIN third_modul.user u ON ugc.user_id = u.id " +
                "WHERE u.id = ?";

        ResultSetExtractor<Set<GiftCertificate>> resultSetExtractor = rs -> {
            Set<GiftCertificate> giftCertificates = new HashSet<>();
            while (rs.next()) {
                GiftCertificate giftCertificate = new GiftCertificate();
                giftCertificate.setId(rs.getLong("gift_certificate_id"));
                giftCertificate.setName(rs.getString("gift_certificate_name"));
                giftCertificate.setDescription(rs.getString("gift_certificate_description"));
                giftCertificate.setPrice(rs.getDouble("gift_certificate_price"));
                giftCertificate.setDuration(rs.getString("gift_certificate_duration"));
                giftCertificate.setCreateDate(rs.getString("gift_certificate_create_date"));
                giftCertificate.setLastUpdateDate(rs.getString("gift_certificate_last_update_date"));
                String query = "SELECT t.id, t.name FROM tag t " +
                        "JOIN gift_certificate_has_tag gct ON gct.tag_id = t.id " +
                        "WHERE gct.gift_certificate_id = ?";
                List<Tag> tags = jdbcTemplate.query(query, new Object[]{giftCertificate.getId()}, new TagRowMapper());
                giftCertificate.setTags(new HashSet<>(tags));
                giftCertificates.add(giftCertificate);
            }
            return giftCertificates;
        };

        return jdbcTemplate.query(sql, resultSetExtractor, userId);
    }
}
