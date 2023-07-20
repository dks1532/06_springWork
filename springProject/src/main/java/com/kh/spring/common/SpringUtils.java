package com.kh.spring.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SpringUtils {
	/*
	 * 파일명 : yyyyMMdd_HHmmssSSS_123.jpg
	 */
	public static String changeMultipartFile(MultipartFile upFile) {
		String oFilename = upFile.getOriginalFilename();
		int beginIndex = oFilename.lastIndexOf(".");
		String ext = "";
		
		if(beginIndex > -1) {
			ext = oFilename.substring(beginIndex);		// .jpg
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
		DecimalFormat df = new DecimalFormat("000");
		
		return sdf.format(new Date()) + df.format(Math.random() * 1000) + ext;
	}
}