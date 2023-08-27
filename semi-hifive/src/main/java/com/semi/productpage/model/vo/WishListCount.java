package com.semi.productpage.model.vo;

import com.semi.category.model.vo.Category;
import com.semi.mypage.model.vo.WishList;
import com.semi.product.model.vo.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListCount {
	private WishList wishlist;
	private int count;
}
