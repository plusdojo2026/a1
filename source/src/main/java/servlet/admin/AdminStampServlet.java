package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.StampsDAO;
import model.Stamp;

/**
 * Servlet implementation class AdminStampServlet
 */
@MultipartConfig
@WebServlet("/admin/stamp")
public class AdminStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//ログインしているか確認　してなかったらログイン画面に飛ぶ
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a1/login");
//			return;
//		} else if ((int)session.getAttribute("is_admin") != 0) { //管理者じゃなかったらトップページに飛ぶ
//			response.sendRedirect("/a1/user/top");
//			return;
//		} else {
//			//スタンプをとってくる　DAO
		request.setCharacterEncoding("UTF-8");
		//一覧表示処理
		StampsDAO sDao = new StampsDAO();
		List<Stamp> stampList = sDao.selectAll();

		//持ってきたスタンプをリクエストスコープにセット
		request.setAttribute("stampList", stampList);
		//JSPのforeachのところと合わせる
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_stamp.jsp");
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/a1/login");
//			return;
//		} else if ((int)session.getAttribute("is_admin") != 0) { //管理者じゃなかったらトップページに飛ぶ
//			response.sendRedirect("/a1/user/top");
//			return;
//		} 
		
		// アップロード先のパスを動的に取得
        String uploadDir = getServletContext().getRealPath("/img");

        // フォルダが存在しない場合は作成
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // ファイル取得
        Part part = request.getPart("IMAGE");//name
        String fileName = part.getSubmittedFileName();

        // 保存
        part.write(uploadDir + File.separator + fileName);
        
        //データベースに保存
        StampsDAO sDao = new StampsDAO();
		if(sDao.insert(new Stamp(0,uploadDir + File.separator + fileName))) {
			 // 結果画面へ
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_stamp.jsp");
	        dispatcher.forward(request, response);
		}


	}
}
