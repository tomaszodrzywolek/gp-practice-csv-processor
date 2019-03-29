package com.chainfire.parsers.csvparser.processors;

import com.chainfire.parsers.models.RecordResult;
import org.apache.commons.csv.CSVRecord;

public interface CsvRecordProcessor {

    RecordResult process(CSVRecord record);
}
