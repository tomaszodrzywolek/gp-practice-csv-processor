package com.chainfire.parsers.csvparser;

import com.chainfire.parsers.csvparser.processors.CsvParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/parsers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParserController {

    private CsvParser parser;

    public ParserController(CsvParser parser) {
        this.parser = parser;
    }

    @PostMapping("csv/parse")
    public ResponseEntity parseCsv(@RequestBody String filepath) throws FileNotFoundException {
        parser.parseFile(filepath);

        return null;

    }


}
