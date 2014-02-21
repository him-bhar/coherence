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
package com.himanshu.coherence.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;

public class SampleInvocableService extends AbstractInvocable implements PortableObject {

	Logger log = LoggerFactory.getLogger(SampleInvocableService.class);
	
	@Override
	public void run() {
		log.info("Demonstrating Invocable service");
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
