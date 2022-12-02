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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Category category;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image_file;
    private Float price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CartProduct> cartProducts;
}