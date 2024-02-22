package com.ncs.green.test;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOService;

public class DTOServiceTest {

	public static void main(String[] args) {
		// 1 UserDTO 생성
		UserDTO dto = new UserDTO();
		dto.setId("banana");
		dto.setName("홍길동");
		dto.setLoginTime("2023/02/22 am 10:04");

		// 2 직접 출력
		System.out.println(" 만든 인스턴스(객체)를 이용해 직접 출력 => " + dto);

		// 3 DTOService 출력
		DTOService service = new DTOService();
		service.setUserDTO(dto);
		System.out.println(" AntBuild Test ");
		System.out.println(" DTOService를 이용한 출력 => " + service.getUserDTO());

	} // main

} // class
