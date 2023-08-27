package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.service.MemberService;


@WebServlet("/changePassword.do")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newPass = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println(newPass);
		System.out.println(email);
		int result = new MemberService().changePasswordByEmail(newPass,email);
		
		/*
		 * if(result == 1) { request.setAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
		 * request.setAttribute("loc", "/");
		 * 
		 * request.getRequestDispatcher("/views/common/msg.jsp") .forward(request,
		 * response);
		 * 
		 * }else { request.setAttribute("msg", "시스템 오류로 비밀번호 변경에 실패했습니다.");
		 * request.setAttribute("loc", "/findPwdView.do");
		 * request.getRequestDispatcher("/views/common/msg.jsp") .forward(request,
		 * response); }
		 */
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
