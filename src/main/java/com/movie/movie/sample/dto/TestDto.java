package com.movie.movie.sample.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 참고용 DTO
 */
@Builder
@Getter
public class TestDto {
	private final Long memberId;
	private final String userId;
	private final String password;
	private final String nickName;
}
