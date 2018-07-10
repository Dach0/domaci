package com.logate.lacademy.security.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.logate.lacademy.config.MicroserviceConfiguration;

@Description(value = "Authentication Component.")
@Component(value = "authComponent")
public class AuthComponent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthComponent.class);
	
	
	
	@Value("${microservice.key}")
	private String key;
	
	@Autowired
	private Environment environment; 
	
	@Autowired
	private MicroserviceConfiguration microConf;
	
	/**
	 * Method for checking permission
	 * 
	 * @return boolean value (has permission or not)
	 */
	public boolean hasPermission() 
	{
		return false;
	}
	
	/**
	 * Method for checking Authorization header value
	 * 
	 * @param authHeaderValue
	 * @return
	 */
	public boolean hasHeaderPermission(String authHeaderValue) 
	{
		LOGGER.info("Header value: {}", authHeaderValue);
		if (authHeaderValue.trim().contentEquals(key)) {
			return true;
		}
		return false;
	}

}
