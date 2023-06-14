package com.kh.spring.demo.model.vo;

import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dev {
	private int no;
	private String name;
	private int career;
	private String email;
	private String gender;
	private String[] lang;
	private LocalDateTime createDate;
	
}
