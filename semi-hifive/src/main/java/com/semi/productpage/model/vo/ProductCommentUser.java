package com.semi.productpage.model.vo;

import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCommentUser {
	private Product product;
	private ProductComment productComment;
	private Member member;	
	private int count;
}

