package com.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/productRemove.do")
public class ProductRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
		int result = new AdminService().productRemove(no);
		
		if(result>=1) {
			System.out.println("삭제완료");
			response.sendRedirect("/semi-hifive/productAdmin.do");
//			request.getRequestDispatcher("/boardListAdmin.do").forward(request, response);
		}else {
			System.out.println("삭제실패");
			response.sendRedirect("/semi-hifive/productAdmin.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
