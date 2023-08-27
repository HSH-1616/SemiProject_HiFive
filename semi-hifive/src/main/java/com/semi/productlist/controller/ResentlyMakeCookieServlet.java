package com.semi.productlist.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ResentlyCookieServlet
 */
@WebServlet("/resentlymakecookie")
public class ResentlyMakeCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResentlyMakeCookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("no");
		String productFileName = request.getParameter("filename");
		String productTitle = request.getParameter("producttitle");
		Cookie[] cookies=request.getCookies();
		System.out.println(productFileName);
		System.out.println(productTitle);
		System.out.println(productId);
		String recentPrev=Arrays.stream(cookies)
				.filter(c->c.getName().equals("recentList"))
				.map(e->{
					try {
						return URLDecoder.decode(e.getValue(),"UTF-8");
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						return e.getValue();
					}
				})
				.collect(Collectors.joining());
		List<Map<String,String>> recentPreList;
		if(recentPrev.length()>0) {
			recentPreList=new Gson().fromJson(recentPrev,List.class);
			 if (recentPreList.size() >= 3) {
		            recentPreList.remove(0);
		        }
		}else {
			recentPreList=new ArrayList();
		}
//		System.out.println(recentPreList);
//		recentPreList.stream().forEach(System.out::println);
		  Cookie cookieproductId = new Cookie("productId", productId);
		  Cookie cookieproductFileName = new Cookie("productFileName", URLEncoder.encode(productFileName,"UTF-8"));
		  Cookie cookieproductTitle = new Cookie("productTitle", URLEncoder.encode(productTitle,"UTF-8"));
		  cookieproductId.setMaxAge(60*60*24);
		  cookieproductFileName.setMaxAge(60*60*24);
		  cookieproductTitle.setMaxAge(60*60*24);
		  response.addCookie(cookieproductId); 
		  response.addCookie(cookieproductFileName);
		  response.addCookie(cookieproductTitle);
		  
		  recentPreList.add(Map.of("productId",productId,"productFileName",productFileName,"productTitle",productTitle));
		  if (recentPreList.size() > 3) {
		        recentPreList.subList(0, recentPreList.size() - 3).clear();
		    }
		  Cookie c=new Cookie("recentList",URLEncoder.encode(new Gson().toJson(recentPreList),"utf-8"));
		  c.setMaxAge(60*60*24);
		  response.addCookie(c);
		  
		  response.sendRedirect(request.getContextPath()+"/productpage?no="+productId);
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
