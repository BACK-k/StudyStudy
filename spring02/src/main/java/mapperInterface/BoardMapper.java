package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardMapper {

	// bPageList
	public List<BoardDTO> bPageList(Criteria cri);

	// totalRowsCount
	public int totalRowsCount(Criteria cri);

	// selectList
	public List<BoardDTO> selectList();

	// selectOne
	public BoardDTO selectOne(int seq);

	// insert
	public int insert(BoardDTO dto);

	// replyInsert
	public int rinsert(BoardDTO dto);

	// stepUpdate
	public int stepUpdate(BoardDTO dto);

	// update
	public int update(BoardDTO dto);

	// delete
	public int delete(BoardDTO dto);

}
