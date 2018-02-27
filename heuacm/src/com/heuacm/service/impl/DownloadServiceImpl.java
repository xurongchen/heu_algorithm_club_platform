package com.heuacm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.DownloadMapper;
import com.heuacm.pojo.Download;
import com.heuacm.service.DownloadService;
@Service
public class DownloadServiceImpl implements DownloadService{
	@Autowired
	DownloadMapper downloadMapper;

	public void downloadAdd(Download download) {
		downloadMapper.add(download);		
	}

	public void downloadDelete(int id) {
		downloadMapper.delete(id);		
	}

	public void downloadUpdate(Download download) {
		downloadMapper.update(download);
	}

	public Download downloadGet(int id) {
		return downloadMapper.get(id);
	}

	public List<Download> downloadlist() {
		return downloadMapper.listall();
	}

	public Download downloadGetByFilename(String filename) {
		return downloadMapper.getByFilename(filename);
	}
}
