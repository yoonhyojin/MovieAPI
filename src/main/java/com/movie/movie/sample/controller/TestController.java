package com.movie.movie.sample.controller;

import com.movie.movie.sample.dto.ParseTestDto;
import com.movie.movie.sample.dto.TestDto;
import com.movie.movie.sample.entity.MemberTest;
import com.movie.movie.sample.service.TestService;
import com.movie.movie.util.connection.RestTemplateUtil;
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

	private final RestTemplateUtil restTemplateUtil;

	/**
	 * Health Chk - Success
	 * @return 처리 결과
	 */
	@GetMapping	("/success")
	public CommonResponse<String> success() {
		// 처리결과가 정상인 경우는 StatusCode.OK (200 OK), 처리 결과, null 로 값을 반환처리 해준다.
		return new CommonResponse<>(StatusCode.OK, "SUCCESS", null);
	}

	/**
	 * Health Chk - Exception
	 */
	@GetMapping("/fail")
	public void fail() {
		// 처리중 Exception 이 발생하면 CommonException 에 Error Status Code 를 담아서 리턴해준다.
		// 각 상태에 대한 처리는 StatusCode.java Enum 참고
		throw new CommonException(StatusCode.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Sample - 회원 정보 조회
	 * @param memberId	조회 대상 MEMBER ID
	 * @return 처리 결과
	 */
	@GetMapping("/{memberId}")
	public CommonResponse<TestDto> getMember(@PathVariable String memberId) {
		log.info("getMember - memberId: {}", memberId);

		// 1. 회원조회
		MemberTest memberTest = testService.findByMemberId(Long.valueOf(memberId));

		// 2. 조회한 Entity 결과 값을 만들어주기 위해 Dto 로 변환
		if (memberTest != null) {
			log.info("getMember - memberTest: {}", memberTest.toString());

			TestDto testDto = TestDto.builder()
				.memberId(memberTest.getMemberId())
				.userId(memberTest.getUserId())
				.password(memberTest.getPassword())
				.nickName(memberTest.getNickName())
				.build();

			return new CommonResponse<>(StatusCode.OK, testDto, null);
		} else {
			// 데이터가 없는 경우
			// 보통 단건 조회의 경우 404 NotFound, 다건 조회의 경우 200 Status 에 결과는 빈 리스트로 담에서 리턴해준다.
			return new CommonResponse<>(StatusCode.NOT_FOUND, null, null);
		}
	}

	/**
	 * Sample - RestTemplateUtil 을 이용한 API 호출 + Data Binding
	 * @param userId	조회 대상 USERID
	 * @return	처리 결과
	 */
	@GetMapping("/parsing/{userId}")
	public CommonResponse<ParseTestDto> parsing(@PathVariable String userId) {
		log.info("parsing userId: {}", userId);

		final String URL = "https://jsonplaceholder.typicode.com/posts/" + userId;

		// 1. HTTP GET 방식 호출
		ParseTestDto parseTestDto = restTemplateUtil.getConnection(URL, ParseTestDto.class);

		return new CommonResponse<>(StatusCode.OK, parseTestDto, null);
	}
}
