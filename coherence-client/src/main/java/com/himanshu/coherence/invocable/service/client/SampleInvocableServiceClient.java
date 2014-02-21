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
package com.himanshu.coherence.invocable.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.himanshu.coherence.service.SampleInvocableService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;

public class SampleInvocableServiceClient {
	
	static Logger log = LoggerFactory.getLogger(SampleInvocableService.class);
	
	public static void main(String[] args) {
		log.info("Starting sample invocable client");
		InvocationService service = (InvocationService) CacheFactory
				.getConfigurableCacheFactory().ensureService(
						"ExtendTcpInvocationService");
		service.query(new SampleInvocableService(), null);
	}

}
