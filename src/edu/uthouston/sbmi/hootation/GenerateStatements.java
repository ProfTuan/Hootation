package edu.uthouston.sbmi.hootation;

import com.google.common.base.Charsets;
import edu.uthouston.sbmi.hootation.models.OutputRecord;
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
import edu.uthouston.sbmi.util.CSVWriter;
import edu.uthouston.sbmi.util.ExcelWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GenerateStatements {

	private OWLOntology ontology;

	private OWLAxiomConverter converter;

	private ArrayList<String> nl_statements;
	
	private static GenerateStatements instance = null;
        
        private StringBuilder outputContent = null;
        
        private ArrayList<OutputRecord> outputRecords = null;

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
		
                //old owlapi 4
                //ToStringRenderer.getInstance().setRenderer(new DLSyntaxObjectRenderer());
		
                DLSyntaxObjectRenderer renderer =  new DLSyntaxObjectRenderer();
                ToStringRenderer.setRenderer(()->renderer);
                
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
                                        System.out.println(output);
					nl_statements.add(output);

				} catch (OWLAxiomConversionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
	}
        
        
        public void outputAsCSVFile(String outputPathFile){

            String[] headers = {"AXIOM TYPE", "AXIOM", "NATURAL LANGUAGE TRANSLATION"};
            
            CSVWriter csv_writer = new CSVWriter(headers);
            
            try {
                csv_writer.write(outputPathFile, outputRecords);
            } catch (IOException ex) {
                Logger.getLogger(GenerateStatements.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        public void outputAsExcel(String outputPathFile){
            
            String[] headers = {"AXIOM TYPE", "AXIOM", "NATURAL LANGUAGE TRANSLATION"};
            
            ExcelWriter xl_writer = new ExcelWriter(headers);

            xl_writer.write(outputPathFile, outputRecords, headers);
            
 
            
        }
        
        public void generateStatementsFromAxioms(File ontologyFile){
            outputRecords = new ArrayList<OutputRecord>();
            //outputContent = new StringBuilder();
            
            DLSyntaxObjectRenderer renderer =  new DLSyntaxObjectRenderer();
                ToStringRenderer.setRenderer(()->renderer);
                
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
                

                 
            try {
                OWLOntology ontology = man.loadOntologyFromOntologyDocument(ontologyFile);
                OWLAxiomConverter converter = new OWLAxiomConverter(ontology);
                
                for (OWLAxiom axiom : ontology.getAxioms()) {
                    if(axiom.isLogicalAxiom()){
                        String output = converter.convert(axiom);
                        if(output != null){
                            //System.out.println("**Converting: " + axiom + " (" + axiom.getAxiomType().getName() +")");
                            System.out.println("**Output: " + output +"\n");
                            
                            String output_line = axiom.getAxiomType().getName() + "\t" + axiom + "\t" + output +"\n";
                            
                            OutputRecord output_record = new OutputRecord();
                            output_record.setAxiom(axiom);
                            output_record.setAxiom_type(axiom.getAxiomType());
                            output_record.setNatural_language(output);
                            
                            outputRecords.add(output_record);
                            
                            //outputContent.append(output_line);
                            
                        }
                    }
                }
                
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(GenerateStatements.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OWLAxiomConversionException ex) {
                Logger.getLogger(GenerateStatements.class.getName()).log(Level.SEVERE, null, ex);
            }
		//OWLOntology ontology = man.loadOntologyFromOntologyDocument(new File(args[0]));
                
            
		
        }
        
        public void printNLStatements(){
            nl_statements.forEach(System.out::println);
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
		gs.init(new File("/Users/mac/HPVCO_Final_Draft_007.rdf"));
		gs.convertAxiomsToStatements();
		
	}
	

}
