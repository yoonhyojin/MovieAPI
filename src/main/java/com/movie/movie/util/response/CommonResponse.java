package com.movie.movie.util.response;

import com.movie.movie.util.response.enums.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 모든 API 응답을 감싸는 공통 응답 클래스
 * 성공 데이터(content)나 실패 데이터(errorResponse)를 포함할 수 있음
 *
 * @param <T> content의 타입
 */
@RequiredArgsConstructor
@Getter
public class CommonResponse<T> {

	/**
	 * 응답 상태를 나타내는 StatusCode
	 */
	private final StatusCode statusCode;

	/**
	 * 성공 시 반환할 실제 데이터
	 */
	private final T content;

	/**
	 * 실패 시 반환할 에러 정보
	 */
	private final ErrorResponse errorResponse;
}
