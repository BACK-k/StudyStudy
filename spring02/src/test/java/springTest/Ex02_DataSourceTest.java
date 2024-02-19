package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;

//*** DataSourceTest

//=> pom.xml 에 <dependency> spring-jdbc 추가
//=> 인터페이스 DataSource 구현객체 DriverManagerDataSource 를 bean 등록하고 (servlet~.xml 또는 root~.xml 에)
//=> DB Connection 생성 확인
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_DataSourceTest {
	@Autowired
// ** 계층도 확인 ( Ctrl+T )
// => DataSource (interface)
// 	-> AbstractDataSource
// 	-> AbstractDriverBasedDataSource
// 	-> DriverManagerDataSource ( root--.xml에 Bean 설정)
// 		org.springframework.jdbc.datasource.DriverManagerDataSource
	DataSource dataSource;
	@Autowired
	MemberDTO dto;

//	@Test
	// 1 DBConnection 확인
	public void connectionTest() {
		try {
			// 이미 Connection에 유저이름과 비밀번호를 넣어줘서 매개변수를 주지않아도 가능
			assertNotNull(dataSource.getConnection());
			System.out.println(" DB Conection 성공 => " + dataSource.getConnection());
		} catch (Exception e) {
			System.out.println(" DB Conection 실패 => " + e.toString());
		}
	} // connectionTest

	// 2 SQL 구문 실행
	public int delete(String id) {
		String sql = "delete from member where id = ?";

		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Delete Test Exception => " + e.toString());
			return 0;
		}
	} // delete

	public int insert(MemberDTO dto) {
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);

			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			pst.setString(10, dto.getUploadfile());

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Insert Test Exception => " + e.toString());
			return 0;
		}
	} // insert

	@Before
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("유니트");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("JUnit Test");
		dto.setPoint(200.456);
		dto.setBirthday("2000-02-02");
		dto.setRid("apple");
		dto.setUploadfile("Arcana1.png");
		assertEquals(insert(dto), 1);
		System.out.println(" Insert dto " + dto);
	} // insertTest

	@Test
	public void deleteTest() {
		String id = "junit"; // 있는 id는 삭제, 삭제한 아이디를 다시 삭제하면 red
		assertEquals(delete(id), 1);
		System.out.println(" Delete ID => " + id);
	} // deleteTest

} // class
