package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;

// JPA CRUD 구현
//=> Entity 와 DTO를 용도별로 분리해서 사용
//  dtoToEntity() 와  entityToDto() default 메서드로 추가
public interface GuestbookService {

	// JPA Pageable을 이용한 Paging 기능
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);

	// JPA CRUD
	List<Guestbook> selectList();

	Guestbook selectOne(Long gno);

	// JPA는 입력과 수정을 같이 사용, key를 return
	Long register(GuestbookDTO dto);

	void delete(Long gno);

	// dtoToEntity(), dto를 꺼내 Entity에 담는다
	// insert, update 위해 주로 사용되므로 regDate, modDate는 제외
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder().gno(dto.getGno()).title(dto.getTitle()).content(dto.getContent())
				.writer(dto.getWriter()).build();

		return entity;
	}

	// entityToDto()
	// 출력 결과를 위해 사용
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder().gno(entity.getGno()).title(entity.getTitle()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();
	}

} // interface
