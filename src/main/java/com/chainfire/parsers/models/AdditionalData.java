package com.chainfire.parsers.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdditionalData {

    private String key;
    private String value;

    public AdditionalData(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
