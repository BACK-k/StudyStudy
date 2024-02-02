package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.MemberDTO;

@Repository
public class MemberDAO {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// ** selectList
	public List<MemberDTO> selectList() {
		sql = "select * from member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// 결과의 존재여부 확인
			if (rs.next()) {
				do {
					// setter 사용
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));

					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	} // selectList

	// selectJoList
	public List<MemberDTO> selectJoList(String jno) {
		sql = "SELECT * FROM member WHERE jno=?";
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, jno);
			rs = pst.executeQuery();

			if (rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));

					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	} // selectJoList

	// ** selectOne
	public MemberDTO selectOne(String id) {
		sql = "SELECT * FROM member WHERE id=?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	} // selectOne

	// ** insert
	// 모든 컬럼을 입력
	public int insert(MemberDTO dto) {
		sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	// ** update
	// Primary_key인 id를 제외한 모든 컬럼 수정
	public int update(MemberDTO dto) {
		sql = "update member set name=?, age=?, jno=?, info=?, point=?, birthday=?, rid=? where id=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setDouble(5, dto.getPoint());
			pst.setString(6, dto.getBirthday());
			pst.setString(7, dto.getRid());
			pst.setString(8, dto.getId());

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	// Password Update
	public int pwUpdate(MemberDTO dto) {
		sql = "update member set password = ? where id = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** PasswordUpdate Exception => " + e.toString());
			return 0;
		}
	}// Password Update

	// ** delete
	public int delete(String id) {
		sql = "delete from member where id=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete

} // class