package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag create(Tag tag) {
        return tagDao.create(tag);
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public Tag findById(long id) {
        return tagDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagDao.deleteTagAssociation(id);
        tagDao.delete(id);
    }

    @Override
    public List<Tag> findByName(String name) {
        return tagDao.findByName(name);
    }


}
