package com.chainfire.parsers.csvparser.processed;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class PracticeCsvProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeCsvProcessor.class);

    public List<ProcessedGpPractice> processCsvTemplate(InputStream inputStream) {
        CSVFormat csvFormat = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase();

        Charset charset = Charset.defaultCharset();
        LOGGER.debug("Starting csv template processing");

        try (CSVParser csvParser = CSVParser.parse(inputStream, charset, csvFormat)) {
            LOGGER.debug("CSVParser instantiated");
            return parseFile(csvParser);

        } catch (Exception e) {
            LOGGER.debug("Error processing csv file");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<ProcessedGpPractice> parseFile(CSVParser csvParser) {
        ArrayList<ProcessedGpPractice> practices = new ArrayList<>();
        for (CSVRecord csvPractice : csvParser) {
            ProcessedGpPractice practice = convertToProcessedPractice(csvPractice);
            practices.add(practice);
        }
        return practices;
    }

    private ProcessedGpPractice convertToProcessedPractice(CSVRecord csvPractice) {
        ProcessedGpPractice practice = new ProcessedGpPractice();
        practice.setOrganisationCode(csvPractice.get(0));
        practice.setName(csvPractice.get(1));
        practice.setHouseNumber(csvPractice.get(2));
        practice.setStreet(csvPractice.get(3));
        practice.setCity(csvPractice.get(4));
        practice.setState(csvPractice.get(5));
        practice.setCounty(csvPractice.get(6));
        practice.setDistrict(csvPractice.get(7));
        practice.setPostalCode(csvPractice.get(8));
        practice.setCountry(csvPractice.get(9));
        practice.setPhoneNumber(csvPractice.get(10));
        practice.setEmail(csvPractice.get(11));
        practice.setType(csvPractice.get(12));
        practice.setLocationId(csvPractice.get(13));
        practice.setLatitude(Double.valueOf(csvPractice.get(14)));
        practice.setLongitude(Double.valueOf(csvPractice.get(15)));

        return practice;
    }
}
