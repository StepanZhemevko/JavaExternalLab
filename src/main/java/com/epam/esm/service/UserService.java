package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    List<User> findAll(int page, int size);

    User findById(int id);
    User findByLogin(String login);

    void buy(User user, GiftCertificate giftCertificate);

    List<UserOrder> getUserOrders(Long userId);


    Tag getMostUsedTagWithHighestCost(Long userId);

    User create(User user);
}
