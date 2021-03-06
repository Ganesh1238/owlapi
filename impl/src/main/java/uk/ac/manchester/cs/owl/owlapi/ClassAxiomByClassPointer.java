/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiomVisitorEx;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;

@SuppressWarnings("javadoc")
public class ClassAxiomByClassPointer extends MapPointer<OWLClass, OWLClassAxiom> {

    private static final long serialVersionUID = 30406L;

    public ClassAxiomByClassPointer(
            AxiomType<?> t, OWLAxiomVisitorEx<?> v, boolean initialized, Internals i) {
        super(t, v, initialized, i);
    }

    @Override
    public void init() {
        if (isInitialized()) {
            return;
        }
        super.init();
        // special case: this map needs other maps to be initialized first
        for (OWLClass c : i.getKeyset(i.getEquivalentClassesAxiomsByClass())) {
            for (OWLClassAxiom ax : i.getValues(
                    i.getEquivalentClassesAxiomsByClass(), c)) {
                put(c, ax);
            }
        }
        for (OWLClass c : i.getKeyset(i.getSubClassAxiomsByLHS())) {
            for (OWLClassAxiom ax : i.getValues(i.getSubClassAxiomsByLHS(), c)) {
                put(c, ax);
            }
        }
        for (OWLClass c : i.getKeyset(i.getDisjointClassesAxiomsByClass())) {
            for (OWLClassAxiom ax : i.getValues(
                    i.getDisjointClassesAxiomsByClass(), c)) {
                put(c, ax);
            }
        }
        for (OWLClass c : i.getKeyset(i.getDisjointUnionAxiomsByClass())) {
            for (OWLClassAxiom ax : i.getValues(
                    i.getDisjointUnionAxiomsByClass(), c)) {
                put(c, ax);
            }
        }
    }
}