package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.service.MemberService;

/**
 * Servlet implementation class CheckNumberServlet
 */
@WebServlet("/checkNumber")
public class CheckNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CheckNumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cNumber = request.getParameter("cNumber");
			String inputEmail = request.getParameter("inputEmail");
			
			// 1 , 2 , 3
			int result = new MemberService().checkNumber(inputEmail,cNumber);
			
			response.getWriter().print(result);  // getWriter -> jsp의 data로 들어감
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
