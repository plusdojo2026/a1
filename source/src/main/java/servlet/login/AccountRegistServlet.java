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
 * Servlet implementation class AccountRegistServlet
 */
@WebServlet("/login/regist")
public class AccountRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/account_regist.jsp");
		dispatcher.forward(request, response);
		System.out.println("ここ5");
		System.out.println("kokodayo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("ここ6");
		
		
		// リクエストパラメータを取得する
		//ブラウザでユーザが打ち込んだ登録情報を開封している
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		System.out.println("ここ７");
			System.out.println("ここだよ");
			
		// 登録処理を行う
		UsersDAO uDao = new UsersDAO();
		//insert
		if(uDao.insert (new User(
							 0,
							 mail,
							 name,
							 pass,
							 null,
							 0
							 ))){
			System.out.println("ここ８");
			//request.setAttribute("result", new Result("登録が成功しました", "名刺情報を登録しました。", "/webapp/MemberMenuServlet"));
		}else {
			//request.setAttribute("result", new Result("登録が失敗しました", "名刺情報を登録できませんでした。", "/webapp/MemberMenuServlet"));
		}
		
		// 次のページににフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
				dispatcher.forward(request, response);
	}
}
		
		
	


