package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.Annex;

public interface AnnexMapper {
	public void add(Annex annex);
	public void delete(int id);
	public Annex get(int id);
	public void update(Annex annex);
	public List<Annex> listbycourseid(int courseid);
	public int count();
}
