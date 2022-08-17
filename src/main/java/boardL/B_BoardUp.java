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

import boardL.B_BoardDao;
@WebServlet("/board/boardUp")
public class B_BoardUp extends HttpServlet{
	
	/*
	 * 게시판 글 수정
	 */
	@Override
	public void doPost(HttpServletRequest request
						, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
	
		String title = request.getParameter("title");
		String content = request.getParameter("forumarea");
		String boardType = request.getParameter("boardType");
		String boardNum = request.getParameter("boardNum");
		
		
		B_BoardInfo boardInfo = new B_BoardInfo();
		boardInfo.setBoard_title(title);
		boardInfo.setBoard_content(content);
		boardInfo.setBoard_type(boardType);
		boardInfo.setBoard_num(Integer.parseInt(boardNum));

		
		B_BoardDao dao = new B_BoardDao();
		dao.boardUpdate(boardInfo);
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println("<script>alert('게시판 정상적으로 글 수정 하였습니다.');");

		//게시판 type 별로 return page 변경
		//boardType이 널이면 무조건 알고리즘 페이지로 return
		if(boardType == "" || boardType == null || "1".equals(boardType)) {
			out.print("location.href = '/prj/Brushupon/general_forum/Algorithm/algorithmFL.html';</script>");
		}else if( boardType == "2" || "2".equals(boardType)) {
			out.print("location.href = '/prj/Brushupon/general_forum/JAVA/javaFL.html';</script>");
		}else if( "3".equals(boardType)) {
			out.print("location.href = '/prj/Brushupon/general_forum/HTML/htmlFL.html';</script>");
		}else if("4".equals(boardType)) {
			out.print("location.href= '/prj/Brushupon/general_forum/CSS/cssFL.html';</script>");
		}else if( "5".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/JAVASCRIPT/javascriptFL.html';</script>");
		}else if( "6".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/DB/dbFL.html';</script>");
		}else if( "7".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/SERVLET/servletFL.html';</script>");
		}
		out.close();
	}
}
