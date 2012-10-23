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

package org.semanticweb.owlapi.binaryowl.change.serializer;

import org.semanticweb.owlapi.binaryowl.BinaryOWLParseException;
import org.semanticweb.owlapi.binaryowl.lookup.LookupTable;
import org.semanticweb.owlapi.binaryowl.owlobject.OWLAnnotationSerializer;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 27/04/2012
 */
public class AddOntologyAnnotationDataSerializer extends OntologyChangeDataSerializer<AddOntologyAnnotationData> {

    @Override
    public void write(AddOntologyAnnotationData recordData, LookupTable lookupTable, DataOutput dataOutput) throws IOException {
        OWLAnnotationSerializer serializer = new OWLAnnotationSerializer();
        serializer.write(recordData.getAnnotation(), lookupTable, dataOutput);
    }

    @Override
    public AddOntologyAnnotationData read(DataInput dataInput, LookupTable lookupTable, OWLDataFactory dataFactory) throws IOException, BinaryOWLParseException {
        OWLAnnotationSerializer serializer = new OWLAnnotationSerializer();
        OWLAnnotation annotation = serializer.read(lookupTable, dataInput, dataFactory);
        return new AddOntologyAnnotationData(annotation);
    }
}