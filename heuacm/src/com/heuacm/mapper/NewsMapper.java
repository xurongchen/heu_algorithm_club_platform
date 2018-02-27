package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.News;

public interface NewsMapper {
	public void add(News news);
	public void delete(int id);
	public News get(int id);
	public void update(News news);
	public List<News> list();
	public List<News> listCan(int auth);
	public int count();
}
