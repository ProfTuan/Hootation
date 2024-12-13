/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.util;

import edu.uthouston.sbmi.hootation.GenerateStatements;
import edu.uthouston.sbmi.hootation.models.OutputRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mac
 */
public class ExcelWriter {

    private Workbook workbook = null;
    private Sheet sheet = null;

    public ExcelWriter(String[] headers) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Results");

        //header customization
        XSSFFont header_font = ((XSSFWorkbook) workbook).createFont();
        header_font.setBold(true);

        CellStyle header_style = workbook.createCellStyle();
        header_style.setFont(header_font);

        Row row_header = sheet.createRow(0);
        int index = 0;
        for (String header : headers) {
            Cell cell_header = row_header.createCell(index);
            cell_header.setCellValue(header);
            cell_header.setCellStyle(header_style);
            index++;
        }
    }

    public void write(String pathFile, ArrayList<OutputRecord> records, String[] headers) {

        //add data from outputRecords
        int row_num = 1;

        for (OutputRecord record : records) {
            Row row = sheet.createRow(row_num);
            int cell_index = 0;
            for (String header : headers) {
                String value = record.getStringValueByMember(header);
                Cell cell = row.createCell(cell_index);
                cell.setCellValue(value);
                cell_index++;
            }

            row_num++;
        }

        //save file
        File outputFile = new File(pathFile);
        try {
            FileOutputStream outstream = new FileOutputStream(outputFile);
            workbook.write(outstream);
            workbook.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateStatements.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateStatements.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
