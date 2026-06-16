package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Schedule;

public class SchedulesDAO {
	
	//予定の一覧表示メソッド
	public List<String> scList(Schedule sche){
		Connection conn = null;
		List<String> resultSche = new ArrayList<String>();
		
		try {
			//JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
					+ "Tokyo&connectTimeout =30000",
					"root", "password");
			
			//SQL文作成 ユーザーIDと日付を基に予定を検索する
			String sql = "SELECT schedule FROM schedules WHERE use_id = ? AND date = ? ORDER BY schedule_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//java.util.Date→java.sql.Dateへの変換
			java.sql.Date sqlDate = new java.sql.Date(sche.getDate().getTime());
			
			pStmt.setInt(1, sche.getUserId());
			pStmt.setDate(2, sqlDate);
			
			//SQL文実行
			ResultSet rs = pStmt.executeQuery();
			
			//確認用
			System.out.println("SQL文:" + pStmt);
			
			//検索結果を格納
			while (rs.next()) {
				String list = rs.getString("schedule");
				resultSche.add(list);
			}
			
			//確認用
			System.out.println("検索結果:" + resultSche);
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultSche = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			resultSche = null;
		} finally {
			//データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					resultSche = null;
				}
			}
		}
		
		//結果を返す
		return resultSche;
	}

}
