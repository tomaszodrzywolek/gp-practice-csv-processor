package com.chainfire.parsers.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayPosition {
    private double latitude;
    private double longitude;

    public DisplayPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
