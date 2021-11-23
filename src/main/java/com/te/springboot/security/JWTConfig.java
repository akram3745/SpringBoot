package com.te.springboot.security;

import org.springframework.core.env.Environment;

@SuppressWarnings("unused")
public class JWTConfig {
	
	private Environment environment;
	private long access_token;
	private long refresh_token;
	
	public long getAccess_token() {
		return Long.parseLong(environment.getProperty("access.token"));
	}
	
	public long getRefresh_token() {
		return Long.parseLong(environment.getProperty("refresh.token"));
	}

}
