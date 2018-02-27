package com.heuacm.mapper;

import com.heuacm.pojo.Config;

public interface ConfigMapper {
	public void add(Config config);
	public void delete(String configkey);
	public void update(Config config);
	Config get(String configkey);
}
