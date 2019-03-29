package com.chainfire.parsers.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProcessResult {

    ArrayList<GpPractice> practices = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();

    public GpPractice addPractice(GpPractice practice) {
        practices.add(practice);

        return practice;
    }

    public Location addLocation(Location location) {
        locations.add(location);

        return location;
    }
}
