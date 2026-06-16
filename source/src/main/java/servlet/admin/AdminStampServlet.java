package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StampsDAO;
import model.Stamp;

/**
 * Servlet implementation class AdminStampServlet
 */
@WebServlet("/admin/stamp")
public class AdminStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//ログインしているか確認　してなかったらログイン画面に飛ぶ
		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a1/login");
//			return;
//		} else if ((int)session.getAttribute("is_admin") != 0) { //管理者じゃなかったらトップページに飛ぶ
//			response.sendRedirect("/a1/user/top");
//			return;
//		} else {
//		// 管理者スタンプページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_stamp.jsp");
		dispatcher.forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//スタンプをとってくる　DAO
		request.setCharacterEncoding("UTF-8");
		String stampPath = request.getParameter("stampPath");
		//一覧表示処理
		StampsDAO sDao = new StampsDAO();
		List<Stamp> stampList = sDao.select(new Stamp(0,stampPath));

		//持ってきたスタンプをリクエストスコープにセット
		request.setAttribute("stampList", stampList);
		//JSPのforeachのところと合わせる
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin_stamp.jsp");
		dispatcher.forward(request, response);

	}

}
