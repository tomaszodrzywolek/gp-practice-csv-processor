package com.chainfire.parsers.models;

import lombok.Data;

@Data
public class RecordResult {

    private GpPractice practice;
    private Location location;

    public RecordResult(GpPractice practice, Location location) {
        this.practice = practice;
        this.location = location;
    }

    public boolean isPopulated() {
        return practice != null && location != null;
    }
}
