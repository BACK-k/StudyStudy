package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.MemberDTO;
import model.MemberDAO;

// component로 bean을 생성하라고 컨테이너에게 요청
// mList에서 사용하기 위해 미리 생성되어 있어야함
@Service
public class MemberService {
	// 전역변수 설정
//	IOC/DI 적용, 자동주입, 이미 생성되어있어야 한다
	@Autowired
	MemberDAO dao;
//	MemberDAO dao = new MemberDAO();

	// SelectList
	public List<MemberDTO> selectList() {
		return dao.selectList();
	}

	// selectOne
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	}

	// insert
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}

	// update
	public int update(MemberDTO dto) {
		return dao.update(dto);
	}

	// delete
	public int delete(String id) {
		return dao.delete(id);
	}
}
