package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tracing.dtrace.ModuleAttributes;

@WebServlet("/signUpOk")
public class SignUp_insertUser extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");		
		System.out.println( "id=" + userId);
		B_UserDao dao = new B_UserDao();		
		int  result=dao.duplicatedID(userId);  //  result => true, false ;		
		System.out.println( "result =" + result);
		PrintWriter out = response.getWriter();		
		// 1:중복된 상황 , 0:사용가능 		 
		out.println(result);	 
	}	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userBirthday=request.getParameter("userBirthday");
		String userTel=request.getParameter("userTel");
		
		
		System.out.println(userId);
		B_User m = new B_User(userId, userPassword,userName,userBirthday, userTel);
		B_UserDao dao = new B_UserDao();
		dao.insertB(m);

		
		//다른페이지로 이동
		response.sendRedirect("/prj/Brushupon/general_forum/login.html");

	}
}
