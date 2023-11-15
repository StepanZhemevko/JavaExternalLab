package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
public class TagDaoImpl implements TagDao {
    private final JdbcTemplate jdbcTemplate;

    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    SQLQuery.INSERT_TAG,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, tag.getName());
            return statement;
        }, keyHolder);

        tag.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return tag;
    }

    @Override
    public Tag findById(long id) {
        String sql = SQLQuery.GET_TAGS_BY_ID;
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Tag(rs.getLong("id"), rs.getString("name"))
        );
    }

    @Override
    public List<Tag> findAll(int offset, int limit) {
        String sql = SQLQuery.GET_ALL_TAGS;
        return jdbcTemplate.query(sql, new Object[]{limit, offset}, (rs, rowNum) ->
                new Tag(rs.getLong("id"), rs.getString("name"))
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(SQLQuery.DELETE_TAG, id);

    }

    @Override
    public List<Tag> findByName(String name) {
        String query = SQLQuery.FIND_BY_NAME;
        return jdbcTemplate.query(query, new Object[]{name}, (rs, rowNum) -> {
            Tag tag = new Tag();
            tag.setId(rs.getLong("id"));
            tag.setName(rs.getString("name"));
            return tag;
        });
    }

    @Override
    public void deleteTagAssociation(long tagId) {
        jdbcTemplate.update(SQLQuery.DELETE_TAG_ASSOCIATION, tagId);
    }

}
