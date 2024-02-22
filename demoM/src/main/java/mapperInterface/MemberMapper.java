package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	// JUnit Test
	// selectDTO, xml대신 @으로 Sql 구현
	@Select("select * from member where id=#{id}")
	MemberDTO selectDTO(MemberDTO dto);

	// => @Param 적용 Test
	// -> 기본규칙: Mybatis 에서는 매개변수 Type은 무관하지만, 갯수는 1개만 허용
	// -> @Param: mapper 에서 #{...} 적용, 복수갯수 사용 가능 (단, 기본자료형 사용불가_JUnit 에서는 가능
	@Select("select * from member where id=#{ii} and jno=#{jno}")
	MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);

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
	List<MemberDTO> selectJoList(int jno);

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
