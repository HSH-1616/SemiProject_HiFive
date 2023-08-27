package com.semi.sc.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.sc.model.dto.Board;
import com.semi.sc.model.dto.BoardFile;
import com.semi.sc.service.BoardService;


@WebServlet("/service/boardInsertEnd.do")
public class BoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardInsertEndServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//multipart/form-data 형식의 요청인지 확인
		if (!(ServletFileUpload.isMultipartContent(request))) {
			// 해당 타입이 맞으면 true, 아니면 false를 반환
			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요.");
			request.setAttribute("loc", "/"); //해당 페이지로 다시 돌아감
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return; // multipart타입이 아니면 리턴 처리
		}
		String path=request.getServletContext().getRealPath("/upload/board");
		int maxSize=1024*1024*200; //200MB
		String encode="UTF-8";
		DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, encode, dfr);
		
		//게시판 저장
		Board b=Board.builder()
				.boardWriter(mr.getParameter("login"))
				.noticeYn(mr.getParameter("titleCategory").charAt(0))
				.boardCategory(mr.getParameter("QACategory"))
				.boardTitle(mr.getParameter("boardTitle"))
				.boardContent(mr.getParameter("boardContent").replaceAll("\n", "<br>"))
				.build();
		
		//파일
		Enumeration<String> files = mr.getFileNames();
		List<String> filesNames = new ArrayList();
		while (files.hasMoreElements()) { //파일명 저장
			String fileName = files.nextElement();
			filesNames.add(mr.getFilesystemName(fileName));
		}
		
		int result=new BoardService().insertBoard(b, filesNames);

		response.setContentType("application/json;charset=utf-8");
		//글과 파일이 저장됐으면 true
		new Gson().toJson(result>0?true:false,response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
