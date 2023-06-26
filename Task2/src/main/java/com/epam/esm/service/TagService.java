package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;


public interface TagService {
    List<Tag> findAll(int page, int size);

    Tag findById(long id);

    List<Tag> findByName(String name);

    Tag create(Tag tag);

    void delete(Long id);


}
