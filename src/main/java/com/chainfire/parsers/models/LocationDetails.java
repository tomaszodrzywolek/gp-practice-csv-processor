package com.chainfire.parsers.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDetails {

    private Double latitude;
    private Double longitude;
    private String locationId;
    private Address address;
}
