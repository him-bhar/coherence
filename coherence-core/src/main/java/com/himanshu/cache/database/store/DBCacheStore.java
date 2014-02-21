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
package com.himanshu.cache.database.store;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.cache.CacheStore;

public class DBCacheStore implements CacheStore {
	
	Logger log = LoggerFactory.getLogger(DBCacheStore.class);
	
	private String cacheName;
	
	public DBCacheStore (String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public Object load(Object arg0) {
		log.debug("For cache : "+cacheName+" key: "+arg0);
		return null;
	}

	@Override
	public Map loadAll(Collection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void erase(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eraseAll(Collection arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store(Object arg0, Object arg1) {
		log.debug("For cache : "+cacheName+" key: "+arg0+" value: "+arg1);
	}

	@Override
	public void storeAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}

}
