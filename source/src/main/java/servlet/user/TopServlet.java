package servlet.user;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiariesDAO;
import model.Diary;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/user/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		//その日最初のログインかどうかを確認する
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/a1/user/login");
		}
		
		//時間の処理
		LocalDateTime date = LocalDateTime.now();
		// Dateから4時間引く　コピーを返すと言われたら、元ある変数に入れられる
		date.minusHours(4);
		date = date.minusHours(4);
		// それをLocalDateに変換
		date.toLocalDate();
		
		//セッションスコープからユーザーID、DATEを取得
		int userId = (int)session.getAttribute("user_id");
		//DAOを持ってくる
		DiariesDAO dDao = new DiariesDAO();
		//分岐
		if () { //今日の日記がない場合
			//その日の日記テーブル作成
			dDao.insert(new Diary(0,0,"",0,0,0,0,0,"",0,""));
			//テーマをランダムに選択　フラグで管理
			
		}
		//天気表示
		
		//気温表示
		
		//継続日数表示
		
		//１年前の今日の日記
		LocalDateTime oneYearAgo = LocalDateTime.now();
		// Dateから1年引く　コピーを返すと言われたら、元ある変数に入れられる
		oneYearAgo.minusYears(1);
		oneYearAgo = oneYearAgo.minusYears(1);
		oneYearAgo.toLocalDate();
		if() {//１年前の日記あったら
			//１年前の日記本文を取得
			
			//リクエストスコープにセット
			request.setAttribute("past_diary", );
		}
		//お知らせ
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
