package com.techurate.api.config;

import org.aeonbits.owner.ConfigCache;

public  class ConfigManage {

	private ConfigManage() {}

	public static ApiConfig getConfig() {
		return ConfigCache.getOrCreate(ApiConfig.class);
	}

}
