package com.heuacm.service;

import java.util.List;

import com.heuacm.pojo.News;

public interface NewsService {
	void newsAdd(News news);
	void newsUpdate(News news);
	News newsGet(int id);
	List<News> newsListCan(int auth);
	List<News> newsListAll();
	
}
