package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificate")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificate(@PathVariable("id") long id) {
        return giftCertificateService.findById(id);
    }

    @GetMapping("/all/{page}")
    public List<GiftCertificate> getAllCertificates(@PathVariable("page") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return giftCertificateService.findAll(page, size);
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        GiftCertificate createdGiftCertificate = giftCertificateService.create(giftCertificate);

        return createdGiftCertificate;
    }
    @PutMapping("/{certificateId}/price")
    public void updateGiftCertificatePrice(@PathVariable("certificateId") int certificateId,
                                           @RequestParam("price") double price) {
        giftCertificateService.updateGiftCertificatePrice(certificateId, price);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable Long id) {
        boolean isRemoved = giftCertificateService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public GiftCertificate updateGiftCertificate(@PathVariable("id") long id, @RequestBody GiftCertificate giftCertificate) {
        GiftCertificate existingGiftCertificate = giftCertificateService.findById(id);
        if (existingGiftCertificate != null) {
            giftCertificateService.update(giftCertificate);
        }
        return giftCertificate;
    }
}
