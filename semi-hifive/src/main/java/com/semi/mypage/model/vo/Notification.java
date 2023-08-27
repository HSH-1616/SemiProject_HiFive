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
public class Notification {
	private int notiCode;
	private String userId;
	private int productId;
	private int notitypeCode;
	private Date notiDate;
	private String readYn;
}
