package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SchedulesDAO;
import model.Schedule;


@WebServlet("/user/date-details")
public class DateDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//未ログイン時、ログインサーブレットにリダイレクト
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a1/user/login");
//		}
		
	//その日の予定一覧表示
		
		//セッションスコープからユーザーIDを取得
//		int userId = (int)session.getAttribute("user_id");
		
		//リクエストスコープから日付データを取得（カレンダーページ作成後）
		
		//カレンダーページ作成前のため入れておく
		LocalDate date = LocalDate.now();
		
//		System.out.println("LocalDate.now :" + date);
		
		//検索処理 戻り値＝予定(String)のリスト
		//ログインページ作成後、1=userIdに変更する
		Schedule plan = new Schedule(0, 1, date, null, null);
		SchedulesDAO sche = new SchedulesDAO();
		List<Schedule> resultSche = sche.scList(plan);
		
		//リクエストスコープに格納
		request.setAttribute("scheList", resultSche);
		
		//日付詳細ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/date_details.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//未ログイン時、ログインサーブレットにリダイレクト
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a1/user/login");
		
		//セッションスコープからユーザーIDを取得
//		int userId = (int)session.getAttribute("user_id");
		
		//リクエストスコープから日付データを取得（カレンダーページ作成後）
		//カレンダーページ作成前のため入れておく
		LocalDate date = LocalDate.now();
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String schedule = request.getParameter("registSchedule");
		
		SchedulesDAO sche = new SchedulesDAO();
		
		//登録ボタン押下時
		if (request.getParameter("submit").equals("登録")) {
			if (sche.insert(new Schedule(0, 1, date, schedule, null))) {
				request.setAttribute("msg", "予定を登録しました。");
			} else {
				request.setAttribute("msg", "予定の登録に失敗しました");
			}
		//編集ボタン押下時
//		} else if (request.getParameter("submit").equals("編集")) {
//		
//		//削除ボタン押下時
//		} else {
//			
		}
		
		//フォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/date_details.jsp");
//		dispatcher.forward(request, response);
		
		//リダイレクト
		response.sendRedirect("/a1/user/date-details");
	}

}

