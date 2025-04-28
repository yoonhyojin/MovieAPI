package com.movie.movie.util.exception;

import com.movie.movie.util.response.enums.StatusCode;
import lombok.Getter;

/**
 * 애플리케이션 전반에서 사용하는 커스텀 예외 클래스
 * 각 예외에 대응하는 StatusCode를 포함함
 */
@Getter
public class CommonException extends RuntimeException {

	private final StatusCode statusCode;

	/**
	 * StatusCode를 받아서 CommonException을 생성
	 * @param statusCode 발생한 예외에 해당하는 상태 코드
	 */
	public CommonException(final StatusCode statusCode) {
		super(statusCode.getMessage());
		this.statusCode = statusCode;
	}

}
