package com.semi.category.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.category.model.vo.Category;
import com.semi.category.model.vo.CategorySubCategory;
import com.semi.category.service.CategoryService;

/**
 * Servlet implementation class HeaderCategoryAjax
 */
@WebServlet("/headercategoryajax")
public class HeaderCategoryAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderCategoryAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Category> categories =  new CategoryService().Category();
        
        List<CategorySubCategory> subCategory =  new CategoryService().SubCategoryList();
            
        Map<String,List> category = Map.of("main",categories,"sub",subCategory);
            response.setContentType("application/json;charset=utf-8");
            new Gson().toJson(category, response.getWriter());
    		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
