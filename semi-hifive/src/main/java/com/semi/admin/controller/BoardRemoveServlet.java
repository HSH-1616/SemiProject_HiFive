package com.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/boardRemove.do")
public class BoardRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardRemoveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("boardNo");
		int result = new AdminService().boardRemove(boardNo);
		
		if(result>=1) {
			System.out.println("삭제완료");
			response.sendRedirect("/semi-hifive/boardListAdmin.do");
//			request.getRequestDispatcher("/boardListAdmin.do").forward(request, response);
		}else {
			System.out.println("삭제실패");
			response.sendRedirect("/semi-hifive/boardListAdmin.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
