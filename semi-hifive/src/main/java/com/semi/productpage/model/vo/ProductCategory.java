package com.semi.productpage.model.vo;

import com.semi.category.model.vo.Category;
import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {
	private Product product;
	private Category category;
	private Member member;
}
