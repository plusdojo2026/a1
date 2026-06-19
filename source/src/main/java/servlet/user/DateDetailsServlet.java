package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiariesDAO;
import dao.SchedulesDAO;
import dao.StampsDAO;
import dao.ThemesDAO;
import model.Diary;
import model.Schedule;
import model.Stamp;
import model.Theme;


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
		
	//クリックした日の予定一覧表示
		
		//セッションスコープからユーザーIDを取得
//		int userId = (int)session.getAttribute("user");
		
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
		
	//クリックした日の日記閲覧表示
		Diary diary = new Diary(0, 1, date, 0, 0, 0, 0, 0, null, 0, null);
		
		DiariesDAO di = new DiariesDAO();
		StampsDAO st = new StampsDAO();
		ThemesDAO th = new ThemesDAO();
		
		List<Diary> dayDi = di.select(diary);
		request.setAttribute("diary", dayDi);
		
		//スタンプIDとパスで連想配列を作っておく
		List<Stamp> stampList = st.selectAll();
		Map<Integer, String> stampMap = new HashMap<>();
		for (Stamp s : stampList) {
			stampMap.put(s.getStampId(), s.getStampPath());
		}
		//リクエストスコープに格納
		request.setAttribute("stamp", stampMap);
		
		//テーマIDとテーマ名で連想配列を作っておく
		List<Theme> themeList = th.selectAll();
		Map<Integer, String> themeMap = new HashMap<>();
		for (Theme t : themeList) {
			themeMap.put(t.getThemeId(), t.getTheme());
		}
		//リクエストスコープに格納
		request.setAttribute("theme", themeMap);
		
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
//		}
		
		//セッションスコープからユーザーIDを取得
//		int userId = (int)session.getAttribute("user");
		
		//リクエストスコープから日付データを取得（カレンダーページ作成後）
		//カレンダーページ作成前のため今日の日付を入れておく
		LocalDate date = LocalDate.now();
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		
		SchedulesDAO sche = new SchedulesDAO();
		
		//以下予定の登録・編集・削除機能　失敗時はエラーページ（user_errormsg.jsp）にフォワードする
		//登録ボタン押下時
		if (request.getParameter("submit").equals("登録")) {
			//リクエストパラメータを取得
			String schedule = request.getParameter("schedule");
			if (sche.insert(new Schedule(0, 1, date, schedule, null))) {
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
		
		//フォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/date_details.jsp");
//		dispatcher.forward(request, response);
		
		//リダイレクト
		response.sendRedirect("/a1/user/date-details");
	}

}

