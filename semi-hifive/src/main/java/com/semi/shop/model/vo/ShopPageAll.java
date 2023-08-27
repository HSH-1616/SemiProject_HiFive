package com.semi.shop.model.vo;

import com.semi.member.model.vo.Member;
import com.semi.mypage.model.vo.Review;
import com.semi.mypage.model.vo.Trade;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductFile;
import com.semi.productpage.model.vo.ShopPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopPageAll {
	private ShopPage shopPage;
	private Product product;
	private Member member;
	private Trade trade;
	private Review review;
	private ProductFile productFile;
	private int productCount;
	private int tradeCount;
	private int reviewCount;
}
