package jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// ** 순서
// 1. JDBC API 에 정의된 필요한 객체들을 전역변수로 정의
// 2. CRUD 기능을 처리하는 메서드
// 3. main에서 사용
public class DBStart {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	// 결과를 처리
	private static ResultSet rs;
	// 모든 SQL문이 java 안에선 string
	private static String sql;

	// 조별 List2
	// PreparedStatement 활용
	public static void joListPS(int jno) {
		sql = "select * from student where jno=?";

		try {
			System.out.println("		** Jo List **		");

			// ?를 해결하기 때문에 sql문을 인자로 미리 전달
			// ?를 해결하지않으면 select * from student where jno=? 를 sql에서 실행하라는 의미
			pst = cn.prepareStatement(sql);
			// ?에 값을 넣어주기, parameterindex는 ?의 순서
			pst.setInt(1, jno);
			rs = pst.executeQuery();

			if (rs.next()) {
				do {
					System.out.print(rs.getInt(1) + " ");
					System.out.print(rs.getString("name") + " ");
					System.out.print(rs.getInt(3) + " ");
					System.out.print(rs.getInt(4) + " ");
					System.out.print(rs.getString(5) + " ");
					System.out.print(rs.getDouble(6) + "\n");
				} while (rs.next());
			} else {
				System.out.println("** joList Exception 결과가 1건도 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** joList Exception => " + e.toString());
		}

	} // joList

	public static void main(String[] args) {
		// 1. connection 확인
		System.out.println("** connection 확인 => " + cn);

	} // main

} // class