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


@WebServlet("/service/boardListCategory.do")
public class BoardListByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardListByCategoryServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category=request.getParameter("data"); //카테고리
		if(category.equals("전체")) {
			response.sendRedirect(request.getContextPath()+"/service/boardList.do?notice=N");
		}else {
		// paging
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		numPerpage = 10;
		String pageBar = "";
		int totalData = new BoardService().selectBoardCountByCategory(category);
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar += "<li><span class='pageMove'>&lt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + (pageNo - 1) + "&notice=N"
					+ "'>&lt;</a></li>";
		}
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<li><span class='nowPage'>" + pageNo + "</span></li>";
			} else {
				pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + pageNo + "&notice=N'>"
						+ pageNo + "</a></li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li><span>&gt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI() + "?cPage=" + pageNo + "&notice=Y'>&gt;</a></li>";
		}
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("noticeYN","N");
		List<Board> boards=new BoardService().selectBoardByCategory(cPage, numPerpage, category);
		request.setAttribute("boardList", boards);
		request.getRequestDispatcher("/views/service/boardList.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
