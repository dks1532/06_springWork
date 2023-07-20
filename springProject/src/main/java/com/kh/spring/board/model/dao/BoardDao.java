package com.kh.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;

// dao는 interface만 있어도 mapper가 있으므로 따로 implement로 구현 안해줘도 됨
@Mapper
public interface BoardDao {
	
	int selectTotalRecord();

	List<Board> selectBoardList(RowBounds rowBounds);

	int insertBoard(Board board);

	int updateCountBoard(int boardNo);
	
	Board selectOneBoard(int boardNo);

}
