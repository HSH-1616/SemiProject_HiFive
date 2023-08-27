package com.semi.productregist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.category.model.vo.Category;
import com.semi.member.model.vo.Member;
import com.semi.productregist.service.ProductRegistService;


@WebServlet("/productRegist.do")
public class ProductRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

			List<Category> categorys = new ProductRegistService().selectAll();
			System.out.println(categorys);
			request.setAttribute("categorys", categorys);
			
			request.getRequestDispatcher("/views/product/productregist.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
