package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.member.service.MemberService;


@WebServlet("/duplicateId.do")
public class IdDupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IdDupCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println(userId);
		Member m = new MemberService().selectByUserId(userId);
		System.out.println(m);
		response.setContentType("text/csv;charset=utf-8");  // csv, html 아무거나해도됨
		response.getWriter().print(m==null?true:false);  // m==null 일경우 -> 아이디는 없는것으로 사용가능함
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
