package com.movie.movie.util.advice;

import com.movie.movie.util.exception.CommonException;
import com.movie.movie.util.response.CommonResponse;
import com.movie.movie.util.response.ErrorResponse;
import com.movie.movie.util.response.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 발생 시 전역적으로 처리하는 ExceptionAdvice 클래스
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	/**
	 * CommonException이 발생했을 때 처리하는 메서드
	 * @param e 발생한 CommonException
	 * @return CommonResponse 형태로 에러 응답 반환
	 */
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<CommonResponse<?>> handleCommonException(CommonException e) {
		log.error("CommonException occurred: {}", e.getMessage(), e);
		return buildErrorResponse(e.getStatusCode(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 에러 응답을 생성하는 공통 메서드
	 * @param statusCode 상태 코드(StatusCode)
	 * @param message 에러 메시지
	 * @param httpStatus HTTP 상태 코드
	 * @return 에러를 포함한 ResponseEntity 반환
	 */
	private ResponseEntity<CommonResponse<?>> buildErrorResponse(StatusCode statusCode, String message, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		// 에러 정보를 담은 ErrorResponse 생성
		ErrorResponse errorResponse = new ErrorResponse(statusCode);

		// 상태 코드, null 콘텐츠, 에러 응답을 포함하는 공통 응답 생성
		CommonResponse<?> commonResponse = new CommonResponse<>(statusCode, null, errorResponse);

		return new ResponseEntity<>(commonResponse, headers, httpStatus);
	}
}
