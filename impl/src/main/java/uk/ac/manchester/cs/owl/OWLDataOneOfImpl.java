package uk.ac.manchester.cs.owl;

import org.semanticweb.owl.model.*;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
/*
 * Copyright (C) 2006, University of Manchester
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 26-Oct-2006<br><br>
 */
public class OWLDataOneOfImpl extends OWLObjectImpl implements OWLDataOneOf {

    private Set<OWLLiteral> values;


    public OWLDataOneOfImpl(OWLDataFactory dataFactory, Set<? extends OWLLiteral> values) {
        super(dataFactory);
        this.values = new TreeSet<OWLLiteral>(values);
    }


    public Set<OWLLiteral> getValues() {
        return Collections.unmodifiableSet(values);
    }


    public boolean isDataType() {
        return false;
    }


    public boolean isTopDataType() {
        return false;
    }


    public OWLDatatype asOWLDataType() {
        throw new OWLRuntimeException("Not a data type!");
    }


    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof OWLDataOneOf)) {
                return false;
            }
            return ((OWLDataOneOf) obj).getValues().equals(values);
        }
        return false;
    }


    public void accept(OWLDataVisitor visitor) {
        visitor.visit(this);
    }


    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }

    public <O> O accept(OWLDataVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    protected int compareObjectOfSameType(OWLObject object) {
        return compareSets(values, ((OWLDataOneOf) object).getValues());
    }
}