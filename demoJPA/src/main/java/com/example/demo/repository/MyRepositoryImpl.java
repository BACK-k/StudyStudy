package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional
@Repository
public class MyRepositoryImpl implements MyRepository {
	private final EntityManager em;

	public MyRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	// JPQL 적용
	// @Override
	public List<Member> emMemberList1() {
		// mybatis는 interface를 만들어 service와 xml을 연결
		// "select * from Member order by id asc" 오류 발생
		// antlr.NoViableAltException: unexpected token: *
		// Entity를 통해 접근하기 때문에 * 사용 금지, alias 사용해 컬럼명 접근
		return em.createQuery("select m from Member m order by m.id asc", Member.class).getResultList();
	}

	// Parameter 적용
	@Override
	public List<Member> emMemberList() {
		return em.createQuery("select m from Member m where jno<:jno order by jno", Member.class).setParameter("jno", 7)
				.getResultList();
	}

	@Override
	public Member emMemberDetail(String id) {
		return em.createQuery("select m from Member m where id=:ii", Member.class).setParameter("ii", id)
				.getSingleResult();
	}
}
