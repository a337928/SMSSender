package com.arthur.sender;

import com.arthur.common.cache.LocalCache;

/**
 * Created by wangtao on 17/4/16.
 */
public class Sender {
	Sender(){
		LocalCache.LOCAL_CACHE.getCache();
	}
}
