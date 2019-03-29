package com.chainfire.parsers.models

import com.chainfire.parsers.CommonTestHelpers
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import spock.lang.Specification

class LocationSpec extends Specification {

    def "should create location object from Location JSON Node"() {
        given: "location JSON Node"
        def locationNode = CommonTestHelpers.createLocationNode()

        when: "creating location object"
        def location = new Location(locationNode)

        then: "location is populated"
        location.locationId == "NT_tjXvKvZlHyUf3QNB1G7yqD"
        location.address != null
        location.displayPosition != null

        and: "display position has populated latitude and longitude"
        location.displayPosition.latitude == 53.5655519 as double
        location.displayPosition.longitude == -2.3082596 as double

        and: "address has populated data"
        location.address.houseNumber == ""
        location.address.state == "England"
        location.address.postalCode == "M26 2TP"
        location.address.city == "Manchester"
        location.address.additionalData.size() == 3
    }

    ObjectNode addressNode() {
        return JsonNodeFactory
                .withExactBigDecimals(false)
                .objectNode()

    }

    JsonNode displayPositionNode() {
        return JsonNodeFactory
                .withExactBigDecimals(false)
                .objectNode()
                .put("Latitude", 53.5655519)
                .put("Longitude", -2.3082596)
    }

    Address createAddress() {
        def address = new Address()
        address.state = "England"
        address.country = "GBR"
        address.street = "Dumers Lane"
        address.postalCode = "M26 2TP"
        address.city = "Manchester"
        address.county = "Lancashire"
        address.district = "Radcliffe"
        address.additionalData = [
                new AdditionalData("CountryName", "United Kingdom"),
                new AdditionalData("StateName", "England"),
                new AdditionalData("CountyName", "Lancashire")]

        return address
    }
}
