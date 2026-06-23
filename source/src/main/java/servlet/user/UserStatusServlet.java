package servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersInfDAO;
import model.UserInf;

/**
 * Servlet implementation class UserStatusServlet
 */
@WebServlet("/user/status")
public class UserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//daoを呼ぶ
		UsersInfDAO dao= new UsersInfDAO();
		//データを全選択して持ってくる
		ArrayList<UserInf> list = dao.select(null);
		System.out.println("ここ１");
		request.setAttribute("weatherList",list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_status.jsp");
		dispatcher.forward(request, response);
		System.out.println("ここ２");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
