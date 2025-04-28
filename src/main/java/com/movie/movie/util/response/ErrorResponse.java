package com.movie.movie.util.response;

import com.movie.movie.util.response.enums.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * API 실패 응답 시 에러 정보를 담는 클래스
 */
@RequiredArgsConstructor
@Getter
public class ErrorResponse {

	/**
	 * 실패에 대한 상태 코드
	 */
	private final StatusCode statusCode;
}
