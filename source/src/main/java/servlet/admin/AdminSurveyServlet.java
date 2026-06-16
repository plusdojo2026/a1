package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SurveysDAO;
import model.Survey;

@WebServlet("/admin/survey")
public class AdminSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOを作成する
		SurveysDAO surveysDAO = new SurveysDAO();
		
		// アンケート一覧を取得する
		List<Survey> surveyList = surveysDAO.findAll();
		
		// リクエストスコープにセットする
		request.setAttribute("survey_list", surveyList);
		
		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_survey.jsp");
		dispatcher.forward(request, response);
	}

}
