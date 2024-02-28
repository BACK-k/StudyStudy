package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
// 클래스명과 동일한 경우 생략 가능
// @Table(name = "guestbook")
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {
	// PK
	@Id
	// Auto_Increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno;

	@Column(length = 100, nullable = false)
	private String title;
	@Column(length = 2000, nullable = false)
	private String content;
	@Column(length = 50, nullable = false)
	private String writer;

	// setter
	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeContent(String content) {
		this.content = content;
	}

}
