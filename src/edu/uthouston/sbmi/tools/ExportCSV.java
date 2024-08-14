package edu.uthouston.sbmi.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.ToStringRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.parameters.Imports;

import edu.uthouston.sbmi.owl2nl.OWLAxiomConversionException;
import edu.uthouston.sbmi.owl2nl.OWLAxiomConverter;

public class ExportCSV {

	public ExportCSV() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws OWLOntologyCreationException, OWLAxiomConversionException, FileNotFoundException {
		// TODO Auto-generated method stub
		//ToStringRenderer.getInstance().setRenderer(new DLSyntaxObjectRenderer());
		
                DLSyntaxObjectRenderer renderer =  new DLSyntaxObjectRenderer();
                ToStringRenderer.setRenderer(()->renderer);
                
		String csvFileName = "/Users/mfamith/Desktop/people-final.txt";
		String dataContent = "";
		String ontologyURL = "/Users/mfamith/Desktop/geography.owl";
		
		//String ontologyURL = "/Users/mfamith/Desktop/TimeEventOntology.owl";
		
		//String ontologyURL = "/Users/mfamith/Desktop/ico_merged.owl";
		
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		
		OWLOntology ontology = man.loadOntologyFromOntologyDocument(new File(ontologyURL));
		
		OWLAxiomConverter converter = new OWLAxiomConverter(ontology);
		
		System.out.println("Processing...");
		for (OWLAxiom axiom : ontology.getTBoxAxioms(Imports.INCLUDED)) {
			
			if(axiom.isLogicalAxiom()){
				
				
					String output = converter.convert(axiom);
					if(output != null){
						dataContent = dataContent.concat(axiom.getAxiomType().getName()+";"+axiom+";"+output+"\n");
					}
				
				
				
				
			}
		}
		
		System.out.println("Outputting results...");
		try(  PrintWriter pwo = new PrintWriter( csvFileName )  ){
			pwo.print(dataContent);
		}
		System.out.println("Done");

	}

}
