package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.SpringUtils;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/boardList.bo")
	public void boardList(@RequestParam(defaultValue="1") int nowPage, Model model) {
							// 값이 안넘어왔으면 default값으로 1페이지 지정
		int totalRecord = boardService.selectTotalRecord();
		int limit = 5;	// 한페이지당 몇개씩 보여줄 것인지(mybatis에 페이징 처리하는부분에 원래 있음)
		int offset = (nowPage - 1) * limit; 
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		PageInfo pi = Pagination.getPageInfo(totalRecord, nowPage, limit, 3);
		
		List<Board> boardList = boardService.selectBoardList(rowBounds);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pi", pi);
	}

	@GetMapping("/boardForm.bo")
	public void boardForm() {}
	
	@PostMapping("/boardEnroll.bo")
	public String boardEnroll(Board board, @RequestParam MultipartFile upFile) {
		String saveDirectory = application.getRealPath("/resources/upload/board");
		System.out.println(saveDirectory);
		if(upFile.getSize() > 0) {
			String originalFilename = upFile.getOriginalFilename();
			String changeFilename = SpringUtils.changeMultipartFile(upFile);
			
			File destFile = new File(saveDirectory, changeFilename);
			
			try {
				upFile.transferTo(destFile);		// 실제로 저장
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			board.setOriginalFilename(originalFilename);
			board.setChangeFilename(changeFilename);
		}
		int result = boardService.insertBoard(board);
		return "redirect:/board/boardList.bo";
	}
	
	@GetMapping("/boardDetail.bo")
	public void boardDetail(@RequestParam int boardNo, Model model) {
		int result = boardService.updateCountBoard(boardNo);
		
		Board board = boardService.selectOneBoard(boardNo);
		
		model.addAttribute("board", board);
	}
	
	/*
	 * Resource 인터페이스
	 * - UriResource : 인터넷상의 파일
	 * - ClassPathResource : classpath의 파일
	 * - FileSystemResource : 서버컴퓨터의 파일
	 * - ServletContextResource : 웹루트디렉토리의 파일
	 * - ByteArrayResource : 이진데이터
	 *   ...
	 * 
	 * @ResponseBody
	 *   : 핸들러의 반환객체를 응답메시지 바디에 직접출력
	 */
	@ResponseBody
	@GetMapping("/fileDownload.bo")
	public Resource fileDownload(@RequestParam int boardNo, HttpServletResponse response) {
		Board board = boardService.selectOneBoard(boardNo);
		
		String oFilename = board.getOriginalFilename();
		String cFilename = board.getChangeFilename();
		
		// 한글깨짐방지
		try {
			oFilename = new String(oFilename.getBytes("utf-8"), "iso-8859-1");		//톰캣기본인코딩 변환처리
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		
		String saveDirectory = application.getRealPath("/resources/upload/board");
		File downFile = new File(saveDirectory, cFilename);
		
		// FileSystemResource에서 파일을 받을 때 - file:경로사용
		String location = "file:" + downFile;		// downfile절대경로
		Resource resource = resourceLoader.getResource(location);
		
		response.setContentType("application/octet-stream; charset=utf-8");	// 네트워크로 파일을 보낼때는 이진수로 바꾸어서 보냄
		response.addHeader("Content-Disposition", "attachment; filename=" + oFilename);
		return resource;
	}
}










