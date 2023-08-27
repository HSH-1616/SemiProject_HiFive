package com.semi.category.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.semi.category.model.vo.Category;
import com.semi.category.service.CategoryService;
import com.semi.productlist.model.service.ProductCategoryListService;
import com.semi.productlist.model.vo.ProductCategoryTimeList;

/**
 * Servlet implementation class HeaderCategoryServlet
 */
@WebServlet("/serachcategory.do")
public class SearchCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String categoryname = request.getParameter("categoryname");
			
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
			int totalData = new ProductCategoryListService().CategoryCount(categoryname);
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
			request.setAttribute("pageBar", pageBar);
			// 카테고리 테이블에서 카테고리 이름찾아서 가져오는 객체
			Category categoryName = new CategoryService().CategoryName(categoryname);
			// 대표카테고리를 찾아서 상품List를 가져오는 객체
			List<ProductCategoryTimeList> searchcategory = new ProductCategoryListService().CategoryList(cPage, numPerpage, categoryname);
			
			//totalData가 카테고리 이름만 찾아서 상품List 갯수 set에 저장
			request.setAttribute("totalData", totalData);
			request.setAttribute("categoryName", categoryName);
		    request.setAttribute("searchcategory", searchcategory);
			request.getRequestDispatcher("/views/productcategorypage/headersearchcategory.jsp").forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
