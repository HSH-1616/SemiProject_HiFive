package com.semi.search.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.product.model.vo.Product;
import com.semi.search.dao.SearchDao;
import com.semi.search.model.vo.Search;
import com.semi.search.model.vo.SearchCount;
import com.semi.shop.model.vo.ProductList;
public class SearchService {
	
	SearchDao dao=new SearchDao();
	
	public int insertSearch(String content){
		Connection conn=getConnection();
		int result=dao.insertSearch(conn,content);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<ProductList> searchTitle(String content){
		Connection conn=getConnection();
		List<ProductList> searchList=dao.searchTitle(conn,content);
		close(conn);
		return searchList;
	}
	
	public List<ProductList> searchKeyWord(String content){
		Connection conn=getConnection();
		List<ProductList> searchList=dao.searchKeyWord(conn,content);
		close(conn);
		return searchList;
	}
	
	public SearchCount searchCount(String content){
		Connection conn=getConnection();
		SearchCount searchCount=dao.searchCount(conn,content);
		close(conn);
		return searchCount;
	}
	
	public List<Search> printSearch(){
		Connection conn=getConnection();
		List<Search> searchList=dao.printSearch(conn);
		close(conn);
		return searchList;
	}
	
	public List<ProductList> allSoon(String content, String soon){
		Connection conn=getConnection();
		List<ProductList> searchList=dao.allSoon(conn,content,soon);
		close(conn);
		return searchList;
	}
}
