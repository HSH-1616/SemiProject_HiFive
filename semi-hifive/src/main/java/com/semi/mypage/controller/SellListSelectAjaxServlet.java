package com.semi.mypage.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.mypage.service.MypageProductService;

/**
 * Servlet implementation class SellListSelectAjaxServlet
 */
@WebServlet("/mypage/ajaxSelect.do")
public class SellListSelectAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellListSelectAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectValue;
		String productId = request.getParameter("productId");
		String userId = request.getParameter("userId");

		if (request.getParameter("selectValue").equals("sell")) {
			selectValue = "판매중";
		} else if (request.getParameter("selectValue").equals("reservation")) {
			selectValue = "예약중";
		} else {
			selectValue = "판매완료";
		}
		
		int result = new MypageProductService().changeSelectValue(selectValue, productId);
		
		// 판매상태 카운트(전체)
		int total = new MypageProductService().countSellStatusAll(userId);

		// 판매상태 카운트(판매중, 예약중, 판매완료)
		String selling = "판매중";
		int countStatusSell = new MypageProductService().countSellStatusSell(userId, selling);
		String reserve = "예약중";
		int countStatusRes = new MypageProductService().countSellStatusSell(userId, reserve);
		String soldOut = "판매완료";
		int countStatusSol = new MypageProductService().countSellStatusSell(userId, soldOut);
//		
		Map jsonData=Map.of("total", total, "countStatusSell", countStatusSell, 
				"countStatusRes", countStatusRes, "countStatusSol", countStatusSol);
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(jsonData, response.getWriter());
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
