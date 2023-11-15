package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public GiftCertificate findByName(String name) {
        return giftCertificateDao.findByName(name);
    }

    @Override
    public List<GiftCertificate> findCertificateByUserLogin(String userLogin) {
        return giftCertificateDao.findCertificateByUserLogin(userLogin);
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        return giftCertificateDao.create(giftCertificate);
    }

    @Override
    public GiftCertificate findById(long id) {
        return giftCertificateDao.findById(id);
    }

    @Override
    public List<GiftCertificate> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return giftCertificateDao.findAll(offset, size);
    }
    @Override
    public List<GiftCertificate> findAllGifts() {
        return giftCertificateDao.findAllGifts();
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        GiftCertificate existingGiftCertificate = giftCertificateDao.findById(giftCertificate.getId());
        if (existingGiftCertificate == null) {
            throw new RuntimeException();
        }
        existingGiftCertificate.setName(giftCertificate.getName());
        existingGiftCertificate.setDescription(giftCertificate.getDescription());
        existingGiftCertificate.setPrice(giftCertificate.getPrice());
        existingGiftCertificate.setDuration(giftCertificate.getDuration());
        existingGiftCertificate.setLastUpdateDate(String.valueOf(LocalDateTime.now()));
        existingGiftCertificate.setTags(giftCertificate.getTags());
        for (Tag tag : existingGiftCertificate.getTags()) {
            if (tag.getId() == null) {
                tagDao.create(tag);
            }
        }
        return giftCertificateDao.update(existingGiftCertificate);
    }
    @Override
    public void updateGiftCertificatePrice(int certificateId, double price) {
        giftCertificateDao.updateGiftCertificatePrice(certificateId, price);
    }
    @Override
    public boolean delete(long id) {
        giftCertificateDao.delete(id);
        return true;
    }

    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        return giftCertificateDao.findByTagName(tagName);
    }

}
