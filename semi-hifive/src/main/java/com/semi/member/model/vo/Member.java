package com.semi.member.model.vo;

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
public class Member {
	   private String userId;
	   private String email;
	   private String nickName;
	   private String password;
	   private String userName;
	   private int declareCount;
	   private Date enrollDate;
	   private String auth;
	   private double temperature;
	   private String profileImg;
}
