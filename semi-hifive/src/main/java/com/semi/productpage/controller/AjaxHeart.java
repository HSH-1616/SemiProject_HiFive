package com.semi.productpage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.mypage.model.vo.WishList;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class AjaxHeart
 */
@WebServlet("/ajaxHeart")
public class AjaxHeart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxHeart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId=request.getParameter("loginId");
		int no = Integer.parseInt(request.getParameter("productId"));
		
		List<WishList> w=new ProductPageService().ajaxHeart(loginId,no);
		
		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(w,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
