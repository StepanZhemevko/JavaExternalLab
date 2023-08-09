package com.epam.esm.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {
    private Long id;
    private User user;
    private GiftCertificate giftCertificate;
    private Double price;
    private LocalDateTime purchaseTimestamp;
}
