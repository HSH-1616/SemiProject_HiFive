package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    // 로그아웃 시키는 서블릿기능
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자를 로그아웃 시키기
		//HttpSession에 저장된 데이터를 삭제
		//1. HttpSession을 가져오기
		HttpSession session = request.getSession(false);
		
		//2. HttpSession을 삭제하는 메소드를 이용함
		// session.invalidate();
		
		if(session!=null) {
			session.invalidate();
		}
		
		// 3. 출력할 화면 선택
		// 메인화면으로 이동 -> index.jsp로
		response.sendRedirect(request.getContextPath()); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
