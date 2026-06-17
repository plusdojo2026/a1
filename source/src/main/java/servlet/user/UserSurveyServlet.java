package servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SurveysDAO;
import model.Survey;

@WebServlet("/user/survey")
public class UserSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード処理
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_survey.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		
		// 取得したデータをモデルに格納する
		Survey survey = new Survey(0, subject, text, null);
		
		// DAOの生成
		SurveysDAO surveysDAO = new SurveysDAO();
		
		// DAOを使ってデータベースにアンケートを登録してもらう。またそのエラー処理。
		if (surveysDAO.insert(survey) == 1) {
			// 成功したらメッセージをセットして、アンケートページにフォワード
			request.setAttribute("message", "アンケートの登録に成功しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_survey.jsp");
			dispatcher.forward(request, response);
		} else {
			// 失敗したらメッセージをセットして、アンケートページにフォワード
			request.setAttribute("message", "アンケートの登録に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/user_survey.jsp");
			dispatcher.forward(request, response);
		}
	}

}
