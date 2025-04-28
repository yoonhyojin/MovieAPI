package com.movie.movie.controller;

import com.movie.movie.dto.TestDto;
import com.movie.movie.entity.MemberTest;
import com.movie.movie.service.TestService;
import com.movie.movie.util.exception.CommonException;
import com.movie.movie.util.response.CommonResponse;
import com.movie.movie.util.response.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 참고용 Controller Layer
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

	private final TestService testService;

	/**
	 * Health Chk - Success
	 * @return
	 */
	@GetMapping	("/success")
	public CommonResponse<String> success() {
		return new CommonResponse(StatusCode.OK, "SUCCESS", null);
	}

	/**
	 * Health Chk - Exception
	 */
	@GetMapping("/fail")
	public void fail() {
		throw new CommonException(StatusCode.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Test Sample - 회원 정보 조회
	 * @param memberId
	 * @return
	 */
	@GetMapping("/{memberId}")
	public CommonResponse<TestDto> getMember(@PathVariable String memberId) {
		log.info("getMember - memberId: {}", memberId);

		// 1. 회원조회
		MemberTest memberTest = testService.findByMemberId(Long.valueOf(memberId));

		log.info("getMember - memberTest: {}", memberTest.toString());

		TestDto testDto = null;

		// 2. 조회한 Entity 결과 값을 만들어주기 위해 Dto 로 변환
		if (memberTest != null) {
			testDto = TestDto.builder()
				.memberId(memberTest.getMemberId())
				.userId(memberTest.getUserId())
				.password(memberTest.getPassword())
				.nickName(memberTest.getNickName())
				.build();
		} else {
			// 데이터가 없는 경우
		}

		return new CommonResponse<>(StatusCode.OK, testDto, null);
	}
}
