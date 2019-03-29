package com.chainfire.parsers.csvparser.processors;

import com.chainfire.parsers.converters.LocationConverter;
import com.chainfire.parsers.models.GpPractice;
import com.chainfire.parsers.models.Location;
import com.chainfire.parsers.models.LocationDetails;
import com.chainfire.parsers.models.RecordResult;
import com.chainfire.parsers.services.LocationService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RawPracticeRecordProcessor implements CsvRecordProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawPracticeRecordProcessor.class);

    private LocationService locationService = new LocationService();
    private LocationConverter locationConverter = new LocationConverter();

    @Override
    public RecordResult process(CSVRecord record) {
        if (isActivePractice(record)) {
            GpPractice practice = createPractice(record);
            Location location = getLocation(practice);
            practice.setLocation(setLocationDetails(location));

            return new RecordResult(practice, location);
        }
        return null;
    }

    private Location getLocation(GpPractice practice) {
        ObjectNode practiceResponse = locationService.locatePractice(practice);

        Location location = locationConverter.toLocation(practiceResponse);
        if (location != null) {
            location.setPracticeOrganisationCode(practice.getOrganisationCode());
            location.setPracticeName(practice.getName());
        }
        return location;
    }

    private LocationDetails setLocationDetails(Location location) {
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setLocationId(location.getLocationId());
        locationDetails.setLatitude(location.getDisplayPosition().getLatitude());
        locationDetails.setLongitude(location.getDisplayPosition().getLongitude());
        locationDetails.setAddress(location.getAddress());

        return locationDetails;
    }

    private GpPractice createPractice(CSVRecord record) {
        GpPractice practice = new GpPractice();
        practice.setOrganisationCode(record.get(0));
        practice.setName(record.get(1));
        practice.setAddress1(record.get(4));
        practice.setAddress2(record.get(5));
        practice.setAddress3(record.get(6));
        practice.setAddress4(record.get(7));
        practice.setAddress5(record.get(8));
        practice.setPostcode(record.get(9));
        practice.setPhoneNumber(record.get(17));

        return practice;
    }

    private boolean isActivePractice(CSVRecord record) {
        String practiceStatusCode = record.get(12);
        return !practiceStatusCode.isEmpty() &&
                practiceStatusCode.equals("A");
    }
}
