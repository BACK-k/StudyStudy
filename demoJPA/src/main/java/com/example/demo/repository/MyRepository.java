package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Member;

public interface MyRepository {
	// 추상 메서드
	List<Member> emMemberList();

	Member emMemberDetail(String id);
}
