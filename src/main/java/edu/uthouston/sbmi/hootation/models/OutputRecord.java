/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.models;

import edu.uthouston.sbmi.hootation.llmenrichment.model.NLAxiomData;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 *
 * @author mac
 */
public class OutputRecord extends NLAxiomData{
    
    private AxiomType axiom_type;
    private OWLAxiom axiom;
    private String natural_language;

    public AxiomType getAxiom_type() {
        return axiom_type;
    }
    
    public String getStringValueByMember(String membership){
        
        if(null == membership) return "ERROR";
        
        else switch (membership) {
            case "AXIOM TYPE":
                return axiom_type.toString();
            case "AXIOM":
                return axiom.toString();
            case "NATURAL LANGUAGE TRANSLATION":
                return natural_language;
            case "LLM Enhancement":
                return this.getLLMNaturalLanguageTranslation();
            case "Fact Check":
                return this.getFactInformation();
            default:
                return "ERROR";
        }
        
    }

    public void setAxiom_type(AxiomType axiom_type) {
        this.axiom_type = axiom_type;
    }

    public OWLAxiom getAxiom() {
        return axiom;
    }

    public void setAxiom(OWLAxiom axiom) {
        this.axiom = axiom;
    }

    public String getNatural_language() {
        return natural_language;
    }

    public void setNatural_language(String natural_language) {
        this.natural_language = natural_language;
    }
    
    
    
}
