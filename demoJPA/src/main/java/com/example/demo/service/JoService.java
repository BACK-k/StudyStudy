package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Jo;

public interface JoService {

	// joList
	List<Jo> selectList();

	// joDetail
	Jo selectOne(int jno);

	// joInsert, joUpdate
	Jo save(Jo entity);

	// joDelete
	void deleteById(int jno);

}