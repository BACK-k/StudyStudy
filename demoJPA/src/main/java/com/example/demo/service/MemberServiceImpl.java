/*
 * package com.example.demo.service;
 * 
 * 
 * 
 * import java.util.List;
 * 
 * import org.springframework.stereotype.Service;
 * 
 * import com.example.demo.domain.MemberDTO; import
 * com.example.demo.entity.Member; import
 * com.example.demo.repository.MemberRepository;
 * 
 * @Service public class MemberServiceImpl implements MemberService {
 * 
 * private final MemberRepository repository;
 * 
 * // SelectList
 * 
 * @Override public List<Member> selectList() { return repository.findAll(); }
 * 
 * // selectOne
 * 
 * @Override public MemberDTO selectOne(String id) { return
 * mapper.selectOne(id); }
 * 
 * // insert
 * 
 * @Override public int insert(MemberDTO dto) { return mapper.insert(dto); }
 * 
 * // update
 * 
 * @Override public int update(MemberDTO dto) { return mapper.update(dto); }
 * 
 * // pwUpdate
 * 
 * @Override public int pwUpdate(MemberDTO dto) { return mapper.pwUpdate(dto); }
 * 
 * // delete
 * 
 * @Override public int delete(String id) { return mapper.delete(id); }
 * 
 * }
 */