package com.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.service.AdminService;


@WebServlet("/deleteCheckProduct")
public class DeleteCheckProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteCheckProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String arr[] = request.getParameterValues("arr[]"); // arr[] -> 배열로 넘기려면 []로 받아야함 
		
		String sql = "";
		for(int i=0; i<arr.length; i++) {
			sql+="'";
			sql+=arr[i];
			sql+="'";
			if(i!=arr.length-1) {
				sql+=",";
			}
		}
	
		int result = new AdminService().deleteCheckProduct(sql);
		
		response.getWriter().print(result);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
