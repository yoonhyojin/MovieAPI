package com.movie.movie.repository;

import com.movie.movie.entity.MemberTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 참고용 Repository Layer
 * JPA 사용햐기 위해서는 Repository(Dao) Layer 에서 JpaRepository 상속받아서 사용해야함.
 */
public interface TestRepository extends JpaRepository<MemberTest, Long> {

	/**
	 * Test Sample - MEMBER_ID 를 이용해 데이터 조회
	 * @param memberId MEMBER_ID
	 * @return 조회 결과값
	 */
	MemberTest findByMemberId(Long memberId);

}
