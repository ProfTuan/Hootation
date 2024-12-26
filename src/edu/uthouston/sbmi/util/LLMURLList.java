/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
    
    private String[] header = { "URLS", "LABEL"};
    
    //private ArrayList<String, URL> llm_list;
    private Set<LLMListItem> llm_list;
    
    private LLMURLList(){
        
        llm_list = new HashSet<LLMListItem>();
        
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("llm_url.csv");
        
        //BufferedReader bufRdr = new BufferedReader(new InputStreamReader(resourceAsStream));
        
        CSVFormat csv_format = CSVFormat.DEFAULT.builder().setHeader(header).setSkipHeaderRecord(true).build();
        
        try {
            CSVParser parse = csv_format.parse(new InputStreamReader(resourceAsStream));
            
            Iterator<CSVRecord> iterator = parse.iterator();
            
            while(iterator.hasNext()){
                CSVRecord record = iterator.next();
                
                String url = record.get("URL").toString();
                String label = record.get("LABEL").toString();
                LLMListItem item = new LLMListItem(url, label);
                
                llm_list.add(item);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LLMURLList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Set <LLMListItem> getLLMList(){
        return llm_list;
    }
    
    static public LLMURLList getInstance(){
        
        if(INSTANCE == null){
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
        
        public String getLabel(){
            return LABEL;
        }
        
        public String getURL(){
            return URL;
        }
    }
    

    
    
}


