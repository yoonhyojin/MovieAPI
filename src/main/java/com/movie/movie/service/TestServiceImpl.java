package com.movie.movie.service;

import com.movie.movie.entity.MemberTest;
import com.movie.movie.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 참고용 ServiceImpl Layer
 */
@Service("TestService")
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

	private final TestRepository testRepository;

	/**
	 * Test Sample - MEMBER_ID 를 이용해 데이터 조회
	 * @param memberId MEMBER_ID
	 * @return 조회 결과값
	 */
	@Override
	public MemberTest findByMemberId(Long memberId) {
		return testRepository.findByMemberId(memberId).orElse(null);
	}
}
