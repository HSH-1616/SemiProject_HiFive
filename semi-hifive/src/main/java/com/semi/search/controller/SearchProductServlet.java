 package com.semi.search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.search.model.vo.SearchCount;
import com.semi.search.service.SearchService;
import com.semi.shop.model.vo.ProductList;

/**
 * Servlet implementation class SearchFunctionServlet
 */
@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");
		List<ProductList> searchList = new ArrayList();
		SearchCount searchCount = new SearchService().searchCount(content);
		
		if (content.length()>2&&content.charAt(1)=='#') {			
			searchList = new SearchService().searchKeyWord(content);
		} else {
			searchList = new SearchService().searchTitle(content);
		}
		request.setAttribute("content", content);
		request.setAttribute("searchList", searchList);
		request.setAttribute("searchCount", searchCount);
		request.getRequestDispatcher("/views/productSearchPage/productSearchPage.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
