package servlet.user;

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
//		List<Stamp> themesList = tDAO.selectAll();
//		request.setAttribute("themesList",themesList);
		
		//次、どのページに飛ぶかの記述をする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/diary_regist.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//次、どのページに飛ぶかの記述をする
	}

}
