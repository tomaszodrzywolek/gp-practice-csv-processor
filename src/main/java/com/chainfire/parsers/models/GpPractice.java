package com.chainfire.parsers.models;

import com.chainfire.parsers.helpers.JSON;
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
    private LocationDetails location = new LocationDetails();
    private boolean isRegistered;

    public List<String> asList() {
        return Arrays.asList(
                organisationCode,
                name,
                checkAndReturn(address1),
                checkAndReturn(address2),
                checkAndReturn(address3),
                checkAndReturn(address4),
                checkAndReturn(address5),
                checkAndReturn(postcode),
                checkAndReturn(phoneNumber),
                checkAndReturn(email),
                location != null ? checkAndReturn(location.getLocationId()) : "",
                location != null ? checkAndReturn(Double.toString(location.getLatitude())) : "",
                location != null ? checkAndReturn(Double.toString(location.getLongitude())) : "");
    }

    private String checkAndReturn(String field) {
        return field == null || field.isEmpty() ? "" : field;
    }

}
