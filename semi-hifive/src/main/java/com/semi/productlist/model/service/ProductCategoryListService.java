package com.semi.productlist.model.service;
import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.mypage.model.vo.WishList;
import com.semi.productlist.model.vo.ProductCategoryTimeList;
import com.semi.productlistlist.model.dao.ProductCategoryListDao;


public class ProductCategoryListService {
	private ProductCategoryListDao dao = new ProductCategoryListDao();
//	전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것	
	public List<ProductCategoryTimeList> CategoryProductList(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<ProductCategoryTimeList> list = dao.CategoryProductList(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
//	전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것 페이징처리 	
	public int CategoryProductListCount() {
		Connection conn = getConnection();
		int result = dao.CategoryProductListCount(conn);
		close(conn);
		return result;
	}
	// 선택한 상품페이지상세페이지로 이동 메소드
	public ProductCategoryTimeList SelectProduct(int productId) {
		Connection conn = getConnection();
		ProductCategoryTimeList p = dao.SelectProduct(conn, productId);
		close(conn);
		return p;
	}
	
	//서브카테고리 이름을 상품 리스트 찾아서 가져오기
	public List<ProductCategoryTimeList> SelectSubCategoryList(int cPage, int numPerpage ,String subcategoryname) {
		Connection conn = getConnection();
		List<ProductCategoryTimeList> p = dao.SelectSubCategoryList(conn, cPage, numPerpage, subcategoryname);
		close(conn);
		return p;
	}
	//서브카테고리 이름을 상품 리스트 찾아서 가져오기 페이징처리
	public int SelectSubCategoryProductListCount(String subcategoryname){
		Connection conn = getConnection();
		int result = dao.SelectSubCategoryProductListCount(conn, subcategoryname);
		close(conn);
		return result;
	}
	//대표카테고리 선택시 대표카테고리 상품리스트 출력
	public List<ProductCategoryTimeList> CategoryList(int cPage, int numPerpage, String categoryname) {
			Connection conn = getConnection();
			List<ProductCategoryTimeList> categorylist = dao.CategoryList(conn, cPage, numPerpage, categoryname);
			close(conn);
			return categorylist;
	}
	//대표카테고리 선택시 대표카테고리 상품리스트 출력 페이징처리
	public int CategoryCount(String categoryname) {
			Connection conn = getConnection();
			int selectcategory = dao.CategoryCount(conn, categoryname);
			close(conn);
			return selectcategory;
	}
	//where 절 or 로 묶인메소드
	public List<ProductCategoryTimeList> GetProductCondition(String conditions, int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<ProductCategoryTimeList> selectgetproduct = dao.GetProductCondition(conn, cPage, numPerpage, conditions);
		close(conn);
		return selectgetproduct;
	}
	public int GetProductConditionCount(String conditions) {
		Connection conn = getConnection();
		int result = dao.GetProductConditionCount(conn,conditions);
		close(conn);
		return result;
	}
	//where 절 or 로 묶인메소드
		public List<ProductCategoryTimeList> MaxpriceList(String conditions, int cPage, int numPerpage) {
			Connection conn = getConnection();
			List<ProductCategoryTimeList> selectgetproduct = dao.MaxpriceList(conn, cPage, numPerpage, conditions);
			close(conn);
			return selectgetproduct;
		}
		public int MaxpirceListCount(String conditions) {
			Connection conn = getConnection();
			int result = dao.MaxpriceListCount(conn,conditions);
			close(conn);
			return result;
		}
		//where 절 or 로 묶인메소드
		public List<ProductCategoryTimeList> MinxpriceList(String conditions, int cPage, int numPerpage) {
			Connection conn = getConnection();
			List<ProductCategoryTimeList> selectgetproduct = dao.MinpriceList(conn, cPage, numPerpage, conditions);
			close(conn);
			return selectgetproduct;
		}
		public int MinpriceListCount(String conditions) {
			Connection conn = getConnection();
			int result = dao.MinpriceListCount(conn,conditions);
			close(conn);
			return result;
		}
		
//		전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것	
		public List<ProductCategoryTimeList> EntireMaxPrice(int cPage, int numPerpage){
			Connection conn = getConnection();
			List<ProductCategoryTimeList> list = dao.EntireMaxPrice(conn, cPage, numPerpage);
			close(conn);
			return list;
		}
//		전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것	
		public List<ProductCategoryTimeList> EntireMinPrice(int cPage, int numPerpage){
			Connection conn = getConnection();
			List<ProductCategoryTimeList> list = dao.EntireMinPrice(conn, cPage, numPerpage);
			close(conn);
			return list;
		}
		public List<ProductCategoryTimeList> EntireViewCount(int cPage, int numPerpage){
			Connection conn = getConnection();
			List<ProductCategoryTimeList> list = dao.EntireViewCount(conn, cPage, numPerpage);
			close(conn);
			return list;
		}
		public List<ProductCategoryTimeList> ViewCount(String conditions, int cPage, int numPerpage) {
			Connection conn = getConnection();
			List<ProductCategoryTimeList> selectgetproduct = dao.ViewCount(conn, cPage, numPerpage, conditions);
			close(conn);
			return selectgetproduct;
		}
		public int ViewCountAndCount(String conditions) {
			Connection conn = getConnection();
			int result = dao.ViewCountAndCount(conn,conditions);
			close(conn);
			return result;
		}
		//좋아요 확인
		public WishList Like(String loginId, int productId){
			Connection conn = getConnection();
			WishList wish = dao.Like(conn,loginId, productId);
			close(conn);
			return wish;
		}
		//좋아요 등록
		public int updateLike(String loginId, int productId) {
			Connection conn=getConnection();
			int result=dao.updateLike(conn,loginId, productId);
			if(result>0) commit(conn);
			else rollback(conn);
			close(conn);
			return result;
		}
		//좋아요 삭제
		public int deleteLike(String loginId, int productId) {
			Connection conn = getConnection();
			int result = dao.deleteLike(conn,loginId, productId);
			if(result > 0) commit(conn);
			else rollback(conn);
			close(conn);
			return result;
		}
		
		//상품상태 리스트
		public List<ProductCategoryTimeList> Status(int cPage, int numPerpage ,String status) {
			Connection conn = getConnection();
			List<ProductCategoryTimeList> p = dao.Status(conn, cPage, numPerpage, status);
			close(conn);
			return p;
		}
//		상품상태리스트
		public int StatusCount(String status){
			Connection conn = getConnection();
			int result = dao.StatusCount(conn, status);
			close(conn);
			return result;
		}
		
		//상품가격 리스트
				public List<ProductCategoryTimeList> Price(int cPage, int numPerpage ,String price) {
					Connection conn = getConnection();
					List<ProductCategoryTimeList> p = dao.Price(conn, cPage, numPerpage, price);
					close(conn);
					return p;
				}
//				상품가격리스트
				public int PriceCount(String price){
					Connection conn = getConnection();
					int result = dao.PriceCount(conn, price);
					close(conn);
					return result;
				}
		
				//상품상태 리스트
				public List<ProductCategoryTimeList> Area(int cPage, int numPerpage ,String areaname) {
					Connection conn = getConnection();
					List<ProductCategoryTimeList> p = dao.Area(conn, cPage, numPerpage, areaname);
					close(conn);
					return p;
				}
//				상품상태리스트
				public int AreaCount(String areaname){
					Connection conn = getConnection();
					int result = dao.AreaCount(conn, areaname);
					close(conn);
					return result;
				}
				public List<WishList> wishlist(){
					Connection conn = getConnection();
					List<WishList> list = dao.wishlist(conn);
					close(conn);
					return list;
				}
	
	public List<ProductCategoryTimeList> Test(String test, int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<ProductCategoryTimeList> selectgetproduct = dao.GetProductCondition(conn, cPage, numPerpage, test);
		close(conn);
		return selectgetproduct;
	}
	public int TestCount(String test) {
		Connection conn = getConnection();
		int result = dao.GetProductConditionCount(conn,test);
		close(conn);
		return result;
	}
	
}
