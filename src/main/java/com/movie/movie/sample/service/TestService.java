package com.movie.movie.sample.service;

import com.movie.movie.sample.entity.MemberTest;

/**
 * 참고용 Service Layer
 */
public interface TestService {

	/**
	 * Test Sample - MEMBER_ID 를 이용해 데이터 조회
	 * @param memberId MEMBER_ID
	 * @return 조회 결과값
	 */
	MemberTest findByMemberId(Long memberId);
}
