package com.heuacm.service;

import java.util.List;

import com.heuacm.pojo.Annex;
import com.heuacm.pojo.Course;
import com.heuacm.pojo.Signup;

public interface CourseService {
	void courseAdd(Course course);
	void courseModify(Course course);
	void annexAdd(Annex annex);
	void annexDelete(int id);
	void signupAdd(Signup signup);
	void signupDelete(Signup signup);
	Course courseGet(int id);
	Annex annexGet(int id);
	List<Course> courselist();
	List<Annex> annexlist(int courseid);
	Signup signupGet(int courseid,int userid);
	List<Signup> signupGetByCourseId(int courseid);
}
