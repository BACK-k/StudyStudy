package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	// Member Check List
	public List<MemberDTO> mCheckList(SearchCriteria cri);

	public int mCheckRowsCount(SearchCriteria cri);

	// Member Search Paging
	public List<MemberDTO> mSearchList(SearchCriteria cri);

	public int mSearchRowsCount(SearchCriteria cri);

	// Member Paging
	public List<MemberDTO> mPageList(SearchCriteria cri);

	public int totalRowsCount(SearchCriteria cri);

	// SelectList
	List<MemberDTO> selectList();

	// selectJoList
	List<MemberDTO> selectJoList(String jno);

	// selectOne
	MemberDTO selectOne(String id);

	// insert
	int insert(MemberDTO dto);

	// update
	int update(MemberDTO dto);

	// pwUpdate
	int pwUpdate(MemberDTO dto);

	// delete
	int delete(String id);
}
