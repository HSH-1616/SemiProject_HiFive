package com.semi.productpage.dao;

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

import com.semi.category.model.vo.Category;
import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.WishList;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductComment;
import com.semi.product.model.vo.ProductFile;
import com.semi.productpage.model.vo.AjaxProductComment;
import com.semi.productpage.model.vo.ProductCategory;
import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.model.vo.ProductUser;
import com.semi.productpage.model.vo.WishListCount;

public class ProductDao {

	private Properties sql = new Properties();

	public ProductDao() {
		String path = ProductDao.class.getResource("/sql/productpage/productpagesql.properties").getPath();
		try (FileReader fr = new FileReader(path);) {
			sql.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int updateViewCount(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateViewCount"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public ProductCategory selectProduct(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductCategory p = null;
		String sql = this.sql.getProperty("selectProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				p = getProductCategory(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}
	
	public List<ProductFile> selectProductFile(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductFile> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProductFile"));
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductFile(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ProductUser> selectProductUser(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductUser> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProductUser"));
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductUser(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ProductCommentUser> selectAllProductComment(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductCommentUser> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllProductComment"));
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductCommentUser(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public AjaxProductComment selectAjaxProductComment(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AjaxProductComment p = null;
		String sql = this.sql.getProperty("selectAjaxProductComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				p = getAjaxProductComment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}

	public AjaxProductComment selectReAjaxProductComment(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AjaxProductComment p = null;
		String sql = this.sql.getProperty("selectReAjaxProductComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				p = getAjaxProductComment (rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}

	public int insertAjaxProductComment(Connection conn, ProductCommentUser pc) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertAjaxProductComment"));
			pstmt.setInt(1, pc.getProduct().getProductId());
			pstmt.setString(2, pc.getProductComment().getUserId());
			pstmt.setString(3, pc.getProductComment().getCommentRef() == 0 ? null : String.valueOf(pc.getProductComment().getCommentRef()));
			pstmt.setString(4, pc.getProductComment().getContent());
			pstmt.setInt(5, pc.getProductComment().getCommentLevel());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAjaxProductComment(Connection conn, ProductCommentUser pc, int cn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateAjaxProductComment"));
			pstmt.setString(1, pc.getProductComment().getContent());
			pstmt.setInt(2, cn);
			pstmt.setInt(3, pc.getProductComment().getProductId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public AjaxProductComment updateSelectAjaxProductComment(Connection conn, int cn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AjaxProductComment p = null;
		String sql = this.sql.getProperty("updateSelectAjaxProductComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				p = getAjaxProductComment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}

	public int deleteProductComment(Connection conn, int cn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteProductComment"));
			pstmt.setInt(1, cn);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<WishListCount> wishListCount(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WishListCount>  w = new ArrayList();
		String sql = this.sql.getProperty("wishListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				w.add(getWishListCount(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return w;
	}
	
	public List<WishList> ajaxHeart(Connection conn, String loginId,int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WishList>  w =  new ArrayList();

		try {
			pstmt = conn.prepareStatement(sql.getProperty("ajaxHeart"));
			pstmt.setString(1, loginId);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				w.add(getWishList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return w;
	}
	
	public int updateAjaxHeart(Connection conn, String loginId, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("ajaxUpdateHeart"));
			pstmt.setString(1, loginId);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteAjaxHeart(Connection conn, String loginId, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("ajaxDeleteHeart"));
			pstmt.setString(1, loginId);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertPay(Connection conn, int id, String user) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertPay"));
			pstmt.setInt(1, id);
			pstmt.setString(2, user);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateStatus(Connection conn, int id) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateStatus"));
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteProduct(Connection conn,int id) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteProduct"));
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println(result);
		}
		return result;
	}

	private ProductCategory getProductCategory(ResultSet rs) throws SQLException {
		return ProductCategory.builder()
				.product(Product.builder()				
						.productId(rs.getInt("product_id"))
						.userId(rs.getString("user_id"))
						.title(rs.getString("product_title"))
						.productStatus(rs.getString("product_status"))
						.sellStatus(rs.getString("sell_status"))
						.price(rs.getInt("price"))
						.registTime(rs.getDate("regist_time"))
						.viewCount(rs.getInt("view_count"))
						.explanation(rs.getString("explanation"))
						.keyword(rs.getString("keyword"))
						.subCategoryName(rs.getString("subcategory_name"))
						.areaName(rs.getString("area_name"))
						.build())
				.category(Category.builder()
						.categoryName(rs.getString("category_name"))
						.build())
				.member(Member.builder()
						.userId(rs.getString("user_id"))
						.nickName(rs.getString("nickname"))
						.profileImg(rs.getString("profile_img"))
						.temperature(rs.getDouble("temperature"))
						.build())
				.build();
	}
	
	private ProductFile getProductFile(ResultSet rs) throws SQLException {
		return ProductFile.builder()								
						.productId(rs.getInt("product_id"))
						.imageName(rs.getString("product_image_name"))
						.mainImageYn(rs.getString("main_image_yn").charAt(0))
						.build();
				
	}
		
	private ProductUser getProductUser(ResultSet rs) throws SQLException {
		return ProductUser.builder()
				.product(Product.builder()				
						.productId(rs.getInt("product_id"))
						.userId(rs.getString("user_id"))
						.title(rs.getString("product_title"))
						.productStatus(rs.getString("product_status"))
						.sellStatus(rs.getString("sell_status"))
						.price(rs.getInt("price"))
						.registTime(rs.getDate("regist_time"))
						.viewCount(rs.getInt("view_count"))
						.explanation(rs.getString("explanation"))
						.keyword(rs.getString("keyword"))
						.subCategoryName(rs.getString("subcategory_name"))
						.areaName(rs.getString("area_name"))
						.build())
				.member(Member.builder()
						.userId(rs.getString("user_id"))
						.nickName(rs.getString("nickname"))
						.profileImg(rs.getString("profile_img"))
						.temperature(rs.getDouble("temperature"))
						.build())
				.file(ProductFile.builder()
						.imageName(rs.getString("product_image_name"))
						.build())
				.count(rs.getInt("count"))
				.build();
	}

		
	private ProductCommentUser getProductCommentUser(ResultSet rs) throws SQLException {
		return ProductCommentUser.builder()
				.product(Product.builder()				
						.productId(rs.getInt("product_id"))
						.userId(rs.getString("user_id"))
						.build())
				.productComment(ProductComment.builder()
						.userId(rs.getString("user_id"))
						.productId(rs.getInt("product_id"))
						.commentNo(rs.getInt("product_comment_no"))
						.commentLevel(rs.getInt("product_comment_level"))
						.content(rs.getString("product_comment_content"))
						.commentRef(rs.getInt("product_comment_ref"))
						.enrollDate(rs.getDate("product_comment_date"))
						.build())
				.member(Member.builder()
						.nickName(rs.getString("nickname"))
						.profileImg(rs.getString("profile_img"))
						.build())
				.count(rs.getInt("comment_count"))
				.build();
	}
	
	private AjaxProductComment getAjaxProductComment(ResultSet rs) throws SQLException {
		return AjaxProductComment.builder()
				.userId(rs.getString("user_id"))
				.productId(rs.getInt("product_id"))
				.commentNo(rs.getInt("product_comment_no"))
				.commentLevel(rs.getInt("product_comment_level"))
				.content(rs.getString("product_comment_content"))
				.commentRef(rs.getInt("product_comment_ref"))
				.enrollDate(rs.getDate("product_comment_date"))
				.nickName(rs.getString("nickname"))
				.profileImg(rs.getString("profile_img"))
				.build();
	}
	
	private WishList getWishList(ResultSet rs) throws SQLException {
		return WishList.builder()
				.wishUserId(rs.getString("wish_user_id"))
				.productId(rs.getInt("product_id"))
				.build();
	}
	
	private WishListCount getWishListCount(ResultSet rs) throws SQLException {
		return WishListCount.builder()
			.wishlist(WishList.builder()
				.wishUserId(rs.getString("wish_user_id"))
				.productId(rs.getInt("product_id"))
				.build())
				.count(rs.getInt("count"))
				.build();
	}
	
	

}