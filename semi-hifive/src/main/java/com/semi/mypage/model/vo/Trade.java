package com.semi.mypage.model.vo;

import java.sql.Date;

import com.semi.productpage.model.vo.ShopPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trade {
	private int tradeId;
	private int productId;
	private String buyerId;
	private Date sellDate;
}
