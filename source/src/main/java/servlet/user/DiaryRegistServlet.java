package servlet.user;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DiariesDAO;
import dao.StampsDAO;
import dao.ThemesDAO;
import model.Diary;
import model.Stamp;
import model.Theme;
import model.User;

@MultipartConfig
@WebServlet("/user/diary-regist")
public class DiaryRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* System.out.println("come"); */
		//スタンプのデータを取得する
		StampsDAO sDAO = new StampsDAO();
		List<Stamp> stampList = sDAO.selectAll();
		request.setAttribute("stampList",stampList);
		//テーマのデータを取得する
		ThemesDAO tDAO = new ThemesDAO();
		ArrayList<Theme> themesList = tDAO.selectAll();
		request.setAttribute("themesList",themesList);
		System.out.println(themesList.size()+":テーマリストのサイズ");
		
		/*
		 * // もしもログインしていなかったらログインサーブレットにリダイレクトする HttpSession session =
		 * request.getSession(); if (session.getAttribute("id") == null) {
		 * response.sendRedirect("/a1/LoginServlet"); return; }
		 */
				
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = now.minusHours(4);
		LocalDate date = today.toLocalDate(); 
		request.setAttribute("date", date);
		/* System.out.println("bbbbbb"+date+"aaaaaaaaaaaaaaaa"); */
		
		//画像のパスをsessionに保存しておく
		HttpSession session  = request.getSession();
		session.setAttribute("pathDir", getServletContext().getRealPath("/img"));
		
		//次、どのページに飛ぶかの記述をする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/diary_regist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");*/
		// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime today = now.minusHours(4);
			LocalDate date = today.toLocalDate(); 
			//int diaryId
			int userId =user.getUserId();
			int weatherCode = Integer.parseInt(request.getParameter("weatherCode"));
			float tempMin = Float.parseFloat(request.getParameter("tempMin"));
			float tempMax = Float.parseFloat(request.getParameter("tempMax"));
			String theme = request.getParameter("theme");
			String stamp = request.getParameter("stamp");
			String diary = request.getParameter("diary");
			int satisfaction = Integer.parseInt(request.getParameter("satisfaction"));
			/* int review = Integer.parseInt(request.getParameter("review")); */
			
			
	        // アップロード先のパスを動的に取得
	        String uploadDir = getServletContext().getRealPath("/img");

	        // フォルダが存在しない場合は作成
	        File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        // ファイル取得
	        Part part = request.getPart("image");
	        String fileName = part.getSubmittedFileName();
	        if(!fileName.equals("")) {
		        System.out.println(uploadDir + File.separator + fileName+":ここだよ！");
		        // 保存
		        part.write(uploadDir + File.separator + fileName);
		        // macでは\、windowsでは/
	        }
	        
	        // データベースに画像の置き場所（パス）を保存する（Diaryインスタンスに入れる）
	        Diary d = new Diary(0,userId, date, weatherCode, tempMin, tempMax, 1,
	    				1, diary, satisfaction, fileName );
			/*DTOから引用
			 * int diaryId, int userId, LocalDate date, int weatherCode, float tempMin,
			 * float tempMax, int theme, int stamp, String diary, int satisfaction, String
			 * image
			 */
	        
	        //DAOをnewする
	        DiariesDAO dDAO = new DiariesDAO();
	        int diaryId = dDAO.insert(d);
	        if (diaryId > 0){
	        	//↓これはいらない
	        	//request.setAttribute("msg", "登録できました！");
	        }else {
	        	request.setAttribute("massage", "登録できませんでした！");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_errormsg.jsp");
	        	dispatcher.forward(request, response);
	        }
		
		//次、どのページに飛ぶかの記述をする
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/top.jsp");
			dispatcher.forward(request, response);
	}

}
