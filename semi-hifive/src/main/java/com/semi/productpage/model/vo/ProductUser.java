package com.semi.productpage.model.vo;


import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.Product;
import com.semi.product.model.vo.ProductFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUser {
	private Product product;
	private Member member;
	private ProductFile file;
	private int count;
}
