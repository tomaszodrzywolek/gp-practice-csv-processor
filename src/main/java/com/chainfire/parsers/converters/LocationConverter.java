package com.chainfire.parsers.converters;

import com.chainfire.parsers.models.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.NoSuchElementException;

public class LocationConverter {


    public Location toLocation(ObjectNode practiceResponse) {
        ArrayNode views = practiceResponse
                .with("Response")
                .withArray("View");

        if (views.hasNonNull(0)) {
            return convertToLocation((ObjectNode) views.get(0));
        }
        throw new NoSuchElementException("Practice not found");
    }

    private Location convertToLocation(ObjectNode view) {
        JsonNode result = view.withArray("Result").get(0);
        ObjectNode locationNode = (ObjectNode) result.with("Location");
        return new Location(locationNode);


    }
}
