package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate findByName(String name);

    List<GiftCertificate> findCertificateByUserLogin(String userLogin);
    GiftCertificate create(GiftCertificate giftCertificate);

    GiftCertificate findById(long id);

    List<GiftCertificate> findAll(int page, int size);

    List<GiftCertificate> findAllGifts();
    GiftCertificate update(GiftCertificate giftCertificate);
    void updateGiftCertificatePrice(int certificateId, double price);

    boolean delete(long id);

    List<GiftCertificate> findByTagName(String tagName);

}
