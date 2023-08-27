package com.semi.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.shop.model.vo.ProductList;
import com.semi.shop.model.vo.ShopPageAll;
import com.semi.shop.model.vo.ShopReview;
import com.semi.shop.service.ShopService;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		List<ShopPageAll> shop = new ShopService().selectShopPage(id);
		List<ProductList> product = new ShopService().selectProduct(id);
		List<ShopReview> review = new ShopService().selectReview(id);
		request.setAttribute("shop", shop);
		request.setAttribute("product", product);
		request.setAttribute("review", review);
		request.getRequestDispatcher("/views/shoppage/shoppage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
