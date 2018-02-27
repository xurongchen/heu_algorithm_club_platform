package com.heuacm.service;

import java.util.List;

import com.heuacm.pojo.Download;

public interface DownloadService {
	void downloadAdd(Download download);
	void downloadDelete(int id);
	void downloadUpdate(Download download);
	Download downloadGet(int id);
	Download downloadGetByFilename(String filename);
	List<Download> downloadlist();
}
