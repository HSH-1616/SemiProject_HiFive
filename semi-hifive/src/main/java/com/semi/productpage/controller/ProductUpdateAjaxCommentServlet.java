package com.semi.productpage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.product.model.vo.ProductComment;
import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class ProductChangeCommentServlet
 */
@WebServlet("/updateAjaxComment")
public class ProductUpdateAjaxCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateAjaxCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cn=Integer.parseInt(request.getParameter("commentNo"));
		ProductCommentUser pc=ProductCommentUser.builder()
				.productComment(ProductComment.builder()
				.productId(Integer.parseInt(request.getParameter("productId")))
				.content(request.getParameter("content"))
				.build())
				.build();

		int result=new ProductPageService().updateAjaxProductComment(pc,cn);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
