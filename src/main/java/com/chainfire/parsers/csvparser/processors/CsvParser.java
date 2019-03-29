package com.chainfire.parsers.csvparser.processors;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class CsvParser {

    private CsvTemplateProcessor templateProcessor;

    public CsvParser(CsvTemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    public void parseFile(String filepath) throws FileNotFoundException {
        FileInputStream csvIs = readFile(filepath);
        templateProcessor.processCsvTemplate(csvIs);
    }

    private FileInputStream readFile(String filepath) throws FileNotFoundException {
        File initialFile = new File(filepath);
        return new FileInputStream(initialFile);
    }
}
