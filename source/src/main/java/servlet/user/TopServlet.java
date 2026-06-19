package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiariesDAO;
import dao.ThemesDAO;
import model.Diary;
import model.Theme;

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
		date = date.minusHours(4);
		// それをLocalDateに変換
		LocalDate localDate = date.toLocalDate();
		
		//セッションスコープからユーザーID、DATEを取得
		int userId = (int)session.getAttribute("user_id");
		//DAOを持ってくる
		DiariesDAO dDao = new DiariesDAO();
		boolean result = dDao.diaryExist((int)session.getAttribute("user_id"),localDate );
		//分岐
		if (result == false) { //今日の日記がない場合
			//テーマを全部取得する
			ThemesDAO dao = new ThemesDAO();
			//データベースに繋いで、全てのテーマのデータを取得してarraylistに格納
			ArrayList<Theme> th =  dao.selectAll();
			//下で行うが、DiaryFlagが0のもののThemeIdだけを格納するarraylistを作る今は空
			ArrayList<Integer> themeIdList = new ArrayList<>();
			//上のArrayListにDiaryFlagが0のもののThemeIdだけを格納する
			for(Theme t : th) {
				if(t.getDiaryFlag()==0) {
					themeIdList.add(t.getThemeId());
				}
			}
			//格納した箱の要素番号をランダムに選択肢、randomNumに入れる
			int randomNum = (int) (Math.random() * themeIdList.size() )+1;
			//上記で選択した箱の要素番号の中身をchoiceIdに入れる
			int choiceId = themeIdList.get(randomNum);
			
			//その日の日記テーブルをあらかじめ作成する
			Diary di = new Diary();
			di.setThemeId(choiceId);
			di.setUserId((int)session.getAttribute("user_id"));
			di.setDate(new Date());
			//作成した上記のデータをテーブルにインサート
			boolean ans = dDao.insert(di);
			
			if(ans == true ) {
				//上でインサートした行のデータを取得して、requestにセット
			}
				
			
			
			
		}
		//天気表示
		
		//気温表示
		
		//継続日数表示
		
		//１年前の今日の日記		
		// Dateから1年引く　コピーを返すと言われたら、元ある変数に入れられる
		LocalDate oneYearAgo = localDate.minusYears(1);
		
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
