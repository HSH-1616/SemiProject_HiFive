package com.semi.productlist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.productlist.model.service.ProductCategoryListService;
import com.semi.productlist.model.vo.ProductCategoryTimeList;

/**
 * Servlet implementation class EntireMinPriceServlet
 */
@WebServlet("/entireminprice")
public class EntireMinPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntireMinPriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		} catch (NumberFormatException e) {
			numPerpage = 32;
		}
		String pageBar = "";
		int totalData = new ProductCategoryListService().CategoryProductListCount();
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		if(pageNo == 1) {
			pageBar += "<li><span class='pageMove'>&lt;</span></li>";
		} else {
			pageBar += "<li><a href='javascript:void(0);' onclick='changePage("+(pageNo-1)+");'>&lt;</a></li>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<li><span class='nowPage'>"+pageNo+"</span></li>";
			}else {
				pageBar+="<li><a href='javascript:void(0);' onclick='changePage("+ pageNo + ");'&numPerpage=" +numPerpage + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar += "<li><span>&gt;</span></li>";
		} else {
			pageBar += "<li><a href='javascript:void(0);'onclick='changePage("+ pageNo + ");'&numPerpage=" + numPerpage + "'>&gt;</a></li>";
		}
		request.setAttribute("totalData", totalData);
		request.setAttribute("pageBar", pageBar);
		
		List<ProductCategoryTimeList> productlist = new ProductCategoryListService().EntireMinPrice(cPage, numPerpage);
		
		
		request.setAttribute("productlist", productlist);
		request.getRequestDispatcher("/views/productcategorypage/entrieminprice.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
