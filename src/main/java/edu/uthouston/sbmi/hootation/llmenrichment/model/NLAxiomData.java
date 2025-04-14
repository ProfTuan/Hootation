/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment.model;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 *
 * @author mac
 */
public class NLAxiomData {
    
    private OWLAxiom axiom;
    private String natural_language_translation;
    private String llm_natural_language_translation;
    private String llm_fact_information;
    
    public NLAxiomData(){
        
    }

    public void setOWLAxiom(OWLAxiom axiom){
        
        this.axiom = axiom;
        
    }
    
    public void setFactInformation(String value){
        this.llm_fact_information = value;
    }
    
    public String getFactInformation(){
        return this.llm_fact_information;
    }
    
    public void setNLTranslation(String natural_language){
        this.natural_language_translation = natural_language;
    }
    
    public void setLLMNaturalLanguageTranslation(String natural_language){
        this.llm_natural_language_translation = natural_language;
    }
    
    public AxiomType getAxiomType(){
        return axiom.getAxiomType();
    }
    
    public String getNLTranslation(){
        return this.natural_language_translation;
    }
    
    public String getLLMNaturalLanguageTranslation(){
        return this.llm_natural_language_translation;
    }
    
}
