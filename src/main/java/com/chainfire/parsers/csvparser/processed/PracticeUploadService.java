package com.chainfire.parsers.csvparser.processed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class PracticeUploadService {

    private PracticeCsvProcessor csvProcessor;

    @Autowired
    public PracticeUploadService(PracticeCsvProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
    }

    public List<ProcessedGpPractice> uploadPracticeData(String filepath) throws FileNotFoundException {
        FileInputStream csvIs = readFile(filepath);
        List<ProcessedGpPractice> practices = csvProcessor.processCsvTemplate(csvIs);

        return practices;

    }

    private FileInputStream readFile(String filepath) throws FileNotFoundException {
        File initialFile = new File(filepath);
        return new FileInputStream(initialFile);
    }
}
