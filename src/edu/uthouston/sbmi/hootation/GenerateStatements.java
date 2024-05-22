package edu.uthouston.sbmi.hootation;

import java.io.File;
import java.util.ArrayList;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.ToStringRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import edu.uthouston.sbmi.owl2nl.OWLAxiomConversionException;
import edu.uthouston.sbmi.owl2nl.OWLAxiomConverter;


public class GenerateStatements {

	OWLOntology ontology;

	OWLAxiomConverter converter;

	private ArrayList<String> nl_statements;
	
	private static GenerateStatements instance = null;

	protected GenerateStatements() {
		// TODO Auto-generated constructor stub

	}
	
	public static GenerateStatements getInstance(){
		if(instance == null){
			instance = new GenerateStatements();
		}

		return instance;
	}
	public void init(File ontologyFile){
		nl_statements = new ArrayList<String>();
		ToStringRenderer.getInstance().setRenderer(new DLSyntaxObjectRenderer());
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		try {
			ontology = man.loadOntologyFromOntologyDocument(ontologyFile);

		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void convertAxiomsToStatements(){
		converter = new OWLAxiomConverter(ontology);
		for (OWLAxiom axiom : ontology.getAxioms()) {
			if(axiom.isLogicalAxiom()){
				try {

					String output = converter.convert(axiom);
					nl_statements.add(output);

				} catch (OWLAxiomConversionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
	}

	public void reset(){
		nl_statements.clear();
	}

	public ArrayList<String> getNl_statements() {
		return nl_statements;
	}

	public void setNl_statements(ArrayList<String> nl_statements) {
		this.nl_statements = nl_statements;
	}

	public static void main(String[] args) throws Exception {
		GenerateStatements gs = GenerateStatements.getInstance();
		gs.init(new File("/Users/mac/Desktop/persona.owl"));
		gs.convertAxiomsToStatements();
		
	}
	

}
