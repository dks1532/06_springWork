package com.kh.spring.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@NonNull	// NonNull을 사용하면 null이 아니어야 하는 데이터만 따로 생성자가 생긴다. 그이외에는 자유롭게
	private int boardNo;
	@NonNull
	private String boardTitle;
	@NonNull
	private String boardWriter;
	@NonNull
	private String content;
	private String originalFilename;
	private String changeFilename;
	private int count;
	private Date createDate;
}









