package servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class PasswordResetServlet
 */
@WebServlet("/login/password-reset")
public class PasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/password_reset.jsp");
		dispatcher.forward(request, response);
		System.out.println("ここ1");
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		UsersDAO uDao = new UsersDAO();
		
		
		User user = new User(0,mail,name,pass,null, 0);
		System.out.println("ここ2");
		if(!user.getMail().equals(request.getParameter("mail"))) {
			request.setAttribute("msg", "メールアドレスまたは氏名が違います。");//JSP側で取得するには${msg}
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/password_reset.jsp");
			dispatcher.forward(request, response);
		}else if(!user.getName().equals (request.getParameter("name"))) {
			request.setAttribute("msg", "メールアドレスまたは氏名が違います。");//JSP側で取得するには${msg}
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/password_reset.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("ここ3");
			//update
			if(uDao.update (new User(
								 0,
								 user.getMail(),
								 user.getName(),
								 request.getParameter("pass"),
								 null,
								 0
								 ))){
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
			dispatcher.forward(request, response);
			}else{
				request.setAttribute("msg", "メールアドレスまたは氏名が違います。");//JSP側で取得するには${msg}
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/password_reset.jsp");
				dispatcher.forward(request, response);
			}
			System.out.println("ここ4");
		}
	}

}
