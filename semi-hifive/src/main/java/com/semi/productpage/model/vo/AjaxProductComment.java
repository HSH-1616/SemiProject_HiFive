package com.semi.productpage.model.vo;

import java.sql.Date;

import com.semi.product.model.vo.ProductComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AjaxProductComment {
	private String userId;
	private int productId;
	private int commentNo;
	private int commentLevel;
	private String content;
	private int commentRef;
	private Date enrollDate;
	private String nickName;
	private String profileImg;
}
