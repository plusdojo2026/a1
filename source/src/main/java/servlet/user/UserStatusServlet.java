package servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersInfDAO;
import model.User;
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
		//未ログイン時、ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/a1/login");
			return;
		}
		
		// HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		//ビーンズのユーザーidを指定
        int userId = u.getUserId();
        //useridserchでuir
        UserInf uir = new UserInf(0, userId, 0,0,0,0,0,0,0,0);
		//daoを呼ぶ
		UsersInfDAO dao= new UsersInfDAO();
		//データを全選択して持ってくる
		ArrayList<UserInf> weatherlist = dao.select(uir);
		ArrayList<UserInf> templist = dao.select1(uir);
		
		System.out.println("ここ１");
		request.setAttribute("weatherList",weatherlist);
		request.setAttribute("templist",templist);
		
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
