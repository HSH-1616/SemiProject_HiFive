package com.semi.productlistlist.model.dao;

import static com.semi.common.JDBCTemplate.close;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.semi.category.model.vo.Category;
import com.semi.category.model.vo.SubCategory;
import com.semi.mypage.model.vo.WishList;
import com.semi.product.model.vo.ProductFile;
import com.semi.productlist.model.vo.ProductCategoryList;
import com.semi.productlist.model.vo.ProductCategoryTimeList;
import com.semi.productpage.model.vo.ProductCategory;
public class ProductCategoryListDao {
	private Properties sql = new Properties();
	{
		String path = ProductCategoryListDao.class.getResource("/sql/product/productchartpage_sql.properties").getPath();
		
		try {
			sql.load(new FileReader(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ProductCategoryTimeList getProduct(ResultSet rs) throws SQLException {
		return ProductCategoryTimeList.builder()
		.elapsedTime(rs.getLong("ELAPSED_TIME"))
//		.productAndSubCategoryname(rs.getString("PRODUCT_SUBCATEGORY_NAME"))
//		.categorySubCategoryname(rs.getString("CATEGORY_SUBCATEGORY_NAME"))
		.productCategoryList(ProductCategoryList.builder()
				.productId(rs.getInt("PRODUCT_ID"))
				.userId(rs.getString("USER_ID"))
				.productTitle(rs.getString("PRODUCT_TITLE"))
				.productStatus(rs.getString("PRODUCT_STATUS"))
				.sellStatus(rs.getString("SELL_STATUS"))
				.price(rs.getInt("PRICE"))
				.registTime(rs.getDate("REGIST_TIME"))
				.viewCount(rs.getInt("VIEW_COUNT"))
				.explanation(rs.getString("EXPLANATION"))
				.keyword(rs.getString("KEYWORD")!= null ? rs.getString("KEYWORD").split(",") : null)
				.subcategoryName(rs.getString("SUBCATEGORY_NAME"))
				.areaName(rs.getString("AREA_NAME")).build())
		.category(Category.builder()
					.categoryId(rs.getString("CATEGORY_ID"))
					.categoryName(rs.getString("CATEGORY_NAME")).build())
		.subCategory(SubCategory.builder()
					.subcategoryName(rs.getString("SUBCATEGORY_NAME")).build())
		.productfile(ProductFile.builder()
				.imageName(rs.getString("PRODUCT_IMAGE_NAME")).build())
		.build();
	}
	private WishList getWishList(ResultSet rs) throws SQLException {
		return WishList.builder()
				.wishUserId(rs.getString("WISH_USER_ID"))
				.productId(rs.getInt("PRODUCT_ID"))
				.build();
	}
	// 전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것
	public List<ProductCategoryTimeList> CategoryProductList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductCategoryTimeList> productlist = new ArrayList<>();
	try {
		pstmt = conn.prepareStatement(sql.getProperty("CategoryProductList"));
		pstmt.setInt(1, (cPage-1) * numPerpage + 1);
		pstmt.setInt(2, cPage * numPerpage);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			productlist.add(getProduct(rs));
			System.out.println("dao" + rs);
			System.out.println("dao" + productlist);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}return productlist;
	}
	public ProductCategoryTimeList selectProduct(Connection conn, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductCategoryTimeList p = null;
		String sql = this.sql.getProperty("selectProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				p = getProduct(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}
	
//	전체 상품리스트 가져오기 카테고리, 세부카테고리 모두 join해서 가져온것 페이징처리 	
	public int CategoryProductListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("CategoryProductListCount"));
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	// 선택한 상품페이지이동 메소드
	public ProductCategoryTimeList SelectProduct(Connection conn, int productId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductCategoryTimeList p = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("SelectProduct"));
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				p = getProduct(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return p;
	}
	//선택한 서브카테고리이름으로 찾아서 상품리스트 화면 출력
	public List<ProductCategoryTimeList> SelectSubCategoryList(Connection conn, int cPage, int numPerpage ,String subcategoryname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductCategoryTimeList> selectcategorylist = new ArrayList<>();
		System.out.println(selectcategorylist);
		try {
			pstmt = conn.prepareStatement(sql.getProperty("SelectSubCategoryList"));
			pstmt.setString(1, subcategoryname);
			pstmt.setInt(2, (cPage-1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			System.out.println("DAO" + selectcategorylist);
			if(rs.next()) {
				selectcategorylist.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return selectcategorylist;
	}
	
	
	
	public int SelectSubCategoryProductListCount(Connection conn, String subcategoryname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("SelectSubCategoryProductListCount"));
			pstmt.setString(1, subcategoryname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	//대표카테고리 클릭시 대표카테고리상품리스트들 출력
		public List<ProductCategoryTimeList> CategoryList(Connection conn, int cPage, int numPerpage, String categoryname){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> categorylist = new ArrayList<>();
			
			try {
				pstmt = conn.prepareStatement(sql.getProperty("CategoryList"));
				pstmt.setString(1, categoryname);
				pstmt.setInt(2, (cPage-1) * numPerpage + 1);
				pstmt.setInt(3, cPage * numPerpage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					categorylist.add(getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return categorylist;
		}
		//대표카테고리 클릭시 대표카테고리상품리스트들 출력 화면에 출력할 페이징
		public int CategoryCount(Connection conn, String categoryname) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			
			try {
				pstmt = conn.prepareStatement(sql.getProperty("CategoryCount"));
				pstmt.setString(1, categoryname);
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		public List<ProductCategoryTimeList> GetProductCondition(Connection conn, int cPage, int numPerpage, String conditions) {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<ProductCategoryTimeList> selectgetproduct = new ArrayList<>();
		    
		    String query=sql.getProperty("GetProductCondition").replace("#CONDITIONS#", conditions);
		    try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					selectgetproduct.add(getProduct(rs));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
		public int GetProductConditionCount(Connection conn, String conditions) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("GetProductConditionCount").replace("#CONDITIONS#", conditions));
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		public List<ProductCategoryTimeList> MaxpriceList(Connection conn, int cPage, int numPerpage, String conditions) {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<ProductCategoryTimeList> selectgetproduct = new ArrayList<>();
		    
		    String query=sql.getProperty("MaxpriceList").replace("#Max#", conditions);
		    try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					selectgetproduct.add(getProduct(rs));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
		public int MaxpriceListCount(Connection conn, String conditions ) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("MaxpriceListCount").replace("#Max#", conditions));
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		public List<ProductCategoryTimeList> MinpriceList(Connection conn, int cPage, int numPerpage, String conditions) {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<ProductCategoryTimeList> selectgetproduct = new ArrayList<>();
		    
		    String query=sql.getProperty("MinpriceList").replace("#Min#", conditions);
		    try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					selectgetproduct.add(getProduct(rs));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
		public int MinpriceListCount(Connection conn, String conditions ) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("MinpriceListCount").replace("#Min#", conditions));
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		public List<ProductCategoryTimeList> ViewCount(Connection conn, int cPage, int numPerpage, String conditions) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> selectgetproduct = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql.getProperty("ViewCount").replace("#VIEW#", conditions));
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					selectgetproduct.add(getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
		public int ViewCountAndCount(Connection conn, String conditions ) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("ViewCountAndCount").replace("#VIEW#", conditions));
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		
		
		public List<ProductCategoryTimeList> EntireMaxPrice(Connection conn, int cPage, int numPerpage) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> productlist = new ArrayList<>();
		
			try {
				pstmt = conn.prepareStatement(sql.getProperty("EntireMaxPrice"));
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					productlist.add(getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return productlist;
		}
		public List<ProductCategoryTimeList> EntireMinPrice(Connection conn, int cPage, int numPerpage) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> productlist = new ArrayList<>();
		
			try {
				pstmt = conn.prepareStatement(sql.getProperty("EntireMinPrice"));
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					productlist.add(getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return productlist;
		}
		public List<ProductCategoryTimeList> EntireViewCount(Connection conn, int cPage, int numPerpage) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> productlist = new ArrayList<>();
		
			try {
				pstmt = conn.prepareStatement(sql.getProperty("EntireViewCount"));
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					productlist.add(getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return productlist;
		}
		
		
		//좋아요 찾는 메소드
		public WishList Like(Connection conn, String loginId,int productId) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			WishList  w =  null;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("Like"));
				pstmt.setString(1, loginId);
				pstmt.setInt(2, productId);
				System.out.println(loginId);
				System.out.println(productId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					System.out.println(getWishList(rs));
					w=getWishList(rs);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return w;
		}
		//좋아요 등록
		public int updateLike(Connection conn, String loginId, int productId) {
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("updateLike"));
				pstmt.setString(1, loginId);
				pstmt.setInt(2, productId);
				result = pstmt.executeUpdate();
				System.out.println(result);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		//좋아요 삭제
		public int deleteLike(Connection conn, String loginId, int productId) {
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("deleteLike"));
				pstmt.setString(1, loginId);
				pstmt.setInt(2, productId);
				result = pstmt.executeUpdate();
				System.out.println(result);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		
		
		
		public List<ProductCategoryTimeList> Status(Connection conn, int cPage, int numPerpage ,String subcategoryname) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProductCategoryTimeList> selectcategorylist =new ArrayList();
			System.out.println("DAO" + selectcategorylist);
			try {
				pstmt = conn.prepareStatement(sql.getProperty("Status"));
				pstmt.setString(1, subcategoryname);
				pstmt.setInt(2, (cPage-1) * numPerpage + 1);
				pstmt.setInt(3, cPage * numPerpage);
				rs = pstmt.executeQuery();
				System.out.println("DAO" + selectcategorylist);
				while(rs.next()) {
					selectcategorylist.add( getProduct(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectcategorylist;
		}
		
		
		//상품상태 클릭시 전체상품상태리스트 출력 화면에 출력할 페이징
				public int StatusCount(Connection conn, String status) {
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int result = 0;
					
					try {
						pstmt = conn.prepareStatement(sql.getProperty("StatusCount"));
						pstmt.setString(1, status);
						rs = pstmt.executeQuery();
						if(rs.next()){
							result = rs.getInt(1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rs);
						close(pstmt);
					}return result;
				}
				public List<ProductCategoryTimeList> Price(Connection conn, int cPage, int numPerpage ,String price) {
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List<ProductCategoryTimeList> selectcategorylist = new ArrayList<>();
					try {
						pstmt = conn.prepareStatement(sql.getProperty("Price").replace("#PRICE#", price));
						pstmt.setInt(1, (cPage-1) * numPerpage + 1);
						pstmt.setInt(2, cPage * numPerpage);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							selectcategorylist.add(getProduct(rs));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rs);
						close(pstmt);
					}return selectcategorylist;
				}
				
				
				//상품상태 클릭시 전체상품상태리스트 출력 화면에 출력할 페이징
						public int PriceCount(Connection conn, String price) {
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int result = 0;
							System.out.println("DAO" + result);
							System.out.println(price);
							System.out.println(sql.getProperty("PriceCount"));
							try {
								pstmt = conn.prepareStatement(sql.getProperty("PriceCount").replace("#PRICE#", price));
								rs = pstmt.executeQuery();
								if(rs.next()){
									result = rs.getInt(1);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}finally {
								close(rs);
								close(pstmt);
							}return result;
						}
		
				public List<ProductCategoryTimeList> Area(Connection conn, int cPage, int numPerpage ,String area) {
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List<ProductCategoryTimeList> selectcategorylist = new ArrayList<>();
					try {
						pstmt = conn.prepareStatement(sql.getProperty("Area"));
						pstmt.setString(1, "%"+area+"%");
						pstmt.setInt(2, (cPage-1) * numPerpage + 1);
						pstmt.setInt(3, cPage * numPerpage);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							selectcategorylist.add(getProduct(rs));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						close(rs);
						close(pstmt);
					}return selectcategorylist;
				}
				
				//상품상태 클릭시 전체상품상태리스트 출력 화면에 출력할 페이징
						public int AreaCount(Connection conn, String area) {
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int result = 0;
							
							try {
								pstmt = conn.prepareStatement(sql.getProperty("AreaCount"));
								pstmt.setString(1, "%"+area+"%");
								rs = pstmt.executeQuery();
								if(rs.next()){
									result = rs.getInt(1);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}finally {
								close(rs);
								close(pstmt);
							}return result;
						}
		
		public List<WishList> wishlist(Connection conn){
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<WishList> selectgetproduct = new ArrayList<>();
		    
		    try {
			    pstmt = conn.prepareStatement(sql.getProperty("Wishlist"));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					selectgetproduct.add(getWishList(rs));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
						
						
						
	public List<ProductCategoryTimeList> Test(Connection conn, int cPage, int numPerpage, String test) {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<ProductCategoryTimeList> selectgetproduct = new ArrayList<>();
		    
		    String query=sql.getProperty("Test").replace("#TEST#", test);
		    try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, (cPage-1) * numPerpage + 1);
				pstmt.setInt(2, cPage * numPerpage);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					selectgetproduct.add(getProduct(rs));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return selectgetproduct;
		}
		public int TestCount(Connection conn, String test) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				pstmt = conn.prepareStatement(sql.getProperty("TestCount").replace("#TEST#", test));
				rs = pstmt.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return result;
		}
		
}
