package servlet.admin;

import java.io.IOException;
import java.time.LocalDate;
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
 * Servlet implementation class AdminNewsServlet
 */
@WebServlet("/admin/news")
public class AdminNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//パラメーター取得
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("newsId")+":ニュースidだよ");
		int newsId=0;
		
		
		String subject=request.getParameter("subject");
		String text=request.getParameter("text");
		int isDisplay=0;
		if(request.getParameter("isDisplay")!=null) {
			isDisplay=Integer.parseInt(request.getParameter("isDisplay"));
		}
		LocalDate submittedAt=LocalDate.now();
		if(request.getParameter("submittedAt")!=null) {
			 submittedAt=LocalDate.parse(request.getParameter("submittedAt"));
		}
	
		
	
		
               NewsDAO neDAO=new NewsDAO();
		
		//登録処理
               
               
				if(request.getParameter("submit").equals("登録")){
					if(neDAO.insert(new News(newsId,subject,text,isDisplay,submittedAt))
						) {//登録成功	
					
					response.sendRedirect("/a1/admin/news");
					

				}else {//登録失敗
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news.jsp");
			        dispatcher.forward(request, response);
				}
				}
				//編集処理
								if(request.getParameter("submit").equals("保存")){
									
										newsId=Integer.parseInt(request.getParameter("newsId"));
									
					if(neDAO.update(new News(newsId,subject,text,isDisplay,submittedAt))){//編集成功
						response.sendRedirect("/a1/admin/news");
					}else {//編集失敗
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news.jsp");
				        dispatcher.forward(request, response);
					}
					}
				//削除処理
				
				if(request.getParameter("submit").equals("削除")){
					newsId=Integer.parseInt(request.getParameter("newsId"));
					if(neDAO.delete(new News(newsId,subject,text,isDisplay,submittedAt))){//編集成功
						response.sendRedirect("/a1/admin/news");
					}else {//編集失敗
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news.jsp");
				        dispatcher.forward(request, response);
					}
					}
				//送信処理
				/**/

}
}
