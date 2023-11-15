package com.epam.esm.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String name;
    private String password;
    private String email;
    private Integer age=31;
    private Set<GiftCertificate> giftCertificates;

}
