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

@WebServlet("/board/boardList")
public class B_BoardList extends HttpServlet {

	/*
	 * 게시판 리스트 가져옴
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		B_BoardDao dao = new B_BoardDao();
		ArrayList<B_BoardInfo> boardList = new ArrayList<>();

		// html Ajax에서 파라미터로 던진 boardType을 변수에 저장, DB에서 게시글을 가져올때 where조건절에 입력
		String boardType = request.getParameter("boardType");

	
		if (boardType == null) {
			boardType = "1";
		}
		boardList = dao.BoardList(boardType);
		System.out.println("boardType:" + boardType);

		
		JSONObject jobj = new JSONObject();
		jobj.put("list", boardList);
		response.getWriter().print(jobj); 
	}

	/**
	 * 게시판 리스트 화면으로 이동
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String boardType = "";
		boardType = request.getParameter("boardType");
		
		//게시판 type 별로 return page 변경
		//boardType이 널이면 무조건 알고리즘 페이지로 return
		if("".equals(boardType) || boardType == null || "1".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/Algorithm/algorithmFL.html';</script>");
		}else if(boardType == "2" ||  "2".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/JAVA/javaFL.html';</script>");
		}else if( "3".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/HTML/htmlFL.html';</script>");
		}else if( "4".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/CSS/cssFL.html';</script>");
		}else if( "5".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/JAVASCRIPT/javascriptFL.html';</script>");
		}else if( "6".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/DB/dbFL.html';</script>");
		}else if( "7".equals(boardType)) {
			out.print("<script>location.href = '/prj/Brushupon/general_forum/SERVLET/servletFL.html';</script>");
		}
	}
}
