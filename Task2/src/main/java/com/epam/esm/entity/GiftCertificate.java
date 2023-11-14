package com.epam.esm.entity;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@Data
public class GiftCertificate {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String duration;
    private String createDate;
    private String lastUpdateDate;
    private Set<Tag> tags;
}
