package com.chainfire.parsers.csvparser.processors;

import com.chainfire.parsers.models.ProcessResult;
import com.chainfire.parsers.models.RecordResult;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvProcessor.class);

    private CsvRecordProcessor recordProcessor = new PracticeRecordProcessor();
    private RecordWriter recordWriter = new RecordWriter();

    public void processFile(CSVParser csvParser) {

        ProcessResult results = new ProcessResult();

        for (CSVRecord practice : csvParser) {
            RecordResult recordResult = recordProcessor.process(practice);

            if (recordResult != null && recordResult.isPopulated()) {
                results.addPractice(recordResult.getPractice());
                results.addLocation(recordResult.getLocation());
            }
        }

        recordWriter.writeFiles(results);
    }
}
