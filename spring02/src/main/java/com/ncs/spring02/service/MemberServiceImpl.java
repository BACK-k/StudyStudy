package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.MemberDTO;

import mapperInterface.MemberMapper;
import pageTest.SearchCriteria;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;

	// Member Check List
	@Override
	public List<MemberDTO> mCheckList(SearchCriteria cri) {
		return mapper.mCheckList(cri);
	}

	@Override
	public int mCheckRowsCount(SearchCriteria cri) {
		return mapper.mCheckRowsCount(cri);
	}

	// Member Paging
	@Override
	public List<MemberDTO> mPageList(SearchCriteria cri) {
		return mapper.mSearchList(cri);
	}

	@Override
	public int totalRowsCount(SearchCriteria cri) {
		return mapper.mSearchRowsCount(cri);
	}

	// SelectList
	@Override
	public List<MemberDTO> selectList() {
		return mapper.selectList();
	}

	// selectJoList
	@Override
	public List<MemberDTO> selectJoList(int jno) {
		return mapper.selectJoList(jno);
	}

	// selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return mapper.selectOne(id);
	}

	// insert
	@Override
	public int insert(MemberDTO dto) {
		return mapper.insert(dto);
	}

	// update
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	}

	// pwUpdate
	@Override
	public int pwUpdate(MemberDTO dto) {
		return mapper.pwUpdate(dto);
	}

	// delete
	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

}
