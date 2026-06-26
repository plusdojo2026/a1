package servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NewsDAO;
import model.News;
import model.User;

/**
 * Servlet implementation class UserNewsDetailsServlet
 */
@WebServlet("/user/news-details")
public class UserNewsDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//未ログイン時、ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/a1/login");
			return;
		}
		
		
		NewsDAO nDAO=new NewsDAO();
		List<News> newsList = nDAO.sellectAll();
		request.setAttribute("newsList",newsList);
       
		String id=request.getParameter("id");
		String subject = request.getParameter("subject");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_news_detail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
