package com.chainfire.parsers.csvparser.processors;

import com.chainfire.parsers.models.GpPractice;
import com.chainfire.parsers.models.Location;
import com.chainfire.parsers.models.ProcessResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RecordWriter {

    public void writeFiles(ProcessResult results) {
        try (
                BufferedWriter practiceWriter = Files.newBufferedWriter(Paths.get("./practice_results_england_wales_6001_end.csv"));

                CSVPrinter practiceCsvPrinter = new CSVPrinter(practiceWriter, CSVFormat.DEFAULT
                        .withHeader("Organisation Code", "Name", "House Number", "Street", "City", "State", "County",
                                "District", "Postal Code", "Country", "Phone Number", "Email", "Type", "LocationId",
                                "Latitude", "Longitude"));

                BufferedWriter locationWriter = Files.newBufferedWriter(Paths.get("./location_results_england_wales_6001_end.csv"));

                CSVPrinter locationCsvPrinter = new CSVPrinter(locationWriter, CSVFormat.DEFAULT
                        .withHeader("Practice Organisation Code", "LocationId", "Latitude", "Longitude", "HouseNumber",
                                "Street", "City", "State", "County", "District", "PostalCode", "Country"));

                BufferedWriter failuresWriter = Files.newBufferedWriter(Paths.get("./failures_results_england_wales_6001_end.csv"));

                CSVPrinter failuresCsvPrinter = new CSVPrinter(failuresWriter, CSVFormat.DEFAULT
                        .withHeader("Organisation Code", "Name", "House Number", "Street", "City", "State", "County",
                                "District", "Postal Code", "Country", "Phone Number", "Email", "Type", "LocationId",
                                "Latitude", "Longitude"))
        ) {
            for (GpPractice practice : results.getPractices()) {
                practiceCsvPrinter.printRecord(practice.asList());
            }

            practiceCsvPrinter.flush();

            for (Location location : results.getLocations()) {
                locationCsvPrinter.printRecord(location.asList());
            }

            locationCsvPrinter.flush();

            for (GpPractice failure : results.getFailures()) {
                failuresCsvPrinter.printRecord(failure.asList());
            }

            failuresCsvPrinter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
