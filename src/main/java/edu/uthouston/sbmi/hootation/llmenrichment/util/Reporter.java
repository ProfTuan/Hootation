/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment.util;


import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author tuan
 */
public class Reporter {
    
    private static Reporter INSTANCE = null;
    
    private Reporter() {
    }
    
    public static Reporter getInstance() {
        
        if(INSTANCE == null){
            INSTANCE = new Reporter();
        }
        
        return INSTANCE;
        
    }
    
    public List<String[]> readcsv(String filepath) {        
        List<String[]> records = null;
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {
            records = reader.readAll();            
            /*
            for (String[] record : records) {                
                System.out.println("Record: " + record.length);
                for (String field : record) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }
            */
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return records;
    }
    
     public void writeCsv(String filePath, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    
}
