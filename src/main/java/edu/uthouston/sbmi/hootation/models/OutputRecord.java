/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.models;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 *
 * @author mac
 */
public class OutputRecord {
    
    private AxiomType axiom_type;
    private OWLAxiom axiom;
    private String natural_language;

    public AxiomType getAxiom_type() {
        return axiom_type;
    }
    
    public String getStringValueByMember(String membership){
        
        if(membership == "AXIOM_TYPE") return axiom_type.toString();
        
        else if(membership == "AXIOM") return axiom.toString();
        
        else if(membership == "NATURAL LANGUAGE TRANSLATION") return natural_language;
        
        else return "ERROR";
        
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
