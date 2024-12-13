/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.util;

import edu.uthouston.sbmi.hootation.models.OutputRecord;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author mac
 */
public class CSVWriter {

    private CSVFormat csvFormat = null;

    public CSVWriter(String[] headers) {
        csvFormat = CSVFormat.DEFAULT.builder().setHeader(headers).build();
    }

    public void write(String filePath, ArrayList<OutputRecord> records) throws IOException {

        Writer writer = Files.newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);

        for (OutputRecord record : records) {
            printer.printRecord(record.getAxiom_type(), record.getAxiom(), record.getNatural_language());

        }

        printer.flush();

    }

}
