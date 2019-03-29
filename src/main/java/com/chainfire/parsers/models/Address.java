package com.chainfire.parsers.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private List<AdditionalData> additionalData;
    private String state;
    private String country;
    private String street;
    private String postalCode;
    private String city;
    private String county;
    private String district;
    private String houseNumber;
}
