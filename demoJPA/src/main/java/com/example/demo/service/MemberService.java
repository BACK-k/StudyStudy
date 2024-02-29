package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	// ** Join
	List<MemberDTO> findMemberJoin();

	// Password Update
	// @Query
	void updatePassword(String id, String password);

	// jno별 Member 출력
	// JPARepository Method Naming 규약
	List<Member> findByJno(int jno);

	// SelectList
	List<Member> selectList();

	// selectOne
	Member selectOne(String id);

	// insert, update
	Member save(Member entity);

	// pwUpdate
	Member pwUpdate(Member entity);

	// delete
	void deleteById(String id);

}