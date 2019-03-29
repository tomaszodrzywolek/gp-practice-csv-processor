package com.chainfire.parsers.csvparser.processors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class CsvTemplateProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvTemplateProcessor.class);

    private CsvProcessor csvProcessor;

    @Autowired
    public CsvTemplateProcessor(CsvProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
    }

    public void processCsvTemplate(InputStream inputStream) {
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        Charset charset = Charset.defaultCharset();
        LOGGER.debug("Starting csv template processing");

        try (CSVParser csvParser = CSVParser.parse(inputStream, charset, csvFormat)) {
            LOGGER.debug("CSVParser instantiated");
            csvProcessor.processFile(csvParser);

        } catch (Exception e) {
            LOGGER.debug("Error processing csv file");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
