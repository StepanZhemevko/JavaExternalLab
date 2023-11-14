package com.epam.esm.entity;


import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;
    private String name;

    public Tag(String tagName) {
        this.name = tagName;
    }
}
