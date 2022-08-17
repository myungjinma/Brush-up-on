package boardL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import boardL.B_BoardInfo;
import user.B_UserDao;

public class B_BoardDao {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String password = "tiger";

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
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

	//게시판 상세
	public B_BoardInfo BoardInfo(int boardNum) {
		B_BoardInfo boardInfo = new B_BoardInfo();
		try {
			dbCon();
			Statement st = con.createStatement();
			String sql = "SELECT BOARD_NUM, BOARD_TYPE, BOARD_TITLE, BOARD_CONTENT, TO_CHAR(BOARD_WRITEDATE, 'YYYY-MM-DD') AS BOARD_WRITEDATE  FROM BOARD WHERE BOARD_NUM = ?"; 
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, boardNum);
			System.out.println(sql);
			System.out.println(boardNum);
			pst.executeUpdate();
			
			try {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					boardInfo.setBoard_num(rs.getInt("board_num"));
					boardInfo.setBoard_type(rs.getString("board_type"));
					boardInfo.setBoard_title(rs.getString("board_title"));
					boardInfo.setBoard_content(rs.getString("board_content"));
					boardInfo.setBoard_writedate(rs.getString("board_writedate"));
				}				
				rs.close();
				pst.close();
				con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return boardInfo;
	}


	//게시판리시트 조회
	public ArrayList<B_BoardInfo> BoardList(String boardType) {
		
		ArrayList<B_BoardInfo> boardList = new ArrayList<>();
		try {
			dbCon();
			Statement st = con.createStatement();
			String sql = "SELECT ROWNUM, BOARD_NUM, BOARD_TYPE, BOARD_TITLE, BOARD_CONTENT, TO_CHAR(BOARD_WRITEDATE, 'YYYY-MM-DD') AS BOARD_WRITEDATE  FROM BOARD WHERE BOARD_TYPE = ? ORDER BY BOARD_NUM DESC"; 
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, boardType);
			
			pst.executeUpdate();
			System.out.println(sql);
			try {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					B_BoardInfo boardInfo = new B_BoardInfo();
					boardInfo.setRownum(rs.getInt("rownum"));
					boardInfo.setBoard_num(rs.getInt("board_num"));
					boardInfo.setBoard_type(rs.getString("board_type"));
					boardInfo.setBoard_title(rs.getString("board_title"));
					boardInfo.setBoard_content(rs.getString("board_content"));
					boardInfo.setBoard_writedate(rs.getString("board_writedate"));
					boardList.add(boardInfo);
				}				
				//rs.close();
				//pst.close();
				//con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return boardList;
	}

	//게시판 등록
	public void boardInsert(B_BoardInfo boardInfo) {
	
		try {
			dbCon();
			String sql = "INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARD_NUM)+1, 1) FROM BOARD),?,?,?,SYSDATE)";
			PreparedStatement pst = con.prepareStatement(sql);
			System.out.println(sql);
			pst.setString(1, boardInfo.getBoard_type());
			pst.setString(2, boardInfo.getBoard_title());
			pst.setString(3, boardInfo.getBoard_content());
	
			pst.executeUpdate();
	
			pst.close();
			con.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//게시판 수정
	public void boardUpdate(B_BoardInfo boardInfo) {
	
		try {
			dbCon();
			String sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NUM = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			System.out.println(sql);
			pst.setString(1, boardInfo.getBoard_title());
			pst.setString(2, boardInfo.getBoard_content());
			pst.setInt(3, boardInfo.getBoard_num());
	
			pst.executeUpdate();
	
			pst.close();
			con.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}