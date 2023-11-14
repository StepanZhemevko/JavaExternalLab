package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin

public class UserController {
    private final UserService userService;
    private final GiftCertificateService giftCertificateService;


    @Autowired
    public UserController(UserService userService, GiftCertificateService giftCertificateService) {
        this.userService = userService;
        this.giftCertificateService = giftCertificateService;

    }

    @GetMapping("/all/{page}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(@PathVariable("page") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getUserById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/{userId}/orders")
    public List<UserOrder> getUserOrders(@PathVariable("userId") Long userId) {
        return userService.getUserOrders(userId);
    }


    @GetMapping("/login/{login}")
    public User getUserByLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }

    @PostMapping("/buy/{user_id}/{certificate_id}")
    public void BuyCertificate(@PathVariable("user_id") Long UserId, @PathVariable("certificate_id") Long CertificateId) {
        User user = userService.findById(UserId.intValue());
        GiftCertificate giftCertificate = giftCertificateService.findById(CertificateId);

        if (user != null && giftCertificate != null) {
            userService.buy(user, giftCertificate);
        } else {
            throw new IllegalArgumentException("User or GiftCertificate not found");
        }
    }

    @PostMapping("/buy_by_name/{user_login}/{certificate_name}")
    public void BuyCertificate(@PathVariable("user_login") String userLogin, @PathVariable("certificate_name") String certificateName) {
        User user = userService.findByLogin(userLogin);
        GiftCertificate giftCertificate = giftCertificateService.findByName(certificateName);
        if (user != null && giftCertificate != null) {
            userService.buy(user, giftCertificate);
        } else {
            throw new IllegalArgumentException("User or GiftCertificate not found");
        }
    }


    @GetMapping("/{userId}/most-used-tag")
    public Tag getMostUsedTagWithHighestCost(@PathVariable("userId") Long userId) {
        return userService.getMostUsedTagWithHighestCost(userId);
    }
    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User CreateUser(@RequestBody User user){
        return userService.create(user);

    }

}
