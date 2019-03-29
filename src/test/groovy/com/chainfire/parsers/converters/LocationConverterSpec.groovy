package com.chainfire.parsers.converters

import com.chainfire.parsers.CommonTestHelpers
import spock.lang.Specification

class LocationConverterSpec extends Specification {

    LocationConverter converter = new LocationConverter()

    def "should properly convert practice location response to location"() {
        given: "location response to convert"
        def locationResponse = CommonTestHelpers.createLocationResponseNode()

        when: "converting location response"
        def location = converter.toLocation(locationResponse)

        then: "location object is properly populated"
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
}
