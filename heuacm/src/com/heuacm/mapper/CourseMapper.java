package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.Course;


public interface CourseMapper {
	public void add(Course course);
	public void delete(int id);
	public Course get(int id);
	public void update(Course course);
	public List<Course> listall();
	public int count();
}
