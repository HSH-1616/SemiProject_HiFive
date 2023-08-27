package com.semi.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.ProductList;
import com.semi.mypage.model.vo.ReviewTrade;
import com.semi.mypage.service.MypageMemberService;
import com.semi.mypage.service.MypageProductService;
import com.semi.productpage.model.vo.ShopPage;

/**
 * Servlet implementation class BuyListServlet
 */
@WebServlet("/myPage/buyList.do")
public class BuyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String buyerId = request.getParameter("userId");
		
		// 페이징
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		numPerpage = 5;
		
		String pageBar = "";
		int totalData = new MypageProductService().countBuyList(buyerId);
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		if(pageNo == 1) {
			pageBar += "<li><span class='pageMove'>&lt;&lt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI()
				+ "?cPage=" + (pageNo-1) + "&numPerpage=" + numPerpage + "&userId=" + buyerId + "'>&lt;&lt;</a></li>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<li><span class='nowPage'>"+pageNo+"</span></li>";
			}else {
				pageBar+="<li><a href='"+request.getRequestURI()
					+"?cPage="+pageNo+ "&userId=" + buyerId + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar += "<li><span>&gt;&gt;</span></li>";
		} else {
			pageBar += "<li><a href='" + request.getRequestURI()
			+ "?cPage=" + pageNo + "&numPerpage=" + numPerpage + "&userId=" + buyerId + "'>&gt;&gt;</a></li>";
		}
		request.setAttribute("pageBar", pageBar);
		
//		구매목록 가져오기
		List<ProductList> p = new MypageProductService().selectBuyListByUserId(cPage, numPerpage, buyerId);
//		후기목록 가져오기
		List<ReviewTrade> rt = new MypageProductService().selectReview(buyerId);
		
		request.setAttribute("reviews", rt);
		request.setAttribute("buyProduct", p);
		request.setAttribute("userId", buyerId);
		request.getRequestDispatcher("/views/mypage/buyList.jsp").forward(request, response);
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
