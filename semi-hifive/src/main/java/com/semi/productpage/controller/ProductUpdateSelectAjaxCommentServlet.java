package com.semi.productpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.productpage.model.vo.AjaxProductComment;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class ProductUpdateSelectAjaxCommentServlet
 */
@WebServlet("/updateSelectAjaxComment")
public class ProductUpdateSelectAjaxCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateSelectAjaxCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cn = Integer.parseInt(request.getParameter("productComment"));
		
		AjaxProductComment updateAjaxComment = new ProductPageService().updateSelectAjaxProductComment(cn);
		
		response.setContentType("application/json;charset=utf-8");
		//자바 객체를 json표현식으로 변환
		new Gson().toJson(updateAjaxComment,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
