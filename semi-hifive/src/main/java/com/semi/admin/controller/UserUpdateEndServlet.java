package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/updateMemberEnd.do")
public class UserUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String declareCount = request.getParameter("declareCount");
		String enrollDate = request.getParameter("enrollDate");
		String temp = request.getParameter("temp");

		System.out.println("두번재 서블릿들어옴");
		
		int result = new AdminService().updateMember(userName,nickName,declareCount,enrollDate,temp,email);
		
		String msg="";
		String loc="";
		
		if(result==1) {
			msg = "정상적으로 수정되었습니다.";
		}
		else {
			msg = "비밀번호 수정에 실패하였습니다.";
		}
		loc = "/";
		request.setAttribute("script", "opener.location.replace('" + request.getContextPath() + "/memberList.do'); close();");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
