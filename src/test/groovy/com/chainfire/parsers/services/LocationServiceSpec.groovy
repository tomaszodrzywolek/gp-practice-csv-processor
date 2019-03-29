package com.chainfire.parsers.services

import com.chainfire.parsers.helpers.JSON
import com.chainfire.parsers.models.GpPractice
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
import spock.lang.Specification

class LocationServiceSpec extends Specification {

    LocationService locationService = new LocationService()

    def "should make request to management api and return location details"() {
        given: "GP practice"
        def practice = createGpPractice()

        when: "locating practice"
        def response = locationService.locatePractice(practice)

        then: "practice location details as JSON Node are returned"
        println(JSON.toJsonPretty(response))
        !response.isEmpty(new DefaultSerializerProvider.Impl())
    }

    GpPractice createGpPractice() {
        def practice = new GpPractice()
        practice.organisationCode = "Y06359"
        practice.name = "GRACE'S PLACE CHILDRENS HOSPICE"
        practice.address1 = "DUMERS LANE"
        practice.address2 = "RADCLIFFE"
        practice.address3 = "MANCHESTER"
        practice.address4 = ""
        practice.address5 = ""
        practice.postcode = "M26 2TP"
        practice.phoneNumber = "0161 7245269"
        practice.email = ""
        practice.registered = false

        return practice
    }
}
