package servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StampsDAO;
import dao.ThemesDAO;
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
			int theme = Integer.parseInt(request.getParameter("theme"));
			int stamp = Integer.parseInt(request.getParameter("stamp"));
			String diary = request.getParameter("diary");
			int review = Integer.parseInt(request.getParameter("review"));
			
			//↑あと、画像を取得
			
			//その後に取得したものを全てdtoにまとめる
			
			//daoを実体化
			
			//daoに上で作ったdtoを渡して登録してもらう
			
			//登録した結果によって、次の遷移先jspを選択する
	
			
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
	}

}
