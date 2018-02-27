package com.heuacm.service;

import com.heuacm.pojo.Config;

public interface ConfigService {
	void configAdd(Config config);
	void configDelete(String configkey);
	void configUpdate(Config config);
	Config configGet(String configkey);
}
