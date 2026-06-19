package servlet.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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


@WebServlet("/user/diary-regist")
public class DiaryRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//スタンプのデータを取得する
		StampsDAO sDAO = new StampsDAO();
		List<Stamp> stampList = sDAO.selectAll();
		request.setAttribute("stampList",stampList);
		//テーマのデータを取得する
		ThemesDAO tDAO = new ThemesDAO();
		ArrayList<Theme> themesList = tDAO.selectAll();
		request.setAttribute("themesList",themesList);
		
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
					response.sendRedirect("/a1/LoginServlet");
					return;
				}
				
		//次、どのページに飛ぶかの記述をする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/diary_regist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストパラメータを取得する
			//request.setCharacterEncoding("UTF-8");
			//int diaryId
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime today = now.minusHours(4);
			LocalDate date = today.toLocalDate(); 
			
			//int userId = Integer.parseInt(request.getParameter(""));
			//int weatherCode = Integer.parseInt(request.getParameter(""));
			//float TempMin = Float.parseFloat(getParameter(""));
			//float TempMax = Float.parseFloat(getParameter(""));
			String theme = request.getParameter("theme");
			String stamp = request.getParameter("stamp");
			String diary = request.getParameter("diary");
			int satisfaction = Integer.parseInt(request.getParameter("satisfaction")
			int review = Integer.parseInt(request.getParameter("review"));
			
			
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

	        // 保存
	        part.write(uploadDir + File.separator + fileName);
	        // macでは\、windowsでは/
	        
	        // データベースに画像の置き場所（パス）を保存する（Diaryインスタンスに入れる）
	        Diary d = new Diary(-1,userId, date, weatherCode, float tempMin, float tempMax, int theme,
	    			int stamp, String diary, int satisfaction, String image);
	        
	        //DAOをnewする
	        DiariesDAO dDAO = new DiariesDAO();
	        
	/*		userId
			date
			weatherCode
			tempMin
			tempMax
			String theme = request.getParameter("theme");
			String stamp
			diary
			satisfaction
			image
			
			DiariesDAO
	*/
			
			
	
		
		
		//次、どのページに飛ぶかの記述をする
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/diary_regist.jsp");
			dispatcher.forward(request, response);
	}

}
