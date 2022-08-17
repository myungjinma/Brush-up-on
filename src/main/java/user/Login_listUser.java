package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginOk")

public class Login_listUser extends HttpServlet {

    
	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//한
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		//parameter 추
    	String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		//로그인 시도시, 아이디와 비밀번호 console 출
		System.out.println(userId + userPassword);
		//Dao 불러오
		B_UserDao dao = new B_UserDao();
		
		//로그인시, 유효성 체
		int result  = dao.isLoginOk(userId, userPassword);

		PrintWriter out = response.getWriter();
		if(result ==1) {
			HttpSession session = request.getSession();   	
			session.setAttribute("userId", userId);
			response.sendRedirect("/prj/Brushupon/general_forum/mainPage2.html");
		}else {
			response.sendRedirect("/prj/Brushupon/general_forum/login.html");
		}
		
    }

}