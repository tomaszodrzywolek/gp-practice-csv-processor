package com.chainfire.parsers.models;

import lombok.Data;

@Data
public class RecordResult {

    private GpPractice practice;
    private Location location;
    private boolean isSuccess;

    public RecordResult(GpPractice practice, Location location) {
        this.practice = practice;
        this.location = location;
        this.isSuccess = true;
    }

    public RecordResult(GpPractice practice) {
        this.practice = practice;
        this.isSuccess = false;
    }

    public boolean isPopulated() {
        return practice != null && location != null;
    }
}
