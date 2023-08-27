package com.semi.category.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import com.semi.category.dao.CategoryDao;
import com.semi.category.model.vo.Category;
import com.semi.category.model.vo.CategorySubCategory;

public class CategoryService {
	private CategoryDao dao = new CategoryDao();
	
	// 대표카테고리만 출력해주는 메소드
	public List<Category> Category(){
		Connection conn = getConnection();
		List<Category> category = dao.Category(conn);
		close(conn);
		return category;
	}	
	// 서브카테고리랑 대표카테고리 join한것
	public List<CategorySubCategory> SubCategoryList(){
		Connection conn = getConnection();
		List<CategorySubCategory> category = dao.SubCategoryList(conn);
		close(conn);
		return category;
	}
	// 카테고리 테이블에서 카테고리 이름을 찾아서 카테고리 이름과 카테고리 id값 출력해주는 메소드
	public Category CategoryName(String categoryname) {
		Connection conn = getConnection();
		Category c = dao.CategoryName(conn, categoryname);
		close(conn);
		return c;
	}
	// 대표 카테고리이름과 서브카테고리 이름만 나올수 있는 메소드
	public CategorySubCategory SubCategoryName(String subcategoryname) {
		Connection conn = getConnection();
		CategorySubCategory sc = dao.SubCategoryName(conn, subcategoryname);
		close(conn);
		return sc;
	}
	public CategorySubCategory SelectSubCategoryName(String subcategoryname) {
		Connection conn = getConnection();
		CategorySubCategory sc = dao.SelectSubCategoryName(conn, subcategoryname);
		close(conn);
		return sc;
	}
}
