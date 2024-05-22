package edu.uthouston.sbmi.hootation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.ToStringRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import edu.uthouston.sbmi.owl2nl.OWLAxiomConversionException;
import edu.uthouston.sbmi.owl2nl.OWLAxiomConverter;

public class DisplayStatements {
	
	static String ontologyURL;
	static String fileName;

	public DisplayStatements() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws OWLOntologyCreationException, OWLAxiomConversionException {
		// TODO Auto-generated method stub
		
		boolean wantsOutput = false;
		String dataContent = "";
		if(args.length<1){
			System.out.println("Please specifiy a location for the ontology resource");
			return;
		}

		if(args.length ==2){
			wantsOutput = true;
			fileName = args[1];
		}

		//ontologyURL = args[0];
		
		//wantsOutput = true;
		//fileName = "viso-hpv.csv";
		ToStringRenderer.getInstance().setRenderer(new DLSyntaxObjectRenderer());

		OWLOntologyManager man = OWLManager.createOWLOntologyManager();

		OWLOntology ontology = man.loadOntologyFromOntologyDocument(new File("/Users/mac/Desktop/persona.owl"));
		//OWLOntology ontology = man.loadOntologyFromOntologyDocument(new File(args[0]));
		OWLAxiomConverter converter = new OWLAxiomConverter(ontology);

		for (OWLAxiom axiom : ontology.getAxioms()) {
			if(axiom.isLogicalAxiom()){
				String output = converter.convert(axiom);

				if(output != null){
					System.out.println("**Converting: " + axiom + " (" + axiom.getAxiomType().getName() +")");
					System.out.println("**Output: " + output +"\n");

					if(wantsOutput){
						dataContent = dataContent.concat(axiom.getAxiomType().getName()+";"+axiom+";"+output+"\n");
					}

				}
				
				/*if(output == null){
					System.out.println(axiom.getAxiomType().getName());
				}*/

			}
		}

		if(wantsOutput){
			
			System.out.println("Outputting results...");
			//new OutputStreamWriter(fileName, StandardCharsets.UTF_8);
			try(  Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))){
				w.append(dataContent);
				w.flush();
				w.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Done");
			
		}

	}

}
