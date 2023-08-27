package com.semi.sc.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.sc.service.InquiryService;


@WebServlet("/service/deleteInquiry.do")
public class InquiryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InquiryDeleteServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inquiryNo=Integer.parseInt(request.getParameter("no"));
		int result=new InquiryService().deleteInquiry(inquiryNo);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/service/InquiryList.do");
		}else {
			request.setAttribute("msg", "삭제 실패했습니다.");
			request.setAttribute("log", "/views/service/boardList.jsp");
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
