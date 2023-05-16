package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(resultSet.getLong("id"));
        giftCertificate.setName(resultSet.getString("name"));
        giftCertificate.setDescription(resultSet.getString("description"));
        giftCertificate.setPrice(resultSet.getDouble("price"));
        giftCertificate.setDuration(resultSet.getString("duration"));
        giftCertificate.setCreateDate((resultSet.getString("create_date")));
        giftCertificate.setLastUpdateDate((resultSet.getString("last_update_date")));
        String query = "SELECT t.id, t.name FROM tag t " +
                "JOIN gift_certificate_has_tag gct ON gct.tag_id = t.id " +
                "WHERE gct.gift_certificate_id = ?";
        List<Tag> tags = jdbcTemplate.query(query, new Object[]{giftCertificate.getId()}, new TagRowMapper());
        giftCertificate.setTags(new HashSet<>(tags));

        return giftCertificate;
    }
}