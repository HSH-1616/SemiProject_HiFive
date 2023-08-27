package com.semi.mypage.model.vo;

import com.semi.member.model.vo.Member;
import com.semi.product.model.vo.ProductComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberComment {
	private Member member;
	private ProductComment productComment;
}
