/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author tuan
 */
public class LLMURLList {

    static public LLMURLList INSTANCE = null;

    private String[] header = {"URLS", "LABEL"};

    //private ArrayList<String, URL> llm_list;
    private ArrayList<LLMListItem> llm_list;
    
    final private String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    
    final private String file_urls = rootPath + "llm_urls.csv";

    private LLMURLList() {
        
        llm_list = new ArrayList<LLMListItem>();
        /*
        try {
            Path resourcePath = Paths.get(Objects.requireNonNull(LLMURLList.class.getResource("/llm_urls.csv")).toURI());
            
            System.out.println(resourcePath.toAbsolutePath());
            
            FileReader file_reader = new FileReader(resourcePath.toFile());
            
            CSVFormat csv_format = CSVFormat.DEFAULT.builder().setHeader(header).setSkipHeaderRecord(true).build();
             
             CSVParser parse = csv_format.parse(file_reader);
             
             
             Iterator<CSVRecord> iterator = parse.iterator();
            
            while(iterator.hasNext()){
                CSVRecord record = iterator.next();
                
                String url = record.get("URL");
                String label = record.get("LABEL");
                LLMListItem item = new LLMListItem(url, label);
                
                llm_list.add(item);
                
            }
            
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
        try {
            FileReader file_reader = new FileReader(file_urls);
            
             CSVFormat csv_format = CSVFormat.DEFAULT.builder().setHeader(header).setSkipHeaderRecord(true).build();
             
             CSVParser parse = csv_format.parse(file_reader);
             
             
             Iterator<CSVRecord> iterator = parse.iterator();
            
            while(iterator.hasNext()){
                CSVRecord record = iterator.next();
                
                String url = record.get("URL");
                String label = record.get("LABEL");
                LLMListItem item = new LLMListItem(url, label);
                
                llm_list.add(item);
                
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("llm_urls.csv");

        BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(resourceAsStream));

        boolean header = true;

        try {
            String readLine;
            while ((readLine = buffer_reader.readLine()) != null) {

                if (header == true) {
                    header = false;
                } else {
                    //first colum is the url, second is the label
                    String[] split = readLine.split(",");
                    String url = split[0];
                    String label = split[1];
                    LLMListItem item = new LLMListItem(url, label);

                    llm_list.add(item);

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        CSVFormat csv_format = CSVFormat.DEFAULT.builder().setHeader(header).setSkipHeaderRecord(true).build();
        
        try {
            CSVParser parse = csv_format.parse(new InputStreamReader(resourceAsStream));
            
            Iterator<CSVRecord> iterator = parse.iterator();
            
            while(iterator.hasNext()){
                CSVRecord record = iterator.next();
                
                String url = record.get("URL");
                String label = record.get("LABEL");
                LLMListItem item = new LLMListItem(url, label);
                
                llm_list.add(item);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        
        /*

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("llm_urls.csv");

        BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(resourceAsStream));

        boolean header = true;

        try {
            String readLine;
            while ((readLine = buffer_reader.readLine()) != null) {

                if (header == true) {
                    header = false;
                } else {
                    //first colum is the url, second is the label
                    String[] split = readLine.split(",");
                    String url = split[0];
                    String label = split[1];
                    LLMListItem item = new LLMListItem(url, label);

                    llm_list.add(item);

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        CSVFormat csv_format = CSVFormat.DEFAULT.builder().setHeader(header).setSkipHeaderRecord(true).build();
        
        try {
            CSVParser parse = csv_format.parse(new InputStreamReader(resourceAsStream));
            
            Iterator<CSVRecord> iterator = parse.iterator();
            
            while(iterator.hasNext()){
                CSVRecord record = iterator.next();
                
                String url = record.get("URL");
                String label = record.get("LABEL");
                LLMListItem item = new LLMListItem(url, label);
                
                llm_list.add(item);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public ArrayList<LLMListItem> getLLMList() {
        return llm_list;
    }

    static public LLMURLList getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new LLMURLList();
        }

        return INSTANCE;

    }

    public static class LLMListItem {

        private String URL;
        private String LABEL;

        public LLMListItem(String url, String label) {

            URL = url;
            LABEL = label;

        }

        public String getLabel() {
            return LABEL;
        }

        public String getURL() {
            return URL;
        }
    }

}
