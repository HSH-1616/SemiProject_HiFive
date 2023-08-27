package com.semi.productregist.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.vo.SubCategory;
import com.semi.productregist.service.ProductRegistService;


@WebServlet("/findSubCate")
public class FindSubCate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindSubCate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateId = request.getParameter("cateId");
		System.out.println(cateId);
		
		List<String> subCategorys = new ProductRegistService().selectSubCate(cateId);
		
		String result = subCategorys.stream().map(n->String.valueOf(n)).collect(Collectors.joining(","));
		// 리스트를 -> 문자열로 만들어줌 (문자열로 만들어줄때 앞뒤로 공백을 없애고 ,로 구분해줌)
		// [컴퓨터, 노트북, 스마트폰, 소프트웨어, 기타 주변기기]  -> 컴퓨터,노트북,스마트폰,소프트웨어,기타 주변기기

		
		System.out.println(result);
		response.getWriter().print(result);  // getWriter -> jsp의 data로 들어감
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
