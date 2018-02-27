package com.heuacm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.ConfigMapper;
import com.heuacm.pojo.Config;
import com.heuacm.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	ConfigMapper configMapper;
	
	public void configAdd(Config config) {
		configMapper.add(config);
	}

	public void configDelete(String configkey) {
		configMapper.delete(configkey);
	}

	public void configUpdate(Config config) {
		configMapper.update(config);
	}

	public Config configGet(String configkey) {
		return configMapper.get(configkey);
	}

}
