package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.GiftCertificateRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private final TagDao tagDao;

    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, TagDao tagDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagDao = tagDao;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        for (Tag tag : giftCertificate.getTags()) {
            List<Tag> existingTags = tagDao.findByName(tag.getName());

            if (existingTags.isEmpty()) {
                KeyHolder tagKeyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(
                        connection -> {
                            PreparedStatement statement = connection.prepareStatement(
                                    SQLQuery.INSERT_TAG,
                                    Statement.RETURN_GENERATED_KEYS
                            );
                            statement.setString(1, tag.getName());
                            return statement;
                        },
                        tagKeyHolder
                );
                tag.setId(Objects.requireNonNull(tagKeyHolder.getKey()).longValue());
            } else {
                tag.setId(existingTags.get(0).getId());
            }
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(
                            SQLQuery.INSERT,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    statement.setString(1, giftCertificate.getName());
                    statement.setString(2, giftCertificate.getDescription());
                    statement.setDouble(3, giftCertificate.getPrice());
                    statement.setString(4, giftCertificate.getDuration());
                    statement.setString(5, giftCertificate.getCreateDate());
                    statement.setString(6, giftCertificate.getLastUpdateDate());
                    return statement;
                },
                keyHolder
        );

        giftCertificate.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        for (Tag tag : giftCertificate.getTags()) {
            jdbcTemplate.update(
                    SQLQuery.INSERT_GIFT_HAS_TAG,
                    giftCertificate.getId(),
                    tag.getId()
            );
        }

        return giftCertificate;
    }


    @Override
    public List<GiftCertificate> findAll() {
        List<GiftCertificate> certificates = jdbcTemplate.query(SQLQuery.GET_ALL, new GiftCertificateRowMapper(jdbcTemplate));
        return certificates;
    }

    @Override
    public GiftCertificate findById(long id) {
        try {
            return jdbcTemplate.queryForObject(SQLQuery.GET_CERTIFICATE_BY_ID, new Object[]{id}, new GiftCertificateRowMapper(jdbcTemplate));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = SQLQuery.DELETE_GIFT_CERTIFICATE;
        jdbcTemplate.update(sql, id);
        return true;
    }

    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        String sql = SQLQuery.FIND_BY_TAG_NAME;

        List<GiftCertificate> certificates = jdbcTemplate.query(sql, new Object[]{tagName}, new GiftCertificateRowMapper(jdbcTemplate));
        return certificates;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        String sql = SQLQuery.UPDATE_GIFT_CERTIFICATE;
        jdbcTemplate.update(sql,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getLastUpdateDate(),
                giftCertificate.getId());

        return findById(giftCertificate.getId());
    }
}

