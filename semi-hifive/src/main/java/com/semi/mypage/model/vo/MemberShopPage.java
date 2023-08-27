package com.semi.mypage.model.vo;

import com.semi.member.model.vo.Member;
import com.semi.productpage.model.vo.ShopPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
@Builder
public class MemberShopPage {
	   private Member member;
	   private ShopPage shopPage;
}
