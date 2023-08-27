package com.semi.shop.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.semi.shop.dao.ShopDao;
import com.semi.shop.model.vo.ProductList;
import com.semi.shop.model.vo.ShopPageAll;
import com.semi.shop.model.vo.ShopReview;

public class ShopService {
	
	ShopDao dao=new ShopDao();
	
	public List<ShopPageAll> selectShopPage(String id){
		Connection conn=getConnection();
		List<ShopPageAll> list=dao.selectShopPage(conn,id);
		close(conn);
		return list;
	}
	
	public List<ProductList> selectProduct(String id){
		Connection conn=getConnection();
		List<ProductList> list=dao.selectProduct(conn,id);
		close(conn);
		return list;
	}
	
	public List<ShopReview> selectReview(String id){
		Connection conn=getConnection();
		List<ShopReview> list=dao.selectReview(conn,id);
		close(conn);
		return list;
	}
	
	public List<ProductList> rsoon(String id){
		Connection conn=getConnection();
		List<ProductList> list=dao.rsoon(conn,id);
		close(conn);
		return list;
	}
	public List<ProductList> psoon(String id){
		Connection conn=getConnection();
		List<ProductList> list=dao.psoon(conn,id);
		close(conn);
		return list;
	}
	public List<ProductList> hsoon(String id){
		Connection conn=getConnection();
		List<ProductList> list=dao.hsoon(conn,id);
		close(conn);
		return list;
	}
	public List<ProductList> lsoon(String id){
		Connection conn=getConnection();
		List<ProductList> list=dao.lsoon(conn,id);
		close(conn);
		return list;
	}
}
