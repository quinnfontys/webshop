package com.qrdsn.fullstackbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
