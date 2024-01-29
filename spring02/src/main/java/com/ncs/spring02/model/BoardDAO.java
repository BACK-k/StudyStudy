package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

// 게시판 CRUD 구현
@Repository
public class BoardDAO {
	// 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// selectList
	public List<BoardDTO> selectList() {
		sql = "select * from board order by seq desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();

					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));

					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				System.out.println(" Board selectList => 출력자료가 없습니다 ");
				return null;
			} // iff

		} catch (Exception e) {
			System.out.println(" Board selectList => " + e.toString());
			return null;
		} // try
	} // selectList

	// selectOne
	public BoardDTO selectOne(int seq) {
		sql = "SELECT * FROM board WHERE seq=?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs = pst.executeQuery();

			if (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}

	// Insert
	public int insert(BoardDTO dto) {
		sql = "	insert into board values(" + "(select * from" + "(select ifNull(max(seq),0)+1 from board) as temp),"
				+ "?, ?, ?, Current_TimeStamp, 0," + "(select * from (select ifNull(max(seq),0)+1 from board) as temp),"
				+ "0, 0)";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			System.out.println(sql);
			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // Insert

	// update
	public int update(BoardDTO dto) {
		sql = "update board set title=?, content=? where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setInt(4, dto.getSeq());

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	}

	// delete
	public int delete(int seq) {
		sql = "delete from board where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, seq);

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	}

} // class
