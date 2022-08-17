package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout/listUser")
public class Logout_listUser extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();	
		
		session.invalidate();//세션 지우기
		
		 response.sendRedirect("/prj/Brushupon/general_forum/mainPage.html");
		
	}
}
