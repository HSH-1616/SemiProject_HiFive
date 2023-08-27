package com.semi.main.model.vo;

import java.util.List;

import com.semi.mypage.model.vo.WishList;
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
public class ProductElapsedTime {
	private Product product;
	private long elapsedTime;
	private ProductFile productFile;
	//private List<String> likeMembers;
	private WishList wishList;
}
