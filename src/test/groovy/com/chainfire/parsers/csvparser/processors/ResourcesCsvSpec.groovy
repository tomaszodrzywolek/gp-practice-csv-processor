package com.chainfire.parsers.csvparser.processors

import spock.lang.Specification

class ResourcesCsvSpec extends Specification {

    def csvProcessor = new CsvProcessor()
    def csvTemplateProcessor = new CsvTemplateProcessor(csvProcessor)

    def "should return single process result in collection when csv is loaded from resources as input stream"() {

        when: "loading input stream from file"
        def file = new File("/Users/tomaszodrzywolek/Projects/practices/gp_practices/epraccur.csv")

        and: "processing template"
        file.withInputStream { stream ->
            csvTemplateProcessor.processCsvTemplate(new BufferedInputStream(stream))
        }

        then: "process results contain one sheet process reports"
        true
    }
}
