package com.heuacm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.NewsMapper;
import com.heuacm.pojo.News;
import com.heuacm.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsMapper newsMapper;
	
	public void newsAdd(News news) {
		news.setDt(new Date());
		newsMapper.add(news);
	}

	public List<News> newsListCan(int auth) {
		return newsMapper.listCan(auth);
	}

	public List<News> newsListAll() {
		return newsMapper.list();
	}

	public News newsGet(int id) {
		return newsMapper.get(id);
	}

	public void newsUpdate(News news) {
		news.setDt(new Date());
		newsMapper.update(news);
	}

}
