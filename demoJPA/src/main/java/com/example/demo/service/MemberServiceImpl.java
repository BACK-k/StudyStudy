
package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberDSLRepositoryImpl;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;
	private final MyRepositoryImpl emrepository;
	private final MemberDSLRepositoryImpl dslrepository;

	// ** Join
	@Override
	public List<MemberDTO> findMemberJoin() {
		// ver01
		// return repository.findMemberJoin();
		// ver02
		return dslrepository.findMemberJoinDSL();
	}

	// Password Update
	// @Query
	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);
	}

	// jno별 Member 출력
	// JPARepository Method Naming 규약
	@Override
	public List<Member> findByJno(int jno) {
		// ver01
		// return repository.findByJno(jno);
		// ver02
		return dslrepository.findMemberJnoDSL(jno);
	}

	// SelectList
	@Override
	public List<Member> selectList() {
		// ver01
		// return repository.findAll();

		// ver02 EntityManager Test
		return emrepository.emMemberList();
	}

	// selectOne
	@Override
	public Member selectOne(String id) {
		// ver01
//		Optional<Member> result = repository.findById(entity);
//		if (result.isPresent())
//			return result.get();
//		else
//			return null;

		// ver02 EntityManager Test
		return emrepository.emMemberDetail(id);

	}

	// insert, update
	@Override
	public Member save(Member entity) {
		return repository.save(entity);
	}

	// pwUpdate
	@Override
	public Member pwUpdate(Member entity) {
		return null;
	}

	// delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
