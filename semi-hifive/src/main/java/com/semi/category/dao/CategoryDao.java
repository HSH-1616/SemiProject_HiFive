package com.semi.category.dao;

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
import com.semi.category.model.vo.CategorySubCategory;
import com.semi.category.model.vo.SubCategory;

public class CategoryDao {
	private Properties sql = new Properties();
	{
		String path = CategoryDao.class.getResource("/sql/product/category_sql.properties").getPath();
		
		try {
			sql.load(new FileReader(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Category getcategory(ResultSet rs) throws SQLException {
		return Category.builder()
				.categoryId(rs.getString("CATEGORY_ID"))
				.categoryName(rs.getString("CATEGORY_NAME")).build();
	}
	public static CategorySubCategory getcategorysubcategory(ResultSet rs) throws SQLException {
		return CategorySubCategory.builder()
				.subCategory(SubCategory.builder()
						.subcategoryName(rs.getString("SUBCATEGORY_NAME")).build())
				.category(Category.builder()
						.categoryId(rs.getString("CATEGORY_ID"))
						.categoryName(rs.getString("CATEGORY_NAME")).build()).build();
				
	}
	
	// 대표카테고리만 출력해주는 메소드
	public List<Category> Category(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Category> selectcategory = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("Category"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				selectcategory.add(getcategory(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return selectcategory;
	}
	// 카테고리와 세부카테고리 두개 join한 메소드
	public List<CategorySubCategory> SubCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CategorySubCategory> subctcategory = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("SubCategoryList"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				subctcategory.add(getcategorysubcategory(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return subctcategory;
	}
	
	// 대표카테고리 테이블에서 대표카테고리 이름을 찾아서 출력해주는 메소드
		public Category CategoryName(Connection conn, String categoryname) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Category c = null;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("CategoryName"));
				pstmt.setString(1, categoryname);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					c = getcategory(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return c;
		}
	// 대표 카테고리이름과 서브카테고리 이름만 나올수 있는 메소드
		public CategorySubCategory SubCategoryName(Connection conn, String subcategoryname) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CategorySubCategory sc = null;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("SubCategoryName"));
				pstmt.setString(1, subcategoryname);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sc = getcategorysubcategory(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return sc;
		}
//		대표카테고리 이름을 검색해서 서브카테고리이름 찾기
		public CategorySubCategory SelectSubCategoryName(Connection conn, String subcategoryname) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CategorySubCategory sc = null;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("SelectSubCategoryName"));
				pstmt.setString(1, subcategoryname);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sc = getcategorysubcategory(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return sc;
		}

}
