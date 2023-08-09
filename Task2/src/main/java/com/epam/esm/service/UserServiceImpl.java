package com.epam.esm.service;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return userDao.findAll(offset, size);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void buy(User user, GiftCertificate giftCertificate) {
        userDao.buy(user, giftCertificate);
    }

    @Override
    public List<UserOrder> getUserOrders(Long userId) {
        return userDao.getUserOrders(userId);
    }

    @Override
    public Tag getMostUsedTagWithHighestCost(Long userId) {
        return userDao.getMostUsedTagWithHighestCost(userId);
    }


}
