package com.semi.productpage.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.mypage.model.vo.WishList;
import com.semi.product.model.vo.ProductFile;
import com.semi.productpage.dao.ProductDao;
import com.semi.productpage.model.vo.AjaxProductComment;
import com.semi.productpage.model.vo.ProductCategory;
import com.semi.productpage.model.vo.ProductCommentUser;
import com.semi.productpage.model.vo.ProductUser;
import com.semi.productpage.model.vo.WishListCount;

public class ProductPageService {
	
	ProductDao dao=new ProductDao();
		
	public ProductCategory selectProduct(int id,boolean isRead) {
		Connection conn=getConnection();
		ProductCategory selectProduct=dao.selectProduct(conn,id);
		if(selectProduct!=null&&!isRead) {
			int result=dao.updateViewCount(conn,id);
			if(result>0) {
				commit(conn);
				selectProduct.getProduct().setViewCount(selectProduct.getProduct().getViewCount()+1);
			}
			else rollback(conn);
		}
		close(conn);
		return selectProduct;		
	}
	
	public List<ProductFile> selectProductFile(int id){
		Connection conn=getConnection();
		List<ProductFile> list=dao.selectProductFile(conn,id);
		close(conn);
		return list;
	}
	
	public List<ProductUser> selectProductUser(int id){
		Connection conn=getConnection();
		List<ProductUser> list=dao.selectProductUser(conn,id);
		close(conn);
		return list;
	}
	
	public List<ProductCommentUser> selectAllProductComment(int id){
		Connection conn=getConnection();
		List<ProductCommentUser> list=dao.selectAllProductComment(conn,id);
		close(conn);
		return list;
	}
	
	public AjaxProductComment selectAjaxProductComment(int id){
		Connection conn=getConnection();
		AjaxProductComment p=dao.selectAjaxProductComment(conn,id);
		close(conn);
		return p;
	}
	
	public AjaxProductComment selectReAjaxProductComment(int id){
		Connection conn=getConnection();
		AjaxProductComment p=dao.selectReAjaxProductComment(conn,id);
		close(conn);
		return p;
	}
	
	public int insertAjaxProductComment(ProductCommentUser pc) {
		Connection conn=getConnection();
		int result=dao.insertAjaxProductComment(conn,pc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateAjaxProductComment(ProductCommentUser pc, int cn) {
		Connection conn=getConnection();
		int result=dao.updateAjaxProductComment(conn,pc,cn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public AjaxProductComment updateSelectAjaxProductComment(int cn){
		Connection conn=getConnection();
		AjaxProductComment p=dao.updateSelectAjaxProductComment(conn,cn);
		close(conn);
		return p;
	}
	
	public int deleteProductComment(int cn) {
		Connection conn=getConnection();
		int result=dao.deleteProductComment(conn,cn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<WishListCount> wishListCount(int id){
		Connection conn=getConnection();
		List<WishListCount> w=dao.wishListCount(conn,id);
		close(conn);
		return w;
	}
	
	public List<WishList> ajaxHeart(String loginId, int id){
		Connection conn=getConnection();
		List<WishList> w=dao.ajaxHeart(conn,loginId,id);
		close(conn);
		return w;
	}
	
	public int updateAjaxHeart(String loginId, int id) {
		Connection conn=getConnection();
		int result=dao.updateAjaxHeart(conn,loginId,id);
		if(result>0) {
			commit(conn);
			List<WishListCount> w=dao.wishListCount(conn,id);
			result=w.get(0).getCount();
		}
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteAjaxHeart(String loginId, int id) {
		Connection conn=getConnection();
		int result=dao.deleteAjaxHeart(conn,loginId,id);
		if(result>0) {
			commit(conn);
			List<WishListCount> w=dao.wishListCount(conn,id);
			result=w.size()>0?w.get(0).getCount():0;
		}
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertPay(int id, String user) {
		Connection conn=getConnection();
		int result=dao.insertPay(conn,id,user);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateStatus(int id) {
		Connection conn=getConnection();
		int result=dao.updateStatus(conn,id);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteProduct(int id) {
		Connection conn=getConnection();
		int result=dao.deleteProduct(conn,id);
		System.out.println(result);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	

}
