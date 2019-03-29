package com.chainfire.parsers

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

class CommonTestHelpers {

    static ObjectNode createLocationResponseNode() {
        def node = JsonNodeFactory.withExactBigDecimals(false)
                .objectNode()

        def view0 = node.with("Response")
                .withArray("View")
        // View array
                .insertObject(0)

        def result0 = view0.put("ViewId", 0)
                .put("_type", "SearchResultsViewType")
                .withArray("Result")
                .insertObject(0)

        result0.put("Relevance", 1)
                .set("Location", createLocationNode())

        return node
    }

    static def createLocationNode() {
        def locationNode = JsonNodeFactory.withExactBigDecimals(false)
                .objectNode()
                .put("LocationId", "NT_tjXvKvZlHyUf3QNB1G7yqD")

        locationNode.with("DisplayPosition")
                .put("Latitude", 53.5655519)
                .put("Longitude", -2.3082596)

        def addressNode = locationNode.with("Address")
                .put("State", "England")
                .put("Country", "GBR")
                .put("Street", "Dumers Lane")
                .put("PostalCode", "M26 2TP")
                .put("City", "Manchester")
                .put("County", "Lancashire")
                .put("District", "Radcliffe")

        addressNode.withArray("AdditionalData")
                .addAll([
                JsonNodeFactory.withExactBigDecimals(false)
                        .objectNode().put("key", "CountryName").put("value", "United Kingdom"),
                JsonNodeFactory.withExactBigDecimals(false)
                        .objectNode().put("key", "StateName").put("value", "England"),
                JsonNodeFactory.withExactBigDecimals(false)
                        .objectNode().put("key", "CountyName").put("value", "Lancashire")])

        return locationNode
    }
}
