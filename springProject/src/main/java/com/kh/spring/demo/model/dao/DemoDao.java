package com.kh.spring.demo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.demo.model.vo.Dev;

@Mapper
public interface DemoDao {

	int insertDev(Dev dev);

	List<Dev> selectDevList();

	Dev selectOneDev(int no);

	int updateDev(Dev dev);

}
