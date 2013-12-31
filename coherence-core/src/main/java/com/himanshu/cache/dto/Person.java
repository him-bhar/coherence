/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.himanshu.cache.dto;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Person implements PortableObject {
	Logger log = LoggerFactory.getLogger(Person.class);
	
	private enum fieldIndex {
		name(0), age(1);
		int fieldId = 0;
		private fieldIndex(int fieldId) {
			this.fieldId = fieldId;
		}
		public int getFieldId() {
			return this.fieldId;
		}
	}
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public void readExternal(PofReader reader) throws IOException {
		log.debug("Inside readExternal");
		name = reader.readString(fieldIndex.name.getFieldId());
		age = reader.readInt(fieldIndex.age.getFieldId());
	}
	
	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		log.debug("Inside writeExternal");
		writer.writeString(fieldIndex.name.getFieldId(), name);
		writer.writeInt(fieldIndex.age.getFieldId(), age);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
