@ -1,12 +1,20 @@
package servlet.admin;

import java.io.IOException;
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

/**
 * Servlet implementation class AdminThemeServlet
@ -20,15 +28,83 @@ public class AdminThemeServlet extends HttpServlet {
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		doGet(request, response);
		
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/a1/LoginServlet");
			return;
		}
//パラメーター取得
		request.setCharacterEncoding("UTF-8");
		
		int themeId=Integer.parseInt(request.getParameter("themeId"));
		String theme=request.getParameter("theme");
		String stampPath=request.getParameter("stampPath");
		
		int diaryFlag=Integer.parseInt(request.getParameter("diaryFlag"));
		
		ThemesDAO ThDAO=new ThemesDAO();
		//登録処理
		if(request.getParameter("submit").equals("登録"))
		{
			
		
		if(ThDAO.insert(new Theme(themeId,theme,stampPath,diaryFlag))
				) {//登録成功
			request.setAttribute("result","/a1/AdminThemeServlet");
		}else {//登録失敗
			request.setAttribute("result","/a1/AdminThemeServlet");
			
		}
		}
		//編集処理
	if(request.getParameter("submit").equals("編集")){
		if(ThDAO.update(new Theme(themeId,theme,stampPath,diaryFlag))){//編集成功
			request.setAttribute("result","/a1/AdminThemeServlet");
		}else {//編集失敗
			request.setAttribute("result","/a1/AdminThemeServlet");
			
		}
	}
	//スタンプ登録　
	
	
		
	
	
	
	
	
	
	
	
	//日替わり設定　
	if(request.getParameter("choice").equals("yes")) {
		
	}else if(request.getParameter("choice").equals("no")) {
		
	}
	
	
	}

}