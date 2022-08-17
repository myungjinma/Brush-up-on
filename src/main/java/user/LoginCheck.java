package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("post");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		
		System.out.println(userId);
		System.out.println(userPassword);
		
		
		B_UserDao dao = new B_UserDao();
		int result=dao.isLoginOk(userId, userPassword);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	
	}
	
}
