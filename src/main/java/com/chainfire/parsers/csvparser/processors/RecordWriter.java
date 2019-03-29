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
                BufferedWriter practiceWriter = Files.newBufferedWriter(Paths.get("./practice_results_100.csv"));

                CSVPrinter practiceCsvPrinter = new CSVPrinter(practiceWriter, CSVFormat.DEFAULT
                        .withHeader("Organisation Code", "Name", "Address1", "Address2", "Address3", "Address4", "Address5",
                                "Post Code", "Phone Number", "Email", "LocationId", "Latitude", "Longitude"));

                BufferedWriter locationWriter = Files.newBufferedWriter(Paths.get("./location_results_100.csv"));

                CSVPrinter locationCsvPrinter = new CSVPrinter(locationWriter, CSVFormat.DEFAULT
                        .withHeader("Practice Organisation Code", "LocationId", "Latitude", "Longitude", "HouseNumber",
                                "Street", "City", "State", "County", "District", "PostalCode", "Country"))
        ) {
            for (GpPractice practice : results.getPractices()) {
                practiceCsvPrinter.printRecord(practice.asList());
            }

            practiceCsvPrinter.flush();

            for (Location location : results.getLocations()) {
                locationCsvPrinter.printRecord(location.asList());
            }

            locationCsvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
