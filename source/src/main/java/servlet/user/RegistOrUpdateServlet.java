package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiariesDAO;
import model.DiaryView;
import model.User;


@WebServlet("/user/regist-or-update")
public class RegistOrUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		
		//未ログイン時、ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/a1/user/login");
		}
		
	//クリックした日の予定一覧表示
		
		//セッションスコープからユーザーIDを取得
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		DiariesDAO dDAO = new DiariesDAO();
		DiaryView newDiary = dDAO.selectNewDiaryId(userId);
		String diary = null;
		diary = newDiary.getDiary();
		if(diary == null ) {//日記登録前の処理　登録ページへ
			response.sendRedirect("/a1/user/diary-regist");
		} else {//日記登録済みの処理　編集ページへ
			response.sendRedirect("/a1/user/diary-update");
		}
	}

}

