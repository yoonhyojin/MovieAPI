package com.movie.movie.util.advice;

import com.movie.movie.util.response.CommonResponse;
import com.movie.movie.util.response.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 모든 Controller 응답을 가로채어 CommonResponse 형태로 감싸는 Advice
 */
@ControllerAdvice
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

	/**
	 * 어떤 경우에 Advice를 적용할지 결정하는 메서드
	 * 항상 true를 반환하여 모든 응답에 적용
	 *
	 * @param returnType 컨트롤러 메서드의 반환 타입
	 * @param converterType 선택된 HttpMessageConverter 타입
	 * @return true: 항상 적용
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	/**
	 * 응답 본문을 수정하는 메서드
	 * 이미 CommonResponse로 감싸져 있으면 그대로 반환, 아니면 새로 감싸기
	 *
	 * @param body 컨트롤러 메서드가 반환한 객체
	 * @param returnType 반환 타입
	 * @param selectedContentType 선택된 콘텐츠 타입
	 * @param selectedConverterType 선택된 HttpMessageConverter 타입
	 * @param request 현재 요청 객체
	 * @param response 현재 응답 객체
	 * @return 수정된 응답 객체
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
		ServerHttpResponse response) {

		// 이미 CommonResponse 타입이면 그대로 반환
		if (body instanceof CommonResponse) {
			return body;
		}

		// 아니면 새로 CommonResponse로 감싸서 반환
		return new CommonResponse<>(StatusCode.OK, response, null);
	}
}
