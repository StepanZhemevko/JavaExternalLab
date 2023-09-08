package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TagDaoImplTest {
    // @Mock
    // private JdbcTemplate jdbcTemplate;

    // @InjectMocks
    // private TagDaoImpl tagDao;


    // @BeforeEach
    // public void setup() {
    //     MockitoAnnotations.initMocks(this);
    // }

    // @Test
    // public void testCreate() {
    //     Tag tag = new Tag(null, "Test Tag");
    //     KeyHolder keyHolder = new GeneratedKeyHolder();

    //     doAnswer(invocation -> {
    //         PreparedStatement statement = invocation.getArgument(0);
    //         statement.getConnection();
    //         statement.setString(1, tag.getName());

    //         Objects.requireNonNull(keyHolder.getKey()).longValue();

    //         tag.setId(keyHolder.getKey().longValue());
    //         return 1;
    //     }).when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

    //     when(jdbcTemplate.update(anyString(), any(Object[].class), any(KeyHolder.class))).thenReturn(1);
    //     when(keyHolder.getKey()).thenReturn(1L);


    //     Tag createdTag = tagDao.create(tag);

    //     assertEquals(1L, createdTag.getId());
    //     assertEquals("Test Tag", createdTag.getName());
    //     verify(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
    //     verify(jdbcTemplate, never()).update(anyString(), any(Object[].class), any(KeyHolder.class));
    // }

//    @Test
//    void getAllTest() {
//        int expectedSize = 0;
//        List<Tag> tags = tagDao.findAll();
//        assertEquals(expectedSize, tags.size());
//    }
//
//    @Test
//    void deleteTagTest() {
//        Long id = tagDao.create(new Tag(1l, "Delete this tag")).getId();
//        tagDao.delete(id);
//        int expectedSize = 0;
//        List<Tag> tags = tagDao.findAll();
//        assertEquals(expectedSize, tags.size());
//    }

}
