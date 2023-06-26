package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate create(GiftCertificate giftCertificate);

    GiftCertificate findById(long id);

    List<GiftCertificate> findAll(int page, int size);

    GiftCertificate update(GiftCertificate giftCertificate);
    void updateGiftCertificatePrice(int certificateId, double price);

    boolean delete(long id);

    List<GiftCertificate> findByTagName(String tagName);

}
