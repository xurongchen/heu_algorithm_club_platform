package com.heuacm.mapper;

import org.apache.ibatis.annotations.Param;

import com.heuacm.pojo.ChatVote;

public interface ChatVoteMapper {
	public ChatVote get(@Param("userid") int userid,@Param("val") String val);
	public void add(ChatVote chatVote);
}
