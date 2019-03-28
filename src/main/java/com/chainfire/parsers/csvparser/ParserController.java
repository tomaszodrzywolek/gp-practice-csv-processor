package com.chainfire.parsers.csvparser;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parsers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParserController {

    @PostMapping("csv/parse")
    public ResponseEntity createPatient() {
        return ResponseEntity.ok("{\"message\": \"hello\"}");

    }


}
