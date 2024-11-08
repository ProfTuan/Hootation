/*
 * #%L
 * OWL2NL
 * %%
 * Copyright (C) 2015 Agile Knowledge Engineering and Semantic Web (AKSW)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package edu.uthouston.sbmi.owl2nl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.uthouston.sbmi.owl2nl.OWLAxiomConverter;
import edu.uthouston.sbmi.owl2nl.OWLClassExpressionConverter;
import edu.uthouston.sbmi.owl2nl.OWLAxiomConversionException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.ToStringRenderer;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualVisitor;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.SWRLRule;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Person;
import simplenlg.features.Tense;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.PhraseElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;


public class OWLAxiomConverter implements OWLAxiomVisitor{

	private static final Logger logger = Logger.getLogger(OWLAxiomConverter.class.getName());
	
	
	
	private NLGFactory nlgFactory;
	private Realiser realiser;

	private OWLClassExpressionConverter ceConverter;

	private OWLDataFactory df = new OWLDataFactoryImpl();

	private OWLOntology sourceOntology;

	private String nl;

	public OWLAxiomConverter(Lexicon lexicon) {
		nlgFactory = new NLGFactory(lexicon);
		realiser = new Realiser(lexicon);

		ceConverter = new OWLClassExpressionConverter(lexicon);
	}



	public OWLAxiomConverter() {
		this(Lexicon.getDefaultLexicon());
	}

	public OWLAxiomConverter(OWLOntology sourceOntology) {
		this(Lexicon.getDefaultLexicon(), sourceOntology);

	}
	public OWLAxiomConverter(Lexicon lexicon, OWLOntology sourceOntology) {
		nlgFactory = new NLGFactory(lexicon);
		realiser = new Realiser(lexicon);

		ceConverter = new OWLClassExpressionConverter(lexicon, sourceOntology);
	}
	/**
	 * Converts the OWL axiom into natural language. Only logical axioms are 
	 * supported, i.e. declaration axioms and annotation axioms are not 
	 * converted and <code>null</code> will be returned instead.
	 * @param axiom the OWL axiom
	 * @return the natural language expression
	 */
	public String convert(OWLAxiom axiom) throws OWLAxiomConversionException {
		reset();

		if (axiom.isLogicalAxiom()) {

			logger.finest("Converting " + axiom.getAxiomType().getName() + " axiom: " + axiom);
			System.out.println("**Converting: " + axiom + " (" + axiom.getAxiomType().getName() +")");
			try {
				axiom.accept(this);
				return nl;
			} catch (Exception e) {
				throw new OWLAxiomConversionException(axiom, e);
                                //System.out.println("Look at this: " + axiom);
			}
		}

		logger.finest("Conversion of non-logical axioms not supported yet! for " + axiom);
		return null;
	}

	private void reset() {
		nl = null;
	}

	/* (non-Javadoc)
	 * @see org.semanticweb.owlapi.model.OWLAxiomVisitor#visit(org.semanticweb.owlapi.model.OWLSubClassOfAxiom)
	 */
	@Override
	public void visit(OWLSubClassOfAxiom axiom) {
		// convert the subclass
		//System.out.println("Before: " + axiom.getSubClass().toString());
		OWLClassExpression subClass = axiom.getSubClass();
		NLGElement subClassElement = ceConverter.asNLGElement(subClass, true);
		logger.finest("SubClass: " + realiser.realise(subClassElement));
		//System.out.println("   subClassElement: " + subClassElement.toString());
		//System.out.println("   SubClass: " + realiser.realise(subClassElement));
		//		((PhraseElement)subClassElement).setPreModifier("every");

		// convert the superclass

		//System.out.println("Before: " + axiom.getSuperClass().toString());
		OWLClassExpression superClass = axiom.getSuperClass();
		NLGElement superClassElement = ceConverter.asNLGElement(superClass);
		logger.finest("SuperClass: " + realiser.realise(superClassElement));
		//System.out.println("   SuperClass: " + realiser.realise(superClassElement));

		SPhraseSpec clause = nlgFactory.createClause(subClassElement, "be", superClassElement);
		superClassElement.setFeature(Feature.COMPLEMENTISER, null);

		nl = realiser.realise(clause).toString();
		//System.out.println("\tAxiom: " + nl);
		logger.finest("Axiom:" + nl);		
	}

	@Override
	public void visit(OWLEquivalentClassesAxiom axiom) {
		List<OWLClassExpression> classExpressions = axiom.getClassExpressionsAsList();

		for (int i = 0; i < classExpressions.size(); i++) {
			for (int j = i + 1; j < classExpressions.size(); j++) {
				OWLSubClassOfAxiom subClassAxiom = df.getOWLSubClassOfAxiom(
						classExpressions.get(i), 
						classExpressions.get(j));
				subClassAxiom.accept(this);
			}
		}
	}

	/*
	 * We rewrite DisjointClasses(C_1,...,C_n) as SubClassOf(C_i, ObjectComplementOf(C_j)) for each subset {C_i,C_j} with i != j 
	 */
	@Override
	public void visit(OWLDisjointClassesAxiom axiom) {


		List<OWLClassExpression> classExpressions = axiom.getClassExpressionsAsList();

		for (int i = 0; i < classExpressions.size(); i++) {
			for (int j = i + 1; j < classExpressions.size(); j++) {
				OWLSubClassOfAxiom subClassAxiom = df.getOWLSubClassOfAxiom(
						classExpressions.get(i), 
						df.getOWLObjectComplementOf(classExpressions.get(j)));
				//System.out.println("printing disjoint: " + subClassAxiom);
				subClassAxiom.accept(this);
			}
		}
	}

	@Override
	public void visit(OWLDisjointUnionAxiom axiom) {
	}


	//#########################################################
	//################# object property axioms ################
	//#########################################################

	@Override
	public void visit(OWLSubObjectPropertyOfAxiom axiom) {
		//System.out.println(axiom.getSubProperty());
		//System.out.println(axiom.getSuperProperty());
		NLGElement subProp = ceConverter.asNLGElement(axiom.getSubProperty());
		NLGElement superProp = ceConverter.asNLGElement(axiom.getSuperProperty());
		
		subProp.setFeature(Feature.FORM, Form.GERUND);
		superProp.setFeature(Feature.FORM, Form.GERUND);
		SPhraseSpec sphrase =nlgFactory.createClause(subProp, "is like", superProp); 
		//System.out.println(sphrase.toString());
		nl = realiser.realiseSentence(sphrase).toString();
	}

	@Override
	public void visit(OWLEquivalentObjectPropertiesAxiom axiom) {
	}

	@Override
	public void visit(OWLDisjointObjectPropertiesAxiom axiom) {
		
		//OWLObjectPropertyExpression
		//System.out.println();
		//NLGElement stuff = ceConverter.asNLGElement(axiom.getProperties().iterator().next());
		//System.out.println(stuff.toString()); 
		//NLGElement [] props = new NLGElement[axiom.getProperties().size()];
		ArrayList <NLGElement> props = new ArrayList<NLGElement>();
		Iterator<OWLObjectPropertyExpression> oeit = axiom.getProperties().iterator();
		while(oeit.hasNext()){
			//System.out.println(oeit.next());
			props.add(ceConverter.asNLGElement(oeit.next()));
		}
		
		
		//props.get(0).setFeature(Feature.PASSIVE, true);
		//props.get(0).setFeature(Feature.PROGRESSIVE, true);
		props.get(1).setFeature(Feature.FORM,Form.GERUND);
		props.get(0).setFeature(Feature.FORM,Form.GERUND);
		SPhraseSpec sphrase = nlgFactory.createClause(props.get(0), "is not the same as", props.get(1));
		nl = realiser.realise(sphrase).toString();
		//System.out.println(realiser.realise(sphrase).toString());
		
		/*System.out.println(realiser.realise(props.get(0)).toString());
		NPPhraseSpec test = nlgFactory.createNounPhrase(realiser.realise(props.get(0)));
		test.setFeature(Feature.PROGRESSIVE, false);
		System.out.println(realiser.realise(test).toString());
		System.out.println(test.toString());
		
		sphrase = nlgFactory.createClause(props.get(0), "is not the same as", props.get(1));
		System.out.println(realiser.realise(sphrase).toString());*/
	
	}

	@Override
	public void visit(OWLObjectPropertyDomainAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLObjectPropertyRangeAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLInverseObjectPropertiesAxiom axiom) {
		
		//"The reversal of " first property "is" second property
		//System.out.println("First property: " + axiom.getFirstProperty());
		//System.out.println(axiom.getProperties().iterator().next());
		
		NLGElement firstprop = ceConverter.asNLGElement(axiom.getFirstProperty());
		firstprop.setFeature(Feature.FORM, Form.GERUND);
		NPPhraseSpec first = nlgFactory.createNounPhrase("The", "reversal");
		first.addComplement("of");
		first.addComplement(firstprop);
		
		
		NLGElement secondprop = ceConverter.asNLGElement(axiom.getSecondProperty());
		secondprop.setFeature(Feature.FORM, Form.GERUND);
		
		//System.out.println("First: " + realiser.realise(first));
		//System.out.println("Second: " + realiser.realise(secondprop));
		
		SPhraseSpec sphrase =nlgFactory.createClause(first, "be", secondprop);
		
		//System.out.println("*****: " + realiser.realiseSentence(sphrase).toString());
		nl = realiser.realiseSentence(sphrase).toString();
	}

	@Override
	public void visit(OWLFunctionalObjectPropertyAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLAsymmetricObjectPropertyAxiom axiom) {
		//if x<>P<>y but never y<>P<>z Premodfier: If every x ....but never every y

		NLGElement verbPhrase = ceConverter.asNLGElement(axiom.getProperty());
		NLGElement domainPhrase = ceConverter.getDomainExpression(axiom.getProperty());
		NLGElement rangePhrase = ceConverter.getRangeExpression(axiom.getProperty());

		if(domainPhrase != null && rangePhrase!= null){
			PhraseElement antRange = ((PhraseElement)rangePhrase);
			antRange.setPreModifier("for");

			SPhraseSpec ant= nlgFactory.createClause();
			ant.setSubject(domainPhrase);
			ant.setVerb(verbPhrase);
			ant.setObject(antRange);

			String antecedent = realiser.realise(ant).toString();

			SPhraseSpec con = nlgFactory.createClause();
			PhraseElement conRange = ((PhraseElement)rangePhrase);
			conRange.setPreModifier("every");

			PhraseElement conDomain = ((PhraseElement)domainPhrase);
			conDomain.setPreModifier("for");

			con.setSubject(rangePhrase);
			con.setVerb(verbPhrase);
			con.setObject(conDomain);
			con.addFrontModifier("but never");

			String consequent = realiser.realise(con).toString();

			nl = antecedent + ", " + consequent;
		}
		else{
			

			nl = "[some wierd error at asymetric object property axiom]";
		}

	}

	@Override
	public void visit(OWLReflexiveObjectPropertyAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}
	
	@Override
	public void visit(OWLIrreflexiveObjectPropertyAxiom axiom) {
		//System.out.println(axiom.getProperty());
		NLGElement verbPhrase = ceConverter.asNLGElement(axiom.getProperty());
		
		SPhraseSpec ant= nlgFactory.createClause();
		ant.setSubject("Nothing");
		ant.setVerbPhrase(verbPhrase);
		ant.addComplement("to itself");
		
		
		nl = realiser.realise(ant).toString();
		
	}

	@Override
	public void visit(OWLSymmetricObjectPropertyAxiom axiom) {
		//if x<>P<>y then y<>P<>z Premodfier: If every x ....then every y
		
		//System.out.println(axiom); 
		NLGElement verbPhrase = ceConverter.asNLGElement(axiom.getProperty());
		NLGElement domainPhrase = ceConverter.getDomainExpression(axiom.getProperty());
		NLGElement rangePhrase = ceConverter.getRangeExpression(axiom.getProperty());



		if(domainPhrase != null && rangePhrase!= null){
			PhraseElement antRange = ((PhraseElement)rangePhrase);
			antRange.setPreModifier("for");
			//System.out.println(realiser.realise(domainPhrase));
			//System.out.println(realiser.realise(verbPhrase));
			//System.out.println(realiser.realise(rangePhrase));
			SPhraseSpec ant= nlgFactory.createClause();
			ant.setSubject(domainPhrase);
			ant.setVerb(verbPhrase);
			ant.setObject(antRange);

			ant.addFrontModifier("if");

			//System.out.println(realiser.realise(ant));
			String antecedent = realiser.realise(ant).toString();

			SPhraseSpec con = nlgFactory.createClause();
			PhraseElement conRange = ((PhraseElement)rangePhrase);
			conRange.setPreModifier("every");

			PhraseElement conDomain = ((PhraseElement)domainPhrase);
			conDomain.setPreModifier("for");

			con.setSubject(rangePhrase);
			con.setVerb(verbPhrase);
			con.setObject(conDomain);
			con.addFrontModifier("then");
			//System.out.println(realiser.realise(con));
			String consequent = realiser.realise(con).toString();

			nl = antecedent + ", " + consequent;
		}
		else{
			SPhraseSpec ant= nlgFactory.createClause();
			ant.setSubject("for everything");
			ant.setVerb(verbPhrase);
			ant.setObject("everything");
			ant.addFrontModifier("if");

			String antecedent = realiser.realise(ant).toString();

			SPhraseSpec con = nlgFactory.createClause();
			con.setSubject("for everything");
			con.setVerb(verbPhrase);
			con.setObject("everything");
			con.addFrontModifier("then");

			String consequent = realiser.realise(con).toString();

			nl = antecedent + ", " +consequent;
		}

	}

	@Override
	public void visit(OWLTransitiveObjectPropertyAxiom axiom) {
		
		//if something X has a sibling for something Y, then something Y has a sibling for something Z, and so on
		//get domain and range
		//System.out.println(axiom.getProperty());
		
		NLGElement propertyEx = ceConverter.asNLGElement(axiom.getProperty());
		String strProperty = realiser.realise(propertyEx).toString();
		
		NLGElement domainEx;
		if((domainEx = ceConverter.getDomainExpression(axiom.getProperty())) == null){
			domainEx = nlgFactory.createNounPhrase("thing");
		}
		
		NLGElement RangeEx;
		if((RangeEx = ceConverter.getRangeExpression(axiom.getProperty())) == null){
			RangeEx = nlgFactory.createNounPhrase("thing");
		}
		
		NLGElement domainEx2;
		if((domainEx2 = ceConverter.getDomainExpression(axiom.getProperty())) == null){
			domainEx2 = nlgFactory.createNounPhrase("thing");
		}
		
		NPPhraseSpec domain = nlgFactory.createNounPhrase(domainEx);
		domain.setPreModifier("");
		domain.setPostModifier("X");
		
		NPPhraseSpec range = nlgFactory.createNounPhrase(RangeEx);
		range.setPreModifier("");
		range.setPostModifier("Y");
		
		
		NPPhraseSpec domain2 = nlgFactory.createNounPhrase(domainEx2);
		domain2.setPreModifier("");
		domain2.setPostModifier("Z");
		
		SPhraseSpec firstPhrase = nlgFactory.createClause(domain, strProperty, range);
		firstPhrase.addFrontModifier("if");
		//System.out.println("*** " + realiser.realise(firstPhrase).toString());
		
		SPhraseSpec secondPhrase = nlgFactory.createClause(range, strProperty, domain2);
		//firstPhrase.setFeature(Feature.COMPLEMENTISER, ", then");
		secondPhrase.addFrontModifier("then");
		//System.out.println("*** " + realiser.realise(secondPhrase).toString());
		
		//firstPhrase.addComplement(secondPhrase);
		firstPhrase.setPostModifier(secondPhrase);
		secondPhrase.addComplement(", and so on");
		//firstPhrase.addComplement(", and so on");
		nl = realiser.realiseSentence(firstPhrase).toString();
		//System.out.println("Final: " + testSentence);
		
		
		
	}

	

	@Override
	public void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	//#########################################################
	//################# data property axioms ##################
	//#########################################################

	@Override
	public void visit(OWLSubDataPropertyOfAxiom axiom) {
		//System.out.println(axiom.getSubProperty() + " and " + axiom.getSuperProperty());
		
		NLGElement subProp = ceConverter.asNLGElement(axiom.getSubProperty());
		NLGElement superProp = ceConverter.asNLGElement(axiom.getSuperProperty());
		
		subProp.setFeature(Feature.FORM, Form.GERUND);
		superProp.setFeature(Feature.FORM, Form.GERUND);
		
		SPhraseSpec sphrase =nlgFactory.createClause(subProp, "is like", superProp); 
		nl = realiser.realiseSentence(sphrase).toString();
		//System.out.println(realiser.realiseSentence(sphrase).toString());
		
	}

	@Override
	public void visit(OWLEquivalentDataPropertiesAxiom axiom) {
	}

	@Override
	public void visit(OWLDisjointDataPropertiesAxiom axiom) {
	}

	@Override
	public void visit(OWLDataPropertyDomainAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLDataPropertyRangeAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLFunctionalDataPropertyAxiom axiom) {
		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	//#########################################################
	//################# individual axioms #####################
	//#########################################################

	@Override
	public void visit(OWLClassAssertionAxiom axiom) {
		/*TESTING: by Tuan*/

		axiom.asOWLSubClassOfAxiom().accept(this);
	}

	@Override
	public void visit(OWLObjectPropertyAssertionAxiom axiom) {


		NLGElement subjectPhrase = ceConverter.asNLGElement(axiom.getSubject());
		NLGElement objectPhrase = ceConverter.asNLGElement(axiom.getObject(),true);
		NLGElement verbPhrase = ceConverter.asNLGElement(axiom.getProperty());

		//System.out.println(objectPhrase.getRealisation());
		//SPhraseSpec clause = nlgFactory.createClause(subjectPhrase, verbPhrase, objectPhrase);

		SPhraseSpec clause = nlgFactory.createClause();
		clause.setSubject(subjectPhrase);
		clause.setVerbPhrase(verbPhrase);
		clause.setObject(objectPhrase);

		nl = realiser.realiseSentence(clause).toString();

	}

	@Override
	public void visit(OWLDataPropertyAssertionAxiom axiom) {


		NLGElement subjectPhrase = ceConverter.asNLGElement(axiom.getSubject());
		NLGElement verbPhrase = ceConverter.asNLGElement(axiom.getProperty());
		NLGElement objectPhrase = ceConverter.asNLGElement(axiom.getObject());

		SPhraseSpec clause = nlgFactory.createClause();
		clause.setSubject(subjectPhrase);
		clause.setVerb(verbPhrase);
		clause.setObject(objectPhrase);

		nl = realiser.realiseSentence(clause).toString();
	}

	@Override
	public void visit(OWLNegativeObjectPropertyAssertionAxiom axiom) {
	}

	@Override
	public void visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
	}

	@Override
	public void visit(OWLDifferentIndividualsAxiom axiom) {


		/*for(OWLDifferentIndividualsAxiom diff : axiom.asPairwiseAxioms()){
			System.out.println(diff.);
		}*/

		StringBuilder strIndividuals = new StringBuilder();

		Iterator <OWLIndividual> it = axiom.getIndividualsAsList().iterator();
		while(it.hasNext()){


			OWLIndividual oi = it.next();

			if(!it.hasNext()){

				strIndividuals.append("and ");
				strIndividuals.append(oi.toString());
			}
			else{
				strIndividuals.append(oi.toString());
				strIndividuals.append(", ");
			}


		}

		//System.out.println(strIndividuals.toString() + " are uniquely different");
		nl = strIndividuals.toString() + " are uniquely different";
	}

	@Override
	public void visit(OWLSameIndividualAxiom axiom) {
		StringBuilder strIndividuals = new StringBuilder();

		Iterator <OWLIndividual> it = axiom.getIndividualsAsList().iterator();
		while(it.hasNext()){


			OWLIndividual oi = it.next();

			if(!it.hasNext()){

				strIndividuals.append("and ");
				strIndividuals.append(oi.toString());
			}
			else{
				strIndividuals.append(oi.toString());
				strIndividuals.append(", ");
			}


		}

		//System.out.println(strIndividuals.toString() + " are uniquely different");
		nl = strIndividuals.toString() + " are the same thing";

	}

	//#########################################################
	//################# other logical axioms ##################
	//#########################################################

	@Override
	public void visit(OWLSubPropertyChainOfAxiom axiom) {
		
		//translation: property chain ', that' property chain,... ' which is ' super property
		/*ArrayList<NLGElement> ex = new ArrayList<NLGElement>();
		for(OWLObjectPropertyExpression pe: axiom.getPropertyChain()){
			//nlgFactory.createClause();
			ex.add(ceConverter.asNLGElement(pe));
		}
		
		ex.get(0).setFeature(Feature.FORM, Form.GERUND);
		for(NLGElement e: ex){
			System.out.println(realiser.realise(e).toString());
			
		}
		*
		*/
		
		NLGElement chain = ceConverter.asNLGElement(axiom.getPropertyChain());
		
		
		
		
		//axiom.getPropertyChain().g
		
		
		//System.out.println(axiom.getSuperProperty());
		NLGElement superProp = ceConverter.asNLGElement(axiom.getSuperProperty());
		superProp.setFeature(Feature.FORM, Form.GERUND);
		//superProp.setFeature(Feature.PASSIVE,true);
		//superProp.setFeature(Feature.PROGRESSIVE, true);
		SPhraseSpec sphrase =nlgFactory.createClause(chain, "is equated to", superProp); 
		//System.out.println(realiser.realiseSentence(sphrase).toString());
		//System.out.println(superProp.toString());
		//System.out.println(axiom.getPropertyChain().iterator().next());
		
		nl = realiser.realiseSentence(sphrase).toString();
		
	}

	@Override
	public void visit(OWLHasKeyAxiom axiom) {
	}

	@Override
	public void visit(OWLDatatypeDefinitionAxiom axiom) {
	}

	@Override
	public void visit(SWRLRule axiom) {
            
            
            
	}

	//#########################################################
	//################# non-logical axioms ####################
	//#########################################################

	@Override
	public void visit(OWLAnnotationAssertionAxiom axiom) {
	}

	@Override
	public void visit(OWLSubAnnotationPropertyOfAxiom axiom) {
	}

	@Override
	public void visit(OWLAnnotationPropertyDomainAxiom axiom) {
	}

	@Override
	public void visit(OWLAnnotationPropertyRangeAxiom axiom) {
	}

	@Override
	public void visit(OWLDeclarationAxiom axiom) {
	}

	public static void main(String[] args) throws Exception {


                //OLD OWL API 4
		//ToStringRenderer.getInstance().setRenderer(new DLSyntaxObjectRenderer());
		
                DLSyntaxObjectRenderer renderer =  new DLSyntaxObjectRenderer();
                ToStringRenderer.setRenderer(()->renderer);
                
                logger.setLevel(Level.FINEST);

		String ontologyURL = "";
		//ontologyURL = "http://rpc295.cs.man.ac.uk:8080/repository/download?ontology=http://reliant.teknowledge.com/DAML/Transportation.owl&format=RDF/XML";
		ontologyURL = "/Users/mac/Downloads/ADMO-alpha.owl";
		//ontologyURL = "/Users/mfamith/Documents/neon/TestOWLNG/assignment-3.owl";
		//ontologyURL = "/Users/mfamith/Desktop/geography.owl";
		//ontologyURL = "/Users/mac/foaf/foafbenefits-minimal.owl";
		//ontologyURL = "/Users/mfamith/Desktop/ico_merged.owl";
		//ontologyURL = "/Users/mfamith/Desktop/pizza-rdf.owl";
		//ontologyURL = "/Users/mfamith/Desktop/VISO-Working-Folder/VISO-HPV-v1.owl";
		//ontologyURL = "/Users/mfamith/Desktop/pizza.owl";
		//ontologyURL = "/Users/mfamith/Downloads/vico_merged.owl";
		//ontologyURL = "/Users/mfamith/Desktop/swo_merged.owl";
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = man.loadOntologyFromOntologyDocument(new File(ontologyURL));
		//OWLOntology ontology = man.loadOntology(IRI.create(ontologyURL));

		OWLAxiomConverter converter = new OWLAxiomConverter(ontology);
		for (OWLAxiom axiom : ontology.getAxioms()) {

			if(axiom.isLogicalAxiom()){
				//System.out.println(axiom.getAnnotatedAxiom(null));
				//|| (axiom.getAxiomType().getName()== "ClassAssertion")
				/*if((axiom.getAxiomType().getName()=="SymmetricObjectProperty")
						//|| (axiom.getAxiomType().getName()== "SameIndividuals")
						){*/
					
					String output = converter.convert(axiom);
					if((output != null)){
						//if(axiom.getAxiomType().getName() =="InverseObjectProperties"){
							
							System.out.println("**Output: " + output +"\n");
						//}
						
					}
					
				//}


			}

		}
		
		System.out.println("Done");
	}


}
