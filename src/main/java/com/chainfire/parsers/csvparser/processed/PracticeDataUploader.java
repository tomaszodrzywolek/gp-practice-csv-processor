package com.chainfire.parsers.csvparser.processed;

import com.chainfire.parsers.helpers.JSON;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class PracticeDataUploader {

    private static final String COUCHDB_BASE_URL = "http://3.8.172.44:5984";
    private static final String PRACTICES_DB_NAME = "/practices";
    private static final String DB_USERNAME = "delfinhealth";
    private static final String DB_PASSWORD = "T3amS1te!!";

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> uploadPractices(List<ProcessedGpPractice> practices) {

        UriComponents url = UriComponentsBuilder
                .fromHttpUrl(COUCHDB_BASE_URL)
                .path(PRACTICES_DB_NAME)
                .path("/_bulk_docs")
                .build()
                .encode();

        PracticeDataPayload requestBody = new PracticeDataPayload(practices);
        HttpEntity<Object> request = new HttpEntity<>(requestBody, requestHeaders());

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url.toUri(), HttpMethod.POST, request, Object.class);

            System.out.println(JSON.toJsonPretty(response));
            return response;
        } catch (HttpClientErrorException e) {
            System.out.println(e.getRawStatusCode());
            System.out.println(e.getResponseBodyAsString());
            throw e;
        }


    }

    private HttpHeaders requestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String encodedCredentials = Base64Utils.encodeToString((DB_USERNAME + ":" + DB_PASSWORD).getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        return headers;
    }


    @Data
    static class PracticeDataPayload {
        private List<GpPracticeEntity> docs;

        public PracticeDataPayload(List<ProcessedGpPractice> practices) {
            ArrayList<GpPracticeEntity> docs = new ArrayList<>();
            for (ProcessedGpPractice practice : practices) {
                GpPracticeEntity practiceEntity = new GpPracticeEntity();
                BeanUtils.copyProperties(practice, practiceEntity);
                practiceEntity.setId(practice.getOrganisationCode());
                docs.add(practiceEntity);
            }
            this.docs = docs;
        }
    }
}
