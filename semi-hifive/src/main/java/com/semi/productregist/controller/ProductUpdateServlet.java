package com.semi.productregist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.vo.Category;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductFile;
import com.semi.productregist.service.ProductRegistService;


@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		
		
		Product p = new ProductRegistService().selectProduct(productId);
		
		List<ProductFile> pf = new ProductRegistService().selectProductFile(productId);
		List<Category> categorys = new ProductRegistService().selectAll();
		
		
		request.setAttribute("categorys", categorys); // 카테고리들 디비에서 불러옴
		request.setAttribute("p", p);  // 해당 상품들의 정보들 갖고옴
		request.setAttribute("pf", pf); // 해당 상품이미지들 정보들 갖고옴
		
		request.getRequestDispatcher("/views/product/productregistupdate.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
