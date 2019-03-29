package com.chainfire.parsers.csvparser.processed;

import com.chainfire.parsers.csvparser.processors.CsvRecordProcessor;
import com.chainfire.parsers.models.GpPractice;
import com.chainfire.parsers.models.RecordResult;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessedPracticeRecordProcessor implements CsvRecordProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessedPracticeRecordProcessor.class);

    @Override
    public RecordResult process(CSVRecord record) {
        createPractice(record);

        return null;
    }

    private GpPractice createPractice(CSVRecord record) {
        GpPractice practice = new GpPractice();
        practice.setOrganisationCode(record.get(0));
        practice.setName(record.get(1));

        return practice;
    }
}
