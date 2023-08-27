package com.semi.sc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sc.service.BoardService;


@WebServlet("/service/deleteBoard.do")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardDeleteServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("no"));
		int result=new BoardService().deleteBoard(boardNo);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/service/boardList.do?notice=N");
		}else {
			request.setAttribute("msg", "삭제 실패했습니다.");
			request.setAttribute("log", "/views/service/boardList.jsp");
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
