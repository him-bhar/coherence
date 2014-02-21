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

package com.himanshu.cache.extend.client;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;
import com.tangosol.net.NamedCache;

public class TestClient {
	
	static Logger log = LoggerFactory.getLogger(TestClient.class);
	
	public static void main(String[] asArgs) throws Throwable {
		NamedCache cache = CacheFactory.getCache("abc-dist-extend");
		Integer IValue = (Integer) cache.get("key");
		log.info("Value is "+IValue);

		InvocationService service = (InvocationService) CacheFactory
				.getConfigurableCacheFactory().ensureService(
						"ExtendTcpInvocationService");
		log.info(service.getCluster().getMemberSet().toString());
		Enumeration enume = service.getCluster().getServiceNames();
		while (enume.hasMoreElements()) {
			Object obj = enume.nextElement();
			log.info("Service name: " + obj.toString());
		}
	}

}