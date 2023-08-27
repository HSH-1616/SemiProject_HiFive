package com.semi.mypage.dao;

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
import com.semi.category.model.vo.SubCategory;
import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.MemberComment;
import com.semi.mypage.model.vo.MemberWishList;
import com.semi.mypage.model.vo.ProductList;
import com.semi.mypage.model.vo.Review;
import com.semi.mypage.model.vo.ReviewTrade;
import com.semi.mypage.model.vo.Trade;
import com.semi.mypage.model.vo.WishList;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductComment;
import com.semi.product.model.vo.ProductFile;

public class MypageProductDao {
	private Properties sql = new Properties();

	public MypageProductDao() {
		String path = MypageMemberDao.class.getResource("/sql/mypage/productsql.properties").getPath();
		try (FileReader fr = new FileReader(path)) {
			sql.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 판매목록
	public List<ProductList> selectSellListByUserId(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectSellListByUserId"));
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				list.add(getProductSellList(rs));
//			    int wishCount = rs.getInt("WISHCOUNT");
				ProductList product = getProductSellList(rs);
				int wishCount = rs.getInt("WISHCOUNT");
				product.setWishCount(wishCount);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 구매목록
	public List<ProductList> selectBuyListByUserId(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBuyListByUserId"));
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductList pl = getProductBuyList(rs);
				pl.setReview(Review.builder().reviewId(rs.getInt("review_id")).build());
				list.add(pl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 구매내역 오래된순
	public List<ProductList> BuyListSortAsc(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("BuyListSortAsc"));
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductList pl = getProductBuyList(rs);
				pl.setReview(Review.builder().reviewId(rs.getInt("review_id")).build());
				list.add(pl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 판매상태 전체 카운트
	public int countSellStatusAll(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countSellStatusAll"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return total;
	}

	// 판매상태 카운트(판매중, 예약중, 판매완료)
	public int countSellStatusSell(Connection conn, String userId, String status) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countSellStatus"));
			pstmt.setString(1, status);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return total;
	}

	// 성사된 거래수
	public int countTrade(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int trade = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countTrade"));
			pstmt.setString(1, userId);
			pstmt.setString(2, "판매완료");
			rs = pstmt.executeQuery();
			if (rs.next())
				trade = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return trade;
	}

	// 페이징처리(구매내역 상품 수)
	public int countBuyList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalData = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countBuyList"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalData = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return totalData;
	}

	// 페이징처리(판매내역 판매중 상품 수)
	public int countSellList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalData = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countSellList"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalData = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return totalData;
	}

	// 판매상태 변경
	public int changeSelectValue(Connection conn, String value, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("changeSelectValue"));
			pstmt.setString(1, value);
			pstmt.setString(2, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 상품삭제
	public int deleteProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteProduct"));
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 판매상태가 판매중인 상품 리스트
	public List<ProductList> sellStatusSell(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("sellStatus"));
			pstmt.setString(1, userId);
			pstmt.setString(2, "판매중");
			pstmt.setInt(3, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(4, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductSellList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 판매상태가 예약중인 상품 리스트
	public List<ProductList> resStatusSell(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("resStatus"));
			pstmt.setString(1, userId);
			pstmt.setString(2, "예약중");
			pstmt.setInt(3, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(4, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductSellList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 판매상태가 판매완료인 상품 리스트
	public List<ProductList> solStatusSell(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("solStatus"));
			pstmt.setString(1, userId);
			pstmt.setString(2, "판매완료");
			pstmt.setInt(3, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(4, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getProductSellList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 찜목록 카운트
	public int countWishList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalData = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("countWishList"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalData = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return totalData;
	}

	// 찜목록 리스트
	public List<MemberWishList> selectWishListByUserId(Connection conn, int cPage, int numPerpage, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberWishList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectWishListByUserId"));
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getMemberWishList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 찜목록 리스트(페이징 x)
	public List<MemberWishList> selectWishListAllByUserId(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberWishList> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectWishListAllByUserId"));
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(getMemberWishList(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 판매자 온도 수정
	public int sellerScore(Connection conn, String productId, double changeTem) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("sellerScore"));
			pstmt.setDouble(1, changeTem);
			pstmt.setString(2, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 거래후기 저장
	public int insertReview(Connection conn, String productId, double reviewScore, String reviewMsg) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertReview"));
			pstmt.setString(1, productId);
			pstmt.setDouble(2, reviewScore);
			pstmt.setString(3, reviewMsg);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 거래후기 가져오기
	public List<ReviewTrade> selectReview(Connection conn, String buyerId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewTrade> productId = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectReview"));
			pstmt.setString(1, buyerId);
			rs = pstmt.executeQuery();
			while (rs.next())
				productId.add(getReviewTrade(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return productId;
	}

	// 찜목록 삭제
	public int deleteWishList(Connection conn, String userId, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteWishList"));
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 찜목록 추가
	public int insertWishList(Connection conn, String userId, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertWishList"));
			pstmt.setString(1, userId);
			pstmt.setString(2, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 판매자 현재 온도 조회
	public double nowTemperature(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double nowTem = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("nowTemperature"));
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next())
				nowTem = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return nowTem;
	}

	// 댓글 리스트 가져오기
	public List<MemberComment> commentList(Connection conn, String productId, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberComment> mc = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectCommentList"));
			pstmt.setString(1, productId);
			pstmt.setString(2, "%" + userId + "%");
			rs = pstmt.executeQuery();
			while (rs.next())
				mc.add(getMemberComment(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return mc;
	}

	// 거래자 선택
	public int insertTrade(Connection conn, String productId, String buyerId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertTrade"));
			pstmt.setString(1, productId);
			pstmt.setString(2, buyerId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 상품별 리뷰 조회
	public ReviewTrade selectReviewByProductId(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewTrade rt = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectReviewByProductId"));
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next())
				rt = getReviewTrade(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return rt;
	}

	private ProductList getProductSellList(ResultSet rs) throws SQLException {
		return ProductList.builder()
				.product(Product.builder().productId(rs.getInt("product_Id")).userId(rs.getString("user_Id"))
						.title(rs.getString("product_Title")).productStatus(rs.getString("product_Status"))
						.sellStatus(rs.getString("sell_Status")).price(rs.getInt("price"))
						.registTime(rs.getDate("regist_Time")).viewCount(rs.getInt("view_Count"))
						.subCategoryName(rs.getString("subcategory_Name")).build())
				.member(Member.builder().userId(rs.getString("user_Id")).build())
				.subCategory(SubCategory.builder().subcategoryName(rs.getString("subcategory_Name")).build())
				.category(Category.builder().categoryName(rs.getString("category_Name")).build())
				.productfile(ProductFile.builder().imageName(rs.getString("product_image_name")).build()).build();
	}

	private ProductList getProductBuyList(ResultSet rs) throws SQLException {
		return ProductList.builder()
				.product(Product.builder().productId(rs.getInt("product_Id")).userId(rs.getString("user_Id"))
						.title(rs.getString("product_Title")).productStatus(rs.getString("product_Status"))
						.sellStatus(rs.getString("sell_Status")).price(rs.getInt("price"))
						.registTime(rs.getDate("regist_Time")).viewCount(rs.getInt("view_Count"))
						.subCategoryName(rs.getString("subcategory_Name")).build())
				.member(Member.builder().userId(rs.getString("user_Id")).nickName(rs.getString("nickName")).build())
				.subCategory(SubCategory.builder().subcategoryName(rs.getString("subcategory_Name")).build())
				.category(Category.builder().categoryName(rs.getString("category_Name")).build())
				.trade(Trade.builder().sellDate(rs.getDate("sell_Date")).build())
				.productfile(ProductFile.builder().imageName(rs.getString("product_image_name")).build()).build();
	}

	private MemberWishList getMemberWishList(ResultSet rs) throws SQLException {
		return MemberWishList.builder()
				.product(Product.builder().productId(rs.getInt("product_Id")).userId(rs.getString("user_Id"))
						.title(rs.getString("product_Title")).productStatus(rs.getString("product_Status"))
						.sellStatus(rs.getString("sell_Status")).price(rs.getInt("price"))
						.registTime(rs.getDate("regist_Time")).viewCount(rs.getInt("view_Count"))
						.subCategoryName(rs.getString("subcategory_Name")).build())
				.subCategory(SubCategory.builder().subcategoryName(rs.getString("subcategory_Name")).build())
				.category(Category.builder().categoryName(rs.getString("category_Name")).build())
				.wishList(WishList.builder().productId(rs.getInt("product_Id")).build())
				.productfile(ProductFile.builder().imageName(rs.getString("product_image_name")).build()).build();

	}

	private ReviewTrade getReviewTrade(ResultSet rs) throws SQLException {
		return ReviewTrade.builder().review(Review.builder().tradeId(rs.getInt("trade_id")).build())
				.trade(Trade.builder().tradeId(rs.getInt("trade_id")).productId(rs.getInt("product_id"))
						.buyerId(rs.getString("buyer_id")).build())
				.build();
	}

	private MemberComment getMemberComment(ResultSet rs) throws SQLException {
		return MemberComment.builder()
				.member(Member.builder().userId(rs.getString("user_Id")).nickName(rs.getString("nickName"))
						.profileImg(rs.getString("profile_Img")).build())
				.productComment(ProductComment.builder().build()).build();
	}
}