package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {
    Tag create(Tag tag);

    List<Tag> findAll(int offset, int limit);

    Tag findById(long id);

    void delete(long id);

    List<Tag> findByName(String name);

    void deleteTagAssociation(long tagId);
}
