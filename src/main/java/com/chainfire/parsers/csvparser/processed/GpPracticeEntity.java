package com.chainfire.parsers.csvparser.processed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GpPracticeEntity extends ProcessedGpPractice {

    @JsonProperty("_id")
    private String id;
}
