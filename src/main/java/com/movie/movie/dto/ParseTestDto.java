package com.movie.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
public class ParseTestDto {
	private String userId;
	private String id;
	private String titie;
	private String body;
}
