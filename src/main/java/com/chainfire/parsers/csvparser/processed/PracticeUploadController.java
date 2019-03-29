package com.chainfire.parsers.csvparser.processed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController("/upload")
public class PracticeUploadController {

    private PracticeUploadService uploadService;

    @Autowired
    public PracticeUploadController(PracticeUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadPracticeData(@RequestBody String filepath) throws FileNotFoundException {

        return uploadService.uploadPracticeData(filepath);
    }


}
