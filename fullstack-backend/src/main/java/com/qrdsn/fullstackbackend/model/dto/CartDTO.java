package com.qrdsn.fullstackbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartDTO {
    private Long id;
    UserDTO user;
    @JsonManagedReference
    private Set<CartProductDTO> cartProducts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_member",
            joinColumns = {
                    @JoinColumn(name = "team_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            })
    public List<User> user;
}
