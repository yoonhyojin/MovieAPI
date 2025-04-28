package com.movie.movie.util.response.enums;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API 응답에 사용될 상태 코드 정의 Enum
 */
@RequiredArgsConstructor
@Getter
public enum StatusCode {

	/**
	 * 정상 처리된 경우
	 */
	OK(HttpStatus.OK, HttpStatus.OK.value(), "OK", "OK MESSAGE"),

	/**
	 * 서버 내부 에러
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "Internal Server Error"),

	/**
	 * 리소스가 성공적으로 생성됨
	 */
	CREATED(HttpStatus.CREATED, HttpStatus.CREATED.value(), "Created", "Resource successfully created"),

	/**
	 * 잘못된 요청
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Bad Request", "Invalid request"),

	/**
	 * 인증 필요
	 */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "Authentication required"),

	/**
	 * 권한 없음
	 */
	FORBIDDEN(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), "Forbidden", "Access denied"),

	/**
	 * 리소스를 찾을 수 없음
	 */
	NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Not Found", "Resource not found"),

	/**
	 * 요청 충돌
	 */
	CONFLICT(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "Conflict", "Request conflict");

	/**
	 * HTTP 상태 코드 (Spring의 HttpStatus)
	 */
	private final HttpStatus httpStatus;

	/**
	 * HTTP 상태 코드 숫자 값
	 */
	private final int statusCode;

	/**
	 * 프로젝트 내에서 사용하는 커스텀 코드 문자열
	 */
	private final String projectCode;

	/**
	 * 상태 메시지
	 */
	private final String message;

	/**
	 * projectCode를 빠르게 조회하기 위한 Map
	 */
	private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
		Stream.of(values()).collect(Collectors.toMap(StatusCode :: getProjectCode, StatusCode::name))
	);

	/**
	 * projectCode를 통해 StatusCode Enum을 조회
	 * @param projectCode 조회할 프로젝트 코드
	 * @return 매칭되는 StatusCode
	 * @throws IllegalArgumentException 존재하지 않는 코드일 경우
	 */
	public static StatusCode of(final String projectCode) {
		String name = CODE_MAP.get(projectCode);
		if (name == null) {
			throw new IllegalArgumentException("Invalid project code: " + projectCode);
		}
		return StatusCode.valueOf(name);
	}
}
