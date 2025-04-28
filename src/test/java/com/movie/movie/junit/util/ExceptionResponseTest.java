package com.movie.movie.junit.util;

import com.movie.movie.util.response.CommonResponse;
import com.movie.movie.util.response.enums.StatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExceptionResponseTest {

	@LocalServerPort
	private int port;

	private final RestTemplate restTemplate = new RestTemplate();

	/**
	 * 정상 요청 시 CommonResponse 포맷으로 반환되는지 테스트
	 */
	@Test
	void testSuccessResponse() {
		// given
		String url = "http://localhost:" + port + "/test/success";

		// when
		ResponseEntity<CommonResponse> response = restTemplate.getForEntity(url, CommonResponse.class);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatusCode()).isEqualTo(StatusCode.OK);
	}

	/**
	 * 실패 요청 시 CommonResponse 안에 ErrorResponse가 포함되는지 테스트
	 */
	@Test
	void testErrorResponse() {
		// given
		String url = "http://localhost:" + port + "/test/fail";

		// when
		ResponseEntity<CommonResponse> response = restTemplate.getForEntity(url, CommonResponse.class);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getErrorResponse()).isNotNull();
	}
}
