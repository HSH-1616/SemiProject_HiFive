package com.semi.productlist.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.semi.productlist.model.vo.ProductCategoryTimeList;

/**
 * Servlet Filter implementation class MinPriceFilter
 */
@WebFilter("/minpricefilter")
public class MinPriceFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public MinPriceFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		List<ProductCategoryTimeList> price = (List)session.getAttribute("getselectproduct");
		System.out.println(price);
	    Comparator<ProductCategoryTimeList> priceComparator = Comparator.comparingDouble(p -> p.getProductCategoryList().getPrice());
	    priceComparator = priceComparator.reversed();
	    price.sort(priceComparator);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
