package com.chainfire.parsers.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Location {

    private String practiceOrganisationCode;
    private String practiceName;
    private DisplayPosition displayPosition;
    private Address address;
    private String locationId;

    public Location(ObjectNode locationNode) {
        this.locationId = locationNode.get("LocationId").asText();

        ObjectNode positionNode = locationNode.with("DisplayPosition");
        this.displayPosition = new DisplayPosition(positionNode.get("Latitude").asDouble(),
                positionNode.get("Longitude").asDouble());

        ObjectNode addressNode = locationNode.with("Address");
        this.address = populateAddress(addressNode);
    }

    public List<String> asList() {
        return Arrays.asList(
                (practiceOrganisationCode != null && !practiceOrganisationCode.isEmpty()) ? practiceOrganisationCode : "",
                (practiceName != null && !practiceName.isEmpty()) ? practiceName : "",
                locationId,
                Double.toString(displayPosition.getLatitude()),
                Double.toString(displayPosition.getLongitude()),
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCounty(),
                address.getDistrict(),
                address.getPostalCode(),
                address.getCountry()
        );
    }

    private Address populateAddress(ObjectNode addressNode) {
        Address address = new Address();
        address.setState(fromNode(addressNode, "State"));
        address.setCountry(fromNode(addressNode, "Country"));
        address.setStreet(fromNode(addressNode, "Street"));
        address.setPostalCode(fromNode(addressNode, "PostalCode"));
        address.setCity(fromNode(addressNode, "City"));
        address.setCounty(fromNode(addressNode, "County"));
        address.setDistrict(fromNode(addressNode, "District"));
        address.setHouseNumber(fromNode(addressNode, "HouseNumber"));

        address.setAdditionalData(populateAdditionalData(addressNode));

        return address;
    }

    private List<AdditionalData> populateAdditionalData(ObjectNode addressNode) {
        ArrayList<AdditionalData> additionalData = new ArrayList<>();

        if (addressNode.has("AdditionalData")) {
            ArrayNode additionalDataNode = addressNode.withArray("AdditionalData");
            for (JsonNode data : additionalDataNode) {
                additionalData.add(new AdditionalData(data.get("key").asText(), data.get("value").asText()));
            }
        }
        return additionalData;
    }

    private String fromNode(ObjectNode addressNode, String value) {
        return addressNode.has(value) ? addressNode.get(value).asText() : "";
    }
}
