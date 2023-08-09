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
    private String name;
    private Integer age;
    private Set<GiftCertificate> giftCertificates;

}
