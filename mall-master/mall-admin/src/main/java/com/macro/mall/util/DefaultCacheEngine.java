package com.macro.mall.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DefaultCacheEngine implements CacheEngine {
	
	private static final Logger logger = Logger.getLogger(DefaultCacheEngine.class);
	protected Map cache = new HashMap();

	
	public DefaultCacheEngine(){
		this.init();
		
	}

	public void add(String key, Object value){
		this.cache.put(key, value);
	}
	
	public void add(String fqn, String key, Object value){
		Map m = (Map)this.cache.get(fqn);
		if (m == null) {
			m = new HashMap();
		}

		m.put(key, value);
		this.cache.put(fqn, m);
	}
	
	public Object get(String fqn, String key){
		Map m = (Map)this.cache.get(fqn);
		if (m == null) {
			return null;
		}
		
		return m.get(key);
	}
	
	public Object get(String fqn){
		return this.cache.get(fqn);
	}
	

	
	public Collection getValues(String fqn){
		Map m = (Map)this.cache.get(fqn);
		if (m == null) {
			return new ArrayList();
		}
		
		return m.values();
	}
	


	public void init(){
		logger.info("init cache");
		this.cache = new HashMap();
	}
	


	public void stop() {
		this.cache.clear();
	}
	

	public void remove(String fqn, String key){
		Map m = (Map)this.cache.get(fqn);
		if (m != null) {
			m.remove(key);
		}
	}

	public void remove(String fqn){
		this.cache.remove(fqn);
	}

}
