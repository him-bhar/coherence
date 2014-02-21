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
package com.himanshu.cache.server;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.DefaultCacheServer;

public class CacheServer {
	
	private AtomicBoolean keepRunning = new AtomicBoolean(true);
	
	Logger log = LoggerFactory.getLogger(CacheServer.class);
	public Object obj = new Object();
	
	public void startServer() {
		log.info("Starting cache server");
		DefaultCacheServer.start();
		while (keepRunning.get() == true) {
			
		}
	}
	
	public static void main(String[] args) {
		CacheServer server = new CacheServer();
		server.addShutdownHook();
		server.startServer();
	}

	private void addShutdownHook() {
		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	log.info("Shutting down cache server");
		        keepRunning.getAndSet(false);
		        try {
					mainThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	

}
