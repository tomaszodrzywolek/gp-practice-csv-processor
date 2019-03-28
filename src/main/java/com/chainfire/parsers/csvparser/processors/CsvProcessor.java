package com.chainfire.parsers.csvparser.processors;

import com.chainfire.parsers.helpers.JSON;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class CsvProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvProcessor.class);

    public void processFile(CSVParser csvParser) {

        Iterator<CSVRecord> iterator = csvParser.iterator();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            System.out.println(JSON.toJsonPretty(record.get(1)));
        }
    }
}
