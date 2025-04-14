/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment;


import de.kherud.llama.ModelParameters;
import edu.uthouston.sbmi.hootation.llmenrichment.model.NLAxiomData;
import edu.uthouston.sbmi.hootation.models.OutputRecord;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTextArea;

/**
 *
 * @author tuan
 */
public class LLMAdapter {
    
    private static LLMAdapter INSTANCE = null;
    
    private LLMEnrichment llm_enrichment;
    private LLMFactChecker llm_fact_checker;
    private LLMManagement llm_management;
    
    private ModelParameters modelParams = null;
    
    public synchronized static LLMAdapter getInstance(){
        
        if(INSTANCE == null){
            
            INSTANCE = new LLMAdapter();
            
        }
        
        
        return INSTANCE;
    }
    
    
    private LLMAdapter(){
        
        init();
             
    }
    
    private void initializeModelParameters(){
        
    }
    
    private void init(){
        llm_enrichment = new LLMEnrichment();
        llm_fact_checker = new LLMFactChecker();
        
        
    }
    
    public void initFactChecker(){
        llm_fact_checker.initModelParameters();
    }
    
    public void initLLMEnrichment(){
        llm_enrichment.initModelParameters();
    }
    
    public void retrieveLLMModel(String file_url, String save_dir, JTextArea panelOutput){
        llm_management = LLMManagement.getInstance();
        
        llm_management.downloadFile(file_url, save_dir, panelOutput);
    }
    
    public String excecuteFactChecking(String nl_string, String axiom_type){
        llm_management = LLMManagement.getInstance();
        
        
        
        return llm_fact_checker.checkSentenceAccuracy(nl_string, axiom_type);
    }
    
    public void executeFactChecking(ArrayList<OutputRecord> records){
        
        llm_management = LLMManagement.getInstance();
        //set up parameter
        
        //execute
        llm_fact_checker.checkSentenceAccuracy(records);
        
    }
    
    public void initializeLLMModelEnrichement(){
        llm_enrichment.initModelParameters();
    }
    
    public void initializeLLMModelFactChecker(){
        llm_fact_checker.initModelParameters();
    }
    
    public String executeLLMEnhancement(String nl_string, String axiom_type){
        
        llm_management = LLMManagement.getInstance();
        
        return llm_enrichment.translateAxiom(nl_string, axiom_type);
    }
    
    public void executeLLMEnhancement(ArrayList<OutputRecord> records){
        
        llm_management = LLMManagement.getInstance();
        //set up parameters
        
        //execute
        llm_enrichment.translateAxioms(records);
    }
    
    public static void main(String[] args) {
        
    }
}
