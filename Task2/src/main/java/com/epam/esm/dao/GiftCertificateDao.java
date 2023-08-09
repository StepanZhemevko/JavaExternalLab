package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftCertificateDao {
    GiftCertificate create(GiftCertificate giftCertificate);

    List<GiftCertificate> findAll(int offset, int limit);

    GiftCertificate findById(long id);
    void updateGiftCertificatePrice(int certificateId, double price);

    boolean delete(long id);

    List<GiftCertificate> findByTagName(String tagName);

    GiftCertificate update(GiftCertificate giftCertificate);
}
