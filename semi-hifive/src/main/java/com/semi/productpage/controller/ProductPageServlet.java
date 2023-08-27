package com.semi.productpage.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.product.model.vo.ProductFile;
import com.semi.productpage.model.vo.ProductCategory;
import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.model.vo.ProductUser;
import com.semi.productpage.model.vo.WishListCount;
import com.semi.productpage.service.ProductPageService;

/**
 * Servlet implementation class ProductPageServlet
 */
@WebServlet("/productpage")
public class ProductPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Product테이블 데이터 가져오기
		int no = Integer.parseInt(request.getParameter("no"));
		
		Cookie[] cookies = request.getCookies();

//		String read = Arrays.stream(cookies)
//				.filter(c -> c.getName().equals("viewCount")).findFirst()
//				.orElse(new Cookie("viewCount", "")).getValue();
		String read="";
		//boolean isRead = read.contains("|" + no + "|");
		boolean isRead=false;
		if(!isRead) {
			Cookie c=new Cookie("viewCount",read+"|"+no+"|");
			c.setMaxAge(60*60);
			c.setPath("/");
			//response.addCookie(c);
		}
		
		ProductCategory p = new ProductPageService().selectProduct(no,isRead);
		List<WishListCount> w= new ProductPageService().wishListCount(no);
		List<ProductCommentUser> comments = new ProductPageService().selectAllProductComment(no);
		List<ProductFile> files = new ProductPageService().selectProductFile(no);
		List<ProductUser> users = new ProductPageService().selectProductUser(no);
		request.setAttribute("product", p);
		request.setAttribute("wishListCount", w);
		request.setAttribute("files", files);
		request.setAttribute("users", users);
		request.setAttribute("comments", comments);
		// 출력할 화면 선택		
				
		request.getRequestDispatcher("/views/productpage/productpage.jsp").forward(request, response);
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