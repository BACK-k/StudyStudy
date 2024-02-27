package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

// 엔티티가 누구인지 전달해줘야함 -> GenericType <엔티티, Key의 Type>
public interface MemberRepository extends JpaRepository<Member, String> {

}
