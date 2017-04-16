package com.arthur.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 程序本地缓存内部使用concurrentMap实现.
 * 使用enum实现单列
 * Created by wangtao on 17/4/16.
 */
public enum LocalCache {

	LOCAL_CACHE;

	private Map<Object,Object> localCache = null;

	private LocalCache(){
		this.localCache = new ConcurrentHashMap<Object,Object>();
	}

	public Map<Object,Object> getCache (){
		return this.localCache;
	}

	public void  put(Object key,Object value) {
		this.localCache.put(key,value);
	}

	public boolean containsKey(Object key){
		return  this.localCache.containsKey(key);
	}

	public void  remove(Object key){
		this.remove(key);
	}

}
