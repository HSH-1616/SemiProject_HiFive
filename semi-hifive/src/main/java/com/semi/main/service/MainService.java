package com.semi.main.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.semi.main.dao.MainDao;
import com.semi.main.model.vo.ProductElapsedTime;

public class MainService {
	private MainDao dao = new MainDao();
	
	public List<ProductElapsedTime> productListAll(){
		Connection conn = getConnection();
		List<ProductElapsedTime> pet = dao.productListAll(conn);
		close(conn);
		return pet;
	}
	
	public List<ProductElapsedTime> productListNew(){
		Connection conn = getConnection();
		List<ProductElapsedTime> pet = dao.productListNew(conn);
		close(conn);
		return pet;
	}
	
	public List<ProductElapsedTime> wishListByUserId(String userId){
		Connection conn = getConnection();
		List<ProductElapsedTime> pet = dao.wishListByUserId(conn, userId);
		close(conn);
		return pet;
	}
}
