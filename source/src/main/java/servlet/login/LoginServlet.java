package servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ここ1");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
		dispatcher.forward(request, response);
		System.out.println("ここ2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ここ3");
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		System.out.println("ここ4");
		
		//ログイン処理　mpDAO=メールアドレスパスワードDAO
		UsersDAO mpDAO = new UsersDAO();
		
		User user = new User(0,mail,null,pass,null, 0);
		
		User u = mpDAO.isLoginOK(user);
		System.out.println(u);
		System.out.println("ここ5");
		if (u !=null) { // ログイン成功 
			int isAdmin = u.getIsAdmin();
			
			if(isAdmin == 0) {//0はユーザー
			
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			//リダイレクト処理
			response.sendRedirect("TopServlet");
			
			}else if(isAdmin == 1){//1は管理者
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			//リダイレクト処理
			response.sendRedirect("AdminSurveyServlet");
			}else{
				request.setAttribute("msg", "ログインできませんでした。メールアドレス,パスワードを確認してください");//JSP側で取得するには${msg}
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
				dispatcher.forward(request, response);
			}
			System.out.println("ここ5");
		}else{

			if(mail.equals("")) {
				request.setAttribute("msg", "メールアドレスを入力してください");//JSP側で取得するには${msg}
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
				dispatcher.forward(request, response);
			}else if(pass.equals("")) {
				request.setAttribute("msg", "パスワードを入力してください");//JSP側で取得するには${msg}
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
				dispatcher.forward(request, response);
				
			}
			
		}

		System.out.println("ここ６");
	}

}
