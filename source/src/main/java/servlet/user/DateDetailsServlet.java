package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiariesDAO;
import dao.SchedulesDAO;
import model.Diary;
import model.DiaryView;
import model.Schedule;
import model.User;


@WebServlet("/user/date-details")
public class DateDetailsServlet extends HttpServlet {
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
		
		//リクエストスコープから日付データを取得
		LocalDate date = LocalDate.parse((String)request.getParameter("date"));
		request.setAttribute("date", date);
		
//		System.out.println("LocalDate.now :" + date);
		
		//検索処理 戻り値＝予定(String)のリスト
		Schedule plan = new Schedule(0, userId, date, null, null);
		SchedulesDAO sche = new SchedulesDAO();
		List<Schedule> resultSche = sche.scList(plan);
		
		//リクエストスコープに格納
		request.setAttribute("scheList", resultSche);
		
	//クリックした日の日記閲覧表示
		Diary diary = new Diary(0, userId, date, 0, 0, 0, 0, 0, null, 0, null);
		
		DiariesDAO di = new DiariesDAO();
		
		List<DiaryView> dayDi = di.select(diary);
		request.setAttribute("diary", dayDi);
		
	//今日(4:oo~28:00)の日付を取得&リクエストスコープに格納 [today]
		//時間の処理
		LocalDateTime dateNow = LocalDateTime.now();
		// Dateから4時間引く　コピーを返すと言われたら、元ある変数に入れられる
		dateNow = dateNow.minusHours(4);
		// それをLocalDateに変換
		LocalDate today = dateNow.toLocalDate();
		request.setAttribute("today", today);
		
		//日付詳細ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/date_details.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		
//		未ログイン時、ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/a1/user/login");
		}
		
		//セッションスコープからユーザーIDを取得
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//リクエストスコープから日付データを取得
		String strDate = (String)request.getParameter("date");
		LocalDate date = LocalDate.parse(strDate);
		
		SchedulesDAO sche = new SchedulesDAO();
		
		//以下予定の登録・編集・削除機能　失敗時はエラーページ（user_errormsg.jsp）にフォワードする
		//登録ボタン押下時
		if (request.getParameter("submit").equals("登録")) {
			//リクエストパラメータを取得
			String schedule = request.getParameter("schedule");
			if (sche.insert(new Schedule(0, userId, date, schedule, null))) {
//				request.setAttribute("msg", "予定を登録しました。");
			} else {
				request.setAttribute("msg", "予定の登録に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_errormsg.jsp");
				dispatcher.forward(request, response);
			}
		//削除ボタン押下時
		} else if (request.getParameter("submit").equals("削除")) {
			//リクエストパラメータを取得
			int scheId = Integer.parseInt(request.getParameter("scheduleId"));
			if (sche.delete(new Schedule(scheId,0,null,null,null))) {
//				request.setAttribute("massage", "予定を削除しました。");
			} else {
				request.setAttribute("massage", "予定の登録に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_errormsg.jsp");
				dispatcher.forward(request, response);
			}
		//編集ボタン押下時
		} else {
			//リクエストパラメータを取得
			int scheId = Integer.parseInt(request.getParameter("scheduleId"));
			String schedule = request.getParameter("schedule");
			if (sche.update(new Schedule(scheId, 0, null, schedule, null))) {
//				request.setAttribute("massage", "予定を更新しました。");
			} else {
				request.setAttribute("massage", "予定の編集に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_errormsg.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		//リダイレクト
		response.sendRedirect("/a1/user/date-details?date=" + strDate);
	}

}

