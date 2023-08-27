package com.semi.sc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sc.model.dto.Board;
import com.semi.sc.service.BoardService;

@WebServlet("/service/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListServlet() {

	}

	// 공지사항 조회 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//공지사항, 자주하는 질문 구분
		String noticeYN=request.getParameter("notice");
		
		// paging
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		numPerpage=10;
		String pageBar = "";
		int totalData = new BoardService().selectBoardCount(noticeYN);
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar += "<li><span class='pageMove'>&lt;&lt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + (pageNo - 1) + "&notice=" + noticeYN +"'>&lt;&lt;</a></li>";
		}
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<li><span class='nowPage'>" + pageNo + "</span></li>";
			} else {
				pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + pageNo + "&notice=" + noticeYN + "'>"
						+ pageNo + "</a></li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li><span>&gt;&gt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + pageNo + "&notice=" + noticeYN + "'>&gt;&gt;</a></li>";
		}
		request.setAttribute("pageBar", pageBar);

		List<Board> boardList = new BoardService().selectBoardList(cPage,numPerpage,noticeYN);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("noticeYN", noticeYN);
		
		request.getRequestDispatcher("/views/service/boardList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
