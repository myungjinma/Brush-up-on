package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import boardL.B_BoardInfo;

public class B_UserDao {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String password = "tiger";

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public int isLoginOk(String userId, String  userPassword) {     //0은 없는 아이디,  1: 정상로그인,  2: 비번오류
		int result=0;		 // 아이디가 없거나 다름 
		
		//System.out.print("<script>alert('아이디가 일치하지 않습니다.');</script>");

		try {
			dbCon();
			String  sql ="select  trim(userPassword) from Bsign where userId=?";
			
			PreparedStatement pst  = con.prepareStatement(sql);
			pst.setString(1, userId);
			ResultSet rs  =pst.executeQuery();
			
			
			if( rs.next()) {    // 값이 있다면 아이디는 존재
				result=2;       // 아이디가 존재하면 2출력 
				//System.out.print("<script>alert('비밀번호가 일치하지 않습니다.');</script>");
				if( userPassword.equals(rs.getString(1))) {
					result=1 ; //  비번 까지 모두 정상 , 로그인 오케이
					//System.out.print("<script>alert('로그인 성공!' +userName + '님');</script>");

				} 				 
			}
			
			rs.close();
			pst.close();
			con.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//아이디 중복 확인
	public int duplicatedID (String userId) {
		int result=0;		
		try {
			dbCon();
			String  sql ="select  * from Bsign where userId=?";
			
			PreparedStatement pst  = con.prepareStatement(sql);
			pst.setString(1, userId);
			ResultSet rs  =pst.executeQuery();
			
			if( rs.next()) { 
				result=1;
			}			
			rs.close();
			pst.close();
			con.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertB(B_User b) { // 회원 정보 입력 (회원가입 전용)

		try {
			dbCon();
			String sql = "insert into Bsign values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, b.getUserId());
			pst.setString(2, b.getUserPassword());
			pst.setString(3, b.getUserName());
			pst.setString(4, b.getUserBirthday());
			pst.setString(5, b.getUserTel());

			pst.executeUpdate();

			pst.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<B_User> listMembers() { // 유저 정보 가져오기

		ArrayList<B_User> list = new ArrayList<>();
		try {

			dbCon();
			Statement st = con.createStatement();
			String sql = "select * from Bsign";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				B_User b = new B_User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				list.add(b);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	
	public void update(String userId, String userPassword) {

		try {
			dbCon();
			String sql = "update Bsign set userPassword=? where userId=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userPassword);
			pst.setString(2, userId);

			pst.executeUpdate();

			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(String userId) { // 삭제 Dao 로그아웃
		dbCon();
		String sql = "delete from Bsign where userId=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			pst.executeUpdate();

			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dbCon() { // DB서버 연결
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		B_UserDao dao = new B_UserDao();

		System.out.println("db연결 완료");
		dao.dbCon();
		
		
		int result =dao.duplicatedID("kcma0622");
		System.out.println( result );
		
	}

	
	
}
