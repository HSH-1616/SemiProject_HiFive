package com.semi.mypage.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.mypage.dao.MypageProductDao;
import com.semi.mypage.model.vo.MemberComment;
import com.semi.mypage.model.vo.MemberWishList;
import com.semi.mypage.model.vo.ProductList;
import com.semi.mypage.model.vo.ReviewTrade;

public class MypageProductService {
	private MypageProductDao dao = new MypageProductDao();

	// 판매목록
	public List<ProductList> selectSellListByUserId(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.selectSellListByUserId(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 구매목록
	public List<ProductList> selectBuyListByUserId(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.selectBuyListByUserId(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 판매상태 카운트(전체)
	public int countSellStatusAll(String userId) {
		Connection conn = getConnection();
		int count = dao.countSellStatusAll(conn, userId);
		close(conn);
		return count;
	}

	// 판매상태 카운트(판매중, 예약중, 판매완료)
	public int countSellStatusSell(String userId, String status) {
		Connection conn = getConnection();
		int count = dao.countSellStatusSell(conn, userId, status);
		close(conn);
		return count;
	}

	// 성사된 거래수
	public int countTrade(String userId) {
		Connection conn = getConnection();
		int count = dao.countTrade(conn, userId);
		close(conn);
		return count;
	}

	// 구매내역 오래된순
	public List<ProductList> BuyListSortAsc(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.BuyListSortAsc(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 페이징(구매내역 상품수)
	public int countBuyList(String userId) {
		Connection conn = getConnection();
		int count = dao.countBuyList(conn, userId);
		close(conn);
		return count;
	}

	// 페이징(판매내역 판매중 상품수)
	public int countSellList(String userId) {
		Connection conn = getConnection();
		int count = dao.countSellList(conn, userId);
		close(conn);
		return count;
	}

	// 판매상태 변경(ajax)
	public int changeSelectValue(String value, String productId) {
		Connection conn = getConnection();
		int result = dao.changeSelectValue(conn, value, productId);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 상품 삭제
	public int deleteProduct(String productId) {
		Connection conn = getConnection();
		int result = dao.deleteProduct(conn, productId);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 판매상태 판매중인 상품 리스트
	public List<ProductList> sellStatusSell(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.sellStatusSell(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 판매상태 예약중인 상품 리스트
	public List<ProductList> resStatusSell(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.resStatusSell(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 판매상태 판매완료인 상품 리스트
	public List<ProductList> solStatusSell(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<ProductList> p = dao.solStatusSell(conn, cPage, numPerpage, userId);
		close(conn);
		return p;
	}

	// 찜목록 카운트
	public int countWishList(String userId) {
		Connection conn = getConnection();
		int count = dao.countWishList(conn, userId);
		close(conn);
		return count;
	}

	// 찜목록 리스트
	public List<MemberWishList> selectWishListByUserId(int cPage, int numPerpage, String userId) {
		Connection conn = getConnection();
		List<MemberWishList> mw = dao.selectWishListByUserId(conn, cPage, numPerpage, userId);
		close(conn);
		return mw;
	}
	
	// 찜목록 리스트(페이징x)
		public List<MemberWishList> selectWishListAllByUserId(String userId) {
			Connection conn = getConnection();
			List<MemberWishList> mw = dao.selectWishListAllByUserId(conn, userId);
			close(conn);
			return mw;
		}

	// 판매자 온도 수정
	public int sellerScore(String productId, double changeTem) {
		Connection conn = getConnection();
		int result = dao.sellerScore(conn, productId, changeTem);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 거래후기 저장
	public int insertReview(String productId, double reviewScore, String reviewMsg) {
		Connection conn = getConnection();
		int result = dao.insertReview(conn, productId, reviewScore, reviewMsg);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 거래후기 조회
	public List<ReviewTrade> selectReview(String buyerId) {
		Connection conn = getConnection();
		List<ReviewTrade> productId = dao.selectReview(conn, buyerId);
		close(conn);
		return productId;
	}

	// 찜목록 삭제
	public int deleteWishList(String userId, String productId) {
		Connection conn = getConnection();
		int result = dao.deleteWishList(conn, userId, productId);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 찜목록 추가
	public int insertWishList(String userId, String productId) {
		Connection conn = getConnection();
		int result = dao.insertWishList(conn, userId, productId);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 판매자 현재 온도 조회
	public double nowTemperature(String productId) {
		Connection conn = getConnection();
		double tem = dao.nowTemperature(conn, productId);
		close(conn);
		return tem;
	}

	// 댓글 리스트 가져오기
	public List<MemberComment> commentList(String productId, String userId) {
		Connection conn = getConnection();
		List<MemberComment> mc = dao.commentList(conn, productId, userId);
		close(conn);
		return mc;
	}
	
	// 거래 추가
	public int insertTrade(String productId, String buyerId) {
		Connection conn = getConnection();
		int result = dao.insertTrade(conn, productId, buyerId);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	// 상품별 리뷰 조회
	public ReviewTrade selectReviewByProductId(String productId) {
		Connection conn = getConnection();
		ReviewTrade p = dao.selectReviewByProductId(conn, productId);
		close(conn);
		return p;
	}
}
