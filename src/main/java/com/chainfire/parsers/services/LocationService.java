package com.chainfire.parsers.services;

import com.chainfire.parsers.models.GpPractice;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class LocationService {

    private RestTemplate restTemplate = new RestTemplate();

    public ObjectNode locatePractice(GpPractice practice) {
        String addressToFind = getAddressParamValue(practice);

        UriComponents url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080")
                .path("/api")
                .path("/locations")
                .path("/locateByAddress")
                .queryParam("searchtext", addressToFind)
                .build()
                .encode();

        ResponseEntity<ObjectNode> response = restTemplate.getForEntity(url.toUri(), ObjectNode.class);
        return response.getBody();
    }

    private String getAddressParamValue(GpPractice practice) {
        StringBuilder sb = new StringBuilder();
        sb.append(addressFragment(practice.getAddress1()))
                .append(addressFragment(practice.getAddress2()))
                .append(addressFragment(practice.getAddress3()))
                .append(addressFragment(practice.getAddress4()))
                .append(addressFragment(practice.getAddress5()))
                .append(addressFragment(practice.getPostcode()))
                .append("GBR");

        return sb.toString();
    }

    private String addressFragment(String addressFragment) {
        return (addressFragment == null || addressFragment.isEmpty()) ? "" : addressFragment + " ";
    }
}
