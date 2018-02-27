package com.heuacm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.AnnexMapper;
import com.heuacm.mapper.CourseMapper;
import com.heuacm.mapper.SignupMapper;
import com.heuacm.pojo.Annex;
import com.heuacm.pojo.Course;
import com.heuacm.pojo.Signup;
import com.heuacm.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	AnnexMapper annexMapper;
	@Autowired
	SignupMapper signupMapper;
	
	public void courseAdd(Course course) {
		courseMapper.add(course);
	}
	
	public void courseModify(Course course) {
		courseMapper.update(course);		
	}
	
	public List<Course> courselist() {
		return courseMapper.listall();
	}

	public Course courseGet(int id) {
		return courseMapper.get(id);
	}

	public List<Annex> annexlist(int courseid) {
		return annexMapper.listbycourseid(courseid);
	}

	public Signup signupGet(int courseid, int userid) {
		return signupMapper.get(courseid, userid);
	}

	public List<Signup> signupGetByCourseId(int courseid) {
		return signupMapper.getbycourseid(courseid);
	}

	public void annexAdd(Annex annex) {
		annexMapper.add(annex);
	}

	public void annexDelete(int id) {
		annexMapper.delete(id);
		
	}

	public void signupAdd(Signup signup) {
		signupMapper.add(signup);		
	}

	public void signupDelete(Signup signup) {
		signupMapper.delete(signup);
	}

	public Annex annexGet(int id) {
		return annexMapper.get(id);
	}
}
