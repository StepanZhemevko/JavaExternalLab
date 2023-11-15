package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftCertificateDao {
    List<GiftCertificate> findCertificateByUserLogin(String userLogin);

    GiftCertificate create(GiftCertificate giftCertificate);

    List<GiftCertificate> findAll(int offset, int limit);
    List<GiftCertificate> findAllGifts();

    GiftCertificate findById(long id);
    GiftCertificate findByName(String name);
    void updateGiftCertificatePrice(int certificateId, double price);

    boolean delete(long id);

    List<GiftCertificate> findByTagName(String tagName);

    GiftCertificate update(GiftCertificate giftCertificate);
}
