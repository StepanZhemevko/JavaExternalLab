package com.epam.esm.dao;

public class SQLQuery {
    public static final String INSERT = "INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String GET_ALL = "SELECT * FROM gift_certificate";
    public static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    public static final String INSERT_GIFT_HAS_TAG = "INSERT INTO gift_certificate_has_tag(gift_certificate_id, tag_id) " +
            "VALUES (?, ?)";
    public static final String INSERT_TAG = "INSERT INTO tag(name) VALUES(?)";
    public static final String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = ?";
    public static final String FIND_BY_TAG_NAME = "SELECT gc.id, gc.name, gc.description, gc.price, gc.duration, gc.create_date, gc.last_update_date "
            + "FROM gift_certificate gc "
            + "JOIN gift_certificate_tag gct ON gc.id = gct.gift_certificate_id "
            + "JOIN tag t ON gct.tag_id = t.id WHERE t.name = ?";
    public static final String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?, last_update_date = ? WHERE id = ?";
    public static final String GET_TAGS_BY_ID = "SELECT * FROM tag WHERE id = ?";
    public static final String DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    public static final String FIND_BY_NAME = "SELECT id, name FROM tag WHERE name = ?";
    public static final String DELETE_TAG_ASSOCIATION = "DELETE FROM gift_certificate_has_tag WHERE tag_id = ?";
    public static final String GET_ALL_TAGS = "SELECT * FROM tag";

}
