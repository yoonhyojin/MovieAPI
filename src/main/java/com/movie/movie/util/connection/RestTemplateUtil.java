package com.movie.movie.util.connection;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * RestTemplate 을 통해 HTTP 통신을 할 수 있게 하는 Util Class
 */
@Component("RestTemplateUtil")
@Slf4j
public class RestTemplateUtil {

	/**
	 * 파라미터 없는 Get Connection
	 * @param URL		Connection Url
	 * @param clazz		맵핑 대상 Class
	 * @return			API 호출 결과
	 * @param <T>
	 */
	public <T> T getConnection(final String URL, Class<T> clazz) {
		// 1. Header Setting
		HttpHeaders headers = new HttpHeaders();

		// 2. Template Setting
		RestTemplate restTemplate = getRestTemplate();

		// 3. Get Connection
		ResponseEntity<T> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<>(headers), clazz);

		return responseEntity.getBody();
	}

	/**
	 * 파라미터 있는 Get Connection
	 * @param URL	Connection Url
	 * @param param	Param
	 * @param clazz	결과로 맵필할 Class
	 * @return		API 호출 결과
	 * @param <T>
	 */
	public <T> T getConnectionWithParam(final String URL, Map<String, Object> param, Class<T> clazz) {
		// 1. Param Setting
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(URL);

		if (param != null && !param.isEmpty()) {
			for (String key : param.keySet()) {
				uriComponentsBuilder.queryParam(key, param.get(key));
			}
		}

		// 2. Header Setting
		HttpHeaders headers = new HttpHeaders();

		// 3. Template Setting
		RestTemplate restTemplate = getRestTemplate();

		// 4. Get Connection With Param
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), clazz);

		return responseEntity.getBody();
	}

	/**
	 * Post Connection
	 * @param URL		Connection Url
	 * @param paramMap	Post Header Setting
	 * @param clazz		결과로 맵필할 Class
	 * @return			API 호출 결과
	 * @param <T>
	 */
	private <T> T postConnection(final String URL, Map<String, Object> paramMap, Class<T> clazz) {
		// 1. Header Setting
		HttpHeaders headers = new HttpHeaders();

		// 2. Template Setting
		RestTemplate restTemplate = getRestTemplate();

		// 3. Get Connection
		ResponseEntity<T> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(paramMap, headers), clazz);

		return responseEntity.getBody();
	}

	// 이하 private
	/**
	 * Header Setting
	 * @return
	 */
	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		return httpHeaders;
	}

	/**
	 * RestTemplate Setting
	 * @return
	 */
	private RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setConnectTimeout(5000);		// Connection Timeout
		requestFactory.setHttpClient(getHttpClient());				// RestTemplate Connection Poll Setting

		return new RestTemplate(requestFactory);
	}

	/**
	 * RestTemplate Connection Poll Setting
	 * @return
	 */
	private CloseableHttpClient getHttpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(10);				// Max Connection Poll
		connectionManager.setDefaultMaxPerRoute(5);		// IP, 포트 1쌍에 대해 수행할 커넥션 수

		return HttpClients.custom()
			.setConnectionManager(connectionManager)
			.setRetryStrategy(new DefaultHttpRequestRetryStrategy(3, Timeout.ofSeconds(1)))
			.build();
	}

}
