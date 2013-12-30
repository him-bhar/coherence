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
package com.himanshu.coherence;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.himanshu.cache.dto.Person;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCacheTest {
	Logger log = LoggerFactory.getLogger(CoherenceCacheTest.class);
	
	private String key="testKey";
	private String value="testValue";
	
	@Test
	public void cachePutTest() {
		log.debug("Starting cachePutTest");
		NamedCache cache = CacheFactory.getCache("test");
		cache.put(key, value);
		Person p = new Person();
		p.setName("Himanshu");
		p.setAge(10);
		cache.put("person", p);
		
		cacheGetTest();
	}
	
	public void cacheGetTest() {
		log.debug("Starting cacheGetTest");
		NamedCache cache = CacheFactory.getCache("test");
		Assert.assertTrue("Actual: "+(String)cache.get(key)+". Original: "+value, value.equalsIgnoreCase((String)cache.get(key)));
		Person p = (Person)cache.get("person");
		log.debug(p.getName());
	}
}
