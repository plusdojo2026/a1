package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Survey;

public class SurveysDAO {

	// アンケート登録用メソッド
	public int insert(Survey survey) {
		int ans = 0;
		// データベースに接続と切断を行うオブジェクト
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"a1", "UA7T7B9jN2n7HnBT");
			
			String sql = "INSERT INTO surveys(subject, text) VALUES(?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, survey.getSubject());
			pStmt.setString(2, survey.getText());
			
			ans = pStmt.executeUpdate();
			
		
		}catch (Exception e) {
			// 例外処理
			e.printStackTrace();
			return ans;
		}finally {
			// データベースを切断する
			if (conn != null) {
				try {
					conn.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ans;
	}
	
	// アンケート全検索用メソッド
	public List<Survey> findAll() {
		List<Survey> surveyList = new ArrayList<>();
		// データベースに接続と切断を行うオブジェクト
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"a1", "UA7T7B9jN2n7HnBT");
			
			String sql = "SELECT * FROM surveys;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Survey survey = new Survey(rs.getInt("survey_id"), rs.getString("subject"), rs.getString("text"), rs.getDate("filled_date").toLocalDate());
				surveyList.add(survey);
			}
			
		
		}catch (Exception e) {
			// 例外処理
			e.printStackTrace();
			return null;
		}finally {
			// データベースを切断する
			if (conn != null) {
				try {
					conn.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return surveyList;
	}
}
