package com.semi.sc.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.sc.service.InquiryService;

/**
 * Servlet implementation class InquiryCommentDeleteServlet
 */
@WebServlet("/service/inquiryCommentDelete.do")
public class InquiryCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InquiryCommentDeleteServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo=Integer.parseInt(request.getParameter("commentNo"));
		int result=new InquiryService().deleteComment(commentNo);
		
		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(result==1?true:false,response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
