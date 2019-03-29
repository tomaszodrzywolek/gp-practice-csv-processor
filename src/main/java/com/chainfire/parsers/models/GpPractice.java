package com.chainfire.parsers.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class GpPractice {

    private String organisationCode;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String postcode;
    private String phoneNumber;
    private String email;
    private String type = "G";
    private LocationDetails location = new LocationDetails();
    private boolean isRegistered;

    public List<String> asList() {
        return Arrays.asList(
                organisationCode,
                name,
                location != null ? checkAndReturn(location.getAddress().getHouseNumber()) : "",
                location != null ? checkAndReturn(location.getAddress().getStreet()) : "",
                location != null ? checkAndReturn(location.getAddress().getCity()) : "",
                location != null ? checkAndReturn(location.getAddress().getState()) : "",
                location != null ? checkAndReturn(location.getAddress().getCounty()) : "",
                location != null ? checkAndReturn(location.getAddress().getDistrict()) : "",
                location != null ? checkAndReturn(location.getAddress().getPostalCode()) : "",
                location != null ? checkAndReturn(location.getAddress().getCountry()) : "",
                checkAndReturn(phoneNumber),
                checkAndReturn(email),
                type,
                location != null ? checkAndReturn(location.getLocationId()) : "",
                location != null ? checkAndReturn(Double.toString(location.getLatitude())) : "",
                location != null ? checkAndReturn(Double.toString(location.getLongitude())) : "");
    }

    private String checkAndReturn(String field) {
        return field == null || field.isEmpty() ? "" : field;
    }

}
