package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/userRemove.do")
public class UserRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		int result = new AdminService().userRemove(email);
		
		if(result==1) {
			System.out.println("삭제완료");
			response.sendRedirect("/semi-hifive/memberList.do");
		/*	request.getRequestDispatcher("/memberList.do").forward(request, response);*/
		}else {
			System.out.println("삭제실패");
			response.sendRedirect("/semi-hifive/memberList.do");

			/* request.getRequestDispatcher("/memberList.do").forward(request, response); */
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
