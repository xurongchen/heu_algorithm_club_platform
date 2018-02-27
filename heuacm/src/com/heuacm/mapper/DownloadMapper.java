package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.Download;

public interface DownloadMapper {
	public void add(Download download);
	public void update(Download download);
	public void delete(int id);
	public Download get(int id);
	public Download getByFilename(String filename);
	public List<Download> listall();
}
