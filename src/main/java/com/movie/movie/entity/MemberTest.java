package com.movie.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 참고용 Entity
 */
@Entity
@Table(name = "MEMBER_TEST")
@Data
public class MemberTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long memberId;

	private String userId;
	private String password;
	private String nickName;
}
