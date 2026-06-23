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

import dao.DiariesDAO;
import dao.SchedulesDAO;
import model.Diary;
import model.Schedule;
import model.User;

@WebServlet("/user/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//未ログイン時、ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/a1/login");
			return;
		}
		
		// ユーザーの予定リストを作る
		List<Schedule> scheduleList = new ArrayList<>();
		SchedulesDAO schedulesDAO = new SchedulesDAO();
		Schedule schedule = new Schedule(-1, user.getUserId(), null, null, null);
		scheduleList = schedulesDAO.selectByUserId(schedule);
		
		request.setAttribute("scheduleList", scheduleList);
		
		// ユーザーの日記リストを作る
		List<Diary> diaryList = new ArrayList<>();
		DiariesDAO diariesDAO = new DiariesDAO();
		Diary diary = new Diary(-1, user.getUserId(), null, -1, 0, 0, -1, -1, null, -1, null);
		// diaryList = diariesDAO.selectByUserId(diary);
		
		// フォワード処理
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/calendar.jsp");
		dispatcher.forward(request, response);
	}

}
