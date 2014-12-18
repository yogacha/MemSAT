/******************************************************************************
 * Copyright (c) 2009 - 2015 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
/**
 * 
 */
package com.ibm.wala.memsat.translation.concurrent;

import java.util.Collections;
import java.util.Set;

import kodkod.ast.Formula;
import kodkod.instance.Bounds;

import com.ibm.wala.memsat.concurrent.Justification;
import com.ibm.wala.memsat.representation.ExpressionFactory;
import com.ibm.wala.memsat.translation.Translation;
import com.ibm.wala.memsat.translation.TranslationWarning;

/**
 * Stores the translation of a graph of concurrently executing Wala methods to Kodkod data structures.
 * 
 * @specfield factory: ExpressionFactory // expression factory used for translation
 * @specfield formula: Formula // the formula generated by the translator 
 * @specfield bounds: Bounds // bounds generated by the translator
 * @specfield context: Justification // an justification object with translation of the main execution (and any speculative executions)
 * @specfield warnigns: set TranslationWarning // translation warnings
 * @author Emina Torlak
 */
public final class ConcurrentTranslation implements Translation<Justification> {
	private final ExpressionFactory factory;
	private final Formula formula;
	private final Justification context;
	private final Set<TranslationWarning> warnings;
	
	/**
	 * Constructs a translation from the given objects.
	 */
	ConcurrentTranslation(ExpressionFactory factory, Formula formula, Justification justification, Set<TranslationWarning> warnings) {
		this.factory=factory;
		this.formula=formula;
		this.context=justification;
		this.warnings = Collections.unmodifiableSet(warnings);
	}
	
	/**
	 * Returns the factory used for allocating relations to 
	 * unknown values (initial heap state, entry method arguments, etc.)
	 * and constants.
	 * @return this.factory
	 */
	public  ExpressionFactory factory() { return factory; }
	/**
	 * Returns this.bounds.
	 * @return this.bounds
	 */
	public  Bounds bounds() { return context.bounds(); }
	/**
	 * Returns the formula constraining the initial and final 
	 * states of the translated program's heap.
	 * @return this.formula
	 */
	public  Formula formula() { return formula; }
	
	/**
	 * Returns the set of all warnings generated during translation.
	 * @return this.warnings
	 */
	public Set<TranslationWarning> warnings() { return warnings; }
	
	/**
	 * Returns the translation context for this.formula and this.bounds.  The context
	 * contains additional information about the results of the translation]
	 * process, and it depends on whether this translation object was generated
	 * by a concurrent or a sequential translator. 
	 * @return translation context for this method.
	 */
	public Justification context() { return context; }

}