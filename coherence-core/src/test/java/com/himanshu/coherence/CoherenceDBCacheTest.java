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

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.himanshu.cache.dto.Person;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.filter.LikeFilter;
import com.tangosol.util.processor.AbstractProcessor;

public class CoherenceDBCacheTest {
	Logger log = LoggerFactory.getLogger(CoherenceDBCacheTest.class);
	
	private String key="testKey";
	private String value="testValue";
	
	@BeforeClass
	public static void setupData() {
		NamedCache cache = CacheFactory.getCache("com.himanshu.db.test");
		//cache.put(key, value);
		Person p = new Person();
		p.setName("Himanshu");
		p.setAge(10);
		cache.put("person", p);
		
		Person p1 = new Person();
		p1.setName("Bittoo");
		p1.setAge(20);
		cache.put("person2", p1);
	}
	
	@Test
	public void cacheGetTest() {
		log.debug("Starting cacheGetTest");
		NamedCache cache = CacheFactory.getCache("com.himanshu.db.test");
		//Assert.assertTrue("Actual: "+(String)cache.get(key)+". Original: "+value, value.equalsIgnoreCase((String)cache.get(key)));
		Person p = (Person)cache.get("person");
		log.debug(p.toString());
	}
	
	@Test
	public void cacheQueryTest() {
		log.debug("Starting cacheQueryTest");
		LikeFilter filter = new LikeFilter("getName", "%mans%");
		NamedCache cache = CacheFactory.getCache("com.himanshu.db.test");
		log.debug(cache.keySet().toString());
		log.debug(cache.getAll(cache.keySet(filter)).toString());
		Assert.assertTrue("Filter execution failed", cache.keySet(filter).contains("person"));
	}
	
	@Test
	public void cacheNoKeyExistsGetOp() {
		log.debug("Starting cacheNoKeyExistsGetOp");
		NamedCache cache = CacheFactory.getCache("com.himanshu.db.test");
		Assert.assertNull("Finding for a non-existent key failed", cache.get("dummy"));
	}
	
	@Test
	public void cacheEntryProcessorTest() {
		log.debug("Starting cacheEntryProcessorTest");
		LikeFilter filter = new LikeFilter("getName", "%mans%");
		NamedCache cache = CacheFactory.getCache("com.himanshu.db.test");
		Map<String, String> map = (Map<String, String>)cache.invokeAll(filter, new AbstractProcessor() {
			
			@Override
			public Object process(Entry arg0) {
				log.debug("Key: "+arg0.getKey());
				log.debug("Value: "+arg0.getValue());
				return ((Person)arg0.getValue()).getName();
			}
		});
		Assert.assertTrue("Invocation failed", map.get("person").equalsIgnoreCase("Himanshu"));
	}
}
