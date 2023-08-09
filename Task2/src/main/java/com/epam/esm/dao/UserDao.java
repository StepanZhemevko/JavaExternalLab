package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> findAll(int offset, int limit);

    User findById(int id);

    void buy(User user, GiftCertificate giftCertificate);

    List<UserOrder> getUserOrders(Long userId);

    Tag getMostUsedTagWithHighestCost(Long userId);
}
