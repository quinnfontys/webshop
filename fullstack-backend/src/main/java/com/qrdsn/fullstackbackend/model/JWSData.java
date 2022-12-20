package com.qrdsn.fullstackbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class JWSData {
    private String issuer;
    private Date expiration;
    private String email;
    private String id;
}
