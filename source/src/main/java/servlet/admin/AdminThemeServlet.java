package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StampsDAO;
import dao.ThemesDAO;
import model.Stamp;
import model.Theme;

/**
 * Servlet implementation class AdminThemeServlet
 */
@WebServlet("/admin/theme")
public class AdminThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
			//response.sendRedirect("/a1/LoginServlet");
			//return;
	//	}
		StampsDAO sDAO=new StampsDAO();
	    List<Stamp> stampList = sDAO.selectAll();
		request.setAttribute("stampList",stampList);
		ThemesDAO thDAO=new ThemesDAO();
		List<Theme>themeList=thDAO.selectAll();
		request.setAttribute("themeList",themeList);
	
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_theme.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/a1/LoginServlet");
		//	return;
	//	}
//パラメーター取得
		request.setCharacterEncoding("UTF-8");
		int themeId=0;
		//テーマidに何かしら値が入っていたら
		if(request.getParameter("themeId")!="") {
			//ちゃんと値を設定する（入ってなかったら0のままにする）
			 themeId=Integer.parseInt(request.getParameter("themeId"));
		}		
		String theme=request.getParameter("theme");
		int stampId=Integer.parseInt(request.getParameter("stampId"));
		int diaryFlag=Integer.parseInt(request.getParameter("choice"));//ラジオボタンで０か１をっとてくるのを文字型に直すこと必要
		
		ThemesDAO ThDAO=new ThemesDAO();
		//登録処理
		if(request.getParameter("submit").equals("登録"))
		{
			
		
		if(ThDAO.insert(new Theme(themeId,theme,stampId,diaryFlag))
				) {//登録成功	
			response.sendRedirect("/a1/admin/theme");
			
		}else {//登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_theme.jsp");
	        dispatcher.forward(request, response);
		}
		}
		//編集処理
	if(request.getParameter("submit").equals("保存")){
		if(ThDAO.update(new Theme(themeId,theme,stampId,diaryFlag))){//編集成功
			response.sendRedirect("/a1/admin/theme");
		}else {//編集失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_theme.jsp");
	        dispatcher.forward(request, response);
			
		}
	}
	

	

	
	}
}











   
   
