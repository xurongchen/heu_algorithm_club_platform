package com.heuacm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heuacm.pojo.Signup;

public interface SignupMapper {
	public Signup get(@Param("courseid") int courseid,@Param("userid") int userid);
	public List<Signup> getbycourseid(int courseid);
	public void add(Signup signup);
	public void delete(Signup signup);
}
