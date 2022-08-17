package boardL;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.tracing.dtrace.ModuleAttributes;

import net.sf.json.JSONObject;
import boardL.B_BoardDao;

@WebServlet("/board/boardDetail")
public class B_BoardDetail extends HttpServlet{
	
	/**
	 * 게시판 상세보기
	 */
	public void doPost(HttpServletRequest request
						, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		B_BoardDao dao = new B_BoardDao();
		B_BoardInfo boardInfo = new B_BoardInfo();
		
		//게시판 리스트에서 상세보기 클릭시 파라미터로 전달한 게시글 번호 변수에 저장
		String boardNum = request.getParameter("boardNum");
		String boardType = "";
		//데이터베이스 컬럼 TYPE이 NUMBER기때문에 INT형으로 형변환
		int num = Integer.parseInt(boardNum);
		boardInfo = dao.BoardInfo(num);
		
		//세션을 선언하여 전달받은 DATA를 세션에 set으로 저장
		HttpSession session = request.getSession();
		//session.invalidate();
		session.setAttribute("boardNum", boardNum);
		session.setAttribute("boardTitle", boardInfo.getBoard_title());
		session.setAttribute("boardContent", boardInfo.getBoard_content());
		session.setAttribute("boardType", boardInfo.getBoard_type());
		boardType = boardInfo.getBoard_type();
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		//게시판 type 별로 return page 변경
		//boardType이 널이면 무조건 알고리즘 페이지로 return
		if(boardType == "" || boardType == null ||  "1".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/Algorithm/algorithmForumInsert.html';</script>");
		}else if( boardType == "2" || "2".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/JAVA/javaForumInsert.html';</script>");
		}else if( "3".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/HTML/htmlForumInsert.html';</script>");
		}else if( "4".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/CSS/cssForumInsert.html';</script>");
		}else if( "5".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/JAVASCRIPT/javascriptForumInsert.html';</script>");
		}else if( "6".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/DB/dbForumInsert.html';</script>");
		}else if( "7".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/SERVLET/servletForumInsert.html';</script>");
		}
		out.close();
	}
	
	
	/*
	 * 게시글 번호가 존재할 경우 세션에 담겨져 있는 정보를 put으로 Ajax호출에 return값으로 전달
	 * 게시글이 없는 경우는 등록페이지에서 호출한 경우이므로 분기문을통하여 그냥 아무런 정보도 전달하지 않음 
	 */
	public void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		HttpSession session = request.getSession();
		String boardNum = "";
		boardNum  = (String) session.getAttribute("boardNum");
		if(boardNum != "") {
			String boardTitle = (String) session.getAttribute("boardTitle");
			String boardContent = (String) session.getAttribute("boardContent");
			String boardType = (String) session.getAttribute("boardType");
			
			JSONObject jobj = new JSONObject();
			jobj.put("boardNum", boardNum);
			jobj.put("boardTitle", boardTitle);
			jobj.put("boardContent", boardContent);
			jobj.put("boardType", boardType);
			
			response.getWriter().print(jobj); // 전송이 되는 부분
		}
	}
}
