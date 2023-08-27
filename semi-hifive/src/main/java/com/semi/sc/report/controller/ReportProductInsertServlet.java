package com.semi.sc.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.product.model.vo.Product;
import com.semi.sc.service.ReportService;

/**
 * Servlet implementation class ReportProductInsertServlet
 */
@WebServlet("/service/reportProductInsert.do")
public class ReportProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReportProductInsertServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId=Integer.parseInt(request.getParameter("no"));
		Product p=new ReportService().selectProductInfo(productId);
		request.setAttribute("product", p);
		request.getRequestDispatcher("/views/service/reportInsertByProduct.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
