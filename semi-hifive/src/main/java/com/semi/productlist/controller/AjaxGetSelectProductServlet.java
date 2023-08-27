package com.semi.productlist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.vo.Category;
import com.semi.category.model.vo.CategorySubCategory;
import com.semi.category.service.CategoryService;
import com.semi.mypage.model.vo.WishList;
import com.semi.productlist.model.service.ProductCategoryListService;
import com.semi.productlist.model.vo.ProductCategoryTimeList;

/**
 * Servlet implementation class AjaxGetSelectProductServlet
 */
@WebServlet("/ajaxGetproduct.do")
public class AjaxGetSelectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxGetSelectProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String categoryNameCondition = request.getParameter("categoryname");
		String subCategoryNameCondition = request.getParameter("subcategoryname");
		String statusCondition = request.getParameter("status");
		String priceCondition = request.getParameter("price");
		String areaCondition = request.getParameter("area");
		String condition="";
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
		int totalData = 0;
	 	if (categoryNameCondition != null) {
	        condition+=categoryNameCondition;
	    }
	    if (subCategoryNameCondition != null) {
	    	condition += (condition.length()>0?" OR ":"")+"R."+subCategoryNameCondition;
	    }
	    if (statusCondition != null) {
	    	condition += (condition.length()>0?" OR ":"")+statusCondition;
	    }
	    if (priceCondition != null) {
	    	priceCondition = priceCondition.replace(",", "");
	    	condition += (condition.length()>0?" OR ":"")+priceCondition;
	    }
	    if (areaCondition != null) {
	    	condition += (condition.length()>0?" OR ":"")+areaCondition;
	    }
	    if(condition.equals("")) {
	    	condition="1=1";
	    }
	    totalData = new ProductCategoryListService().GetProductConditionCount(condition);
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
		request.setAttribute("totalData", totalData);
		
		List<ProductCategoryTimeList> getselectproduct = new ProductCategoryListService().GetProductCondition(condition, cPage, numPerpage);
		//카테고리와 서브카테고리랑만 join해서 가져오는 List객체
		List<CategorySubCategory> categorylist = new CategoryService().SubCategoryList();
		//카테고리만 가져오는 List객체
		List<Category> category = new CategoryService().Category();
		List<WishList> wishlist = new ProductCategoryListService().wishlist();
		request.setAttribute("wishlist", wishlist);
		request.setAttribute("category", category);
		request.setAttribute("categorylist", categorylist);
		request.setAttribute("getselectproduct", getselectproduct);
		request.getRequestDispatcher("/views/productcategorypage/productcategorylistpage3.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
