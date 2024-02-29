package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoDTO {
	private int jno;
	// MemberDTO를 이용한 JPQL을 사용할 때, 후손이 접근 가능하게 바꿔줌
	protected String jname;
	private String captain;
	protected String project;
	private String slogan;
	private String cname;
}