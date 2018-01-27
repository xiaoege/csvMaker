package com.chen.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chen.model.User;

public interface UserMapper {

	void runSql(@Param("list")ArrayList list);
	

	

}
