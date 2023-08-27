package com.semi.shop.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.Review;
import com.semi.mypage.model.vo.Trade;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductFile;
import com.semi.productpage.dao.ProductDao;
import com.semi.productpage.model.vo.ShopPage;
import com.semi.shop.model.vo.ProductList;
import com.semi.shop.model.vo.ShopPageAll;
import com.semi.shop.model.vo.ShopReview;

public class ShopDao {
	
	private Properties sql = new Properties();

	public ShopDao() {
		String path = ProductDao.class.getResource("/sql/shop/shopsql.properties").getPath();
		try (FileReader fr = new FileReader(path);) {
			sql.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<ShopPageAll> selectShopPage(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShopPageAll> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectShopPage"));
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getShopPageAll(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ProductList> selectProduct(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProduct"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ShopReview> selectReview(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShopReview> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectReview"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getShopReview(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ProductList> psoon(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("psoon"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ProductList> rsoon(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("rsoon"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<ProductList> hsoon(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("hsoon"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<ProductList> lsoon(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("lsoon"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	private ShopPageAll getShopPageAll(ResultSet rs) throws SQLException {
		return ShopPageAll.builder()
				.product(Product.builder()				
						.productId(rs.getInt("product_id"))
						.userId(rs.getString("user_id"))
						.title(rs.getString("product_title"))
						.price(rs.getInt("price"))
						.registTime(rs.getDate("regist_time"))
						.areaName(rs.getString("area_name"))						
						.build())
				.productFile(ProductFile.builder()
						.imageName(rs.getString("product_image_name"))
						.build())
				.shopPage(ShopPage.builder()
						.userId(rs.getString("user_id"))
						.introduce(rs.getString("introduce"))						
						.build())
				.member(Member.builder()
						.userId(rs.getString("user_Id"))
						.nickName(rs.getString("nickname"))
						.profileImg(rs.getString("profile_img"))
						.enrollDate(rs.getDate("enroll_date"))
						.temperature(rs.getDouble("temperature"))
						.build())
				.trade(Trade.builder()
						.buyerId(rs.getString("buyer_id"))
						.build())
				.review(Review.builder()
						.reviewScore(rs.getDouble("review_score"))
						.reviewMsg(rs.getString("review_msg"))
						.reviewDate(rs.getDate("review_date"))
						.build())
				.productCount(rs.getInt("pc"))
				.reviewCount(rs.getInt("rc"))
				.tradeCount(rs.getInt("tc"))
				.build();
	}
	
	private ProductList getProductList(ResultSet rs) throws SQLException {
		return ProductList.builder()
				.product(Product.builder()				
						.productId(rs.getInt("product_id"))
						.userId(rs.getString("user_id"))
						.title(rs.getString("product_title"))
						.productStatus(rs.getString("product_status"))
						.price(rs.getInt("price"))
						.registTime(rs.getDate("regist_time"))
						.viewCount(rs.getInt("view_count"))
						.areaName(rs.getString("area_name"))
						.build())
				.productFile(ProductFile.builder()
						.imageName(rs.getString("product_image_name"))
						.build())
				.build();
	}
	
	private ShopReview getShopReview(ResultSet rs) throws SQLException {
		return ShopReview.builder()
				
				.member(Member.builder()	
						.userId(rs.getString("user_id"))
						.nickName(rs.getString("nickname"))
						.profileImg(rs.getString("profile_img"))
						.enrollDate(rs.getDate("enroll_date"))
						.temperature(rs.getDouble("temperature"))
						.build())				
				.review(Review.builder()
						.reviewScore(rs.getDouble("review_score"))
						.reviewMsg(rs.getString("review_msg"))
						.reviewDate(rs.getDate("review_date"))
						.build())
				
				.build();
	}
	
	
	
}
