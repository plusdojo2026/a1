package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiariesDAO;
import dao.ThemesDAO;
import model.Diary;
import model.DiaryView;
import model.Theme;
import model.User;

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
		
		request.setCharacterEncoding("UTF-8");
		
		//その日最初のログインかどうかを確認する
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/a1/user/login");
		}
		
		//時間の処理
		LocalDateTime date = LocalDateTime.now();
		// Dateから4時間引く　コピーを返すと言われたら、元ある変数に入れられる
		date = date.minusHours(4);
		// それをLocalDateに変換
		LocalDate localDate = date.toLocalDate();
		
		//セッションスコープからユーザーID、DATEを取得
		User u = (User)session.getAttribute("user");
		int userId = u.getUserId();
		//DAOを持ってくる
		DiariesDAO dDao = new DiariesDAO();
		boolean result = dDao.diaryExist(userId,localDate);
		//分岐
		if (result == false) { //今日の日記がない場合
			//テーマを全部取得する
			ThemesDAO dao = new ThemesDAO();
			//データベースに繋いで、全てのテーマのデータを取得してarraylistに格納
			ArrayList<Theme> th =  dao.selectAll();
			//下で行うが、DiaryFlagが0のもののThemeIdだけを格納するarraylistを作る　今は空
			ArrayList<Integer> themeIdList = new ArrayList<>();
			//
			ArrayList<Integer> stampIdList = new ArrayList<>();
			//上のArrayListにDiaryFlagが0のもののThemeIdだけを格納する
			for(Theme t : th) {
				if(t.getDiaryFlag()==0) {
					themeIdList.add(t.getThemeId());
					stampIdList.add(t.getStampId());
				}
			}
			//格納した箱の要素番号をランダムに選択肢、randomNumに入れる
			int randomNum = (int) (Math.random() * themeIdList.size() );
			//上記で選択した箱の要素番号の中身をchoiceIdに入れる
			int choiceId = themeIdList.get(randomNum);
			
			//その日の日記テーブルをあらかじめ作成する
			Diary di = new Diary();
			di.setThemeId(choiceId);
			di.setStampId(stampIdList.get(randomNum));
			User user = (User)session.getAttribute("user");
			di.setUserId((int)user.getUserId());
			di.setDate(localDate);
			//作成した上記のデータをテーブルにインサート
			int diaryId = dDao.insert(di);
			System.out.println(diaryId+"入れた日記のidだよ");
			if(diaryId > 0) {
				//上でインサートした行のデータを取得して、requestにセット
				request.setAttribute("diaryId",diaryId );//左がJSPのEL式に対応してる部分、右が８０行目
			}
			List<DiaryView> diaryList =  dDao.selectD(diaryId);
			System.out.println(diaryList.size()+"：とってきた日記だよ");
			request.setAttribute("diaryList", diaryList);
			//テーマだけセット
			request.setAttribute("theme", diaryList.get(0).getTheme());
			
		}

		//継続日数表示

		
		//１年前の今日の日記		
		// Dateから1年引く　コピーを返すと言われたら、元ある変数に入れられる
		LocalDate oneYearAgo = localDate.minusYears(1);
		
		Diary diary = new Diary(0, 1, oneYearAgo, 0, 0, 0, 0, 0, null, 0, null);
		DiariesDAO di = new DiariesDAO();
		List<DiaryView> dayDi = di.select(diary);
		if(dayDi.size() != 0) {//１年前の日記あったら
			//１年前の日記本文を取得
			String diaryBody = dayDi.get(0).getDiary();
			
			//リクエストスコープにセット
			request.setAttribute("pastDiary",diaryBody);
		}
		//お知らせ
		
		//トップページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/top.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
