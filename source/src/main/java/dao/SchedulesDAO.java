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
	
//	public java.sql.Date convertToSqlDate(LocalDate localDate){
//		return java.sql.Date.valueOf(localDate);
//	}

	
	//予定の一覧表示メソッド
	public List<Schedule> scList(Schedule sche){
		Connection conn = null;
		List<Schedule> resultSche = new ArrayList<Schedule>();
		
		try {
			//JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
					+ "Tokyo&connectTimeout =30000",
					"root", "password");
			
			//SQL文作成 ユーザーIDと日付を基に予定を検索する
			String sql = "SELECT schedule_id, schedule FROM schedules WHERE user_id = ? AND date = ? ORDER BY schedule_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//LocalDate→sqlDateの変換
			java.sql.Date sqlDate = java.sql.Date.valueOf(sche.getDate());
			
			pStmt.setInt(1, sche.getUserId());
			pStmt.setDate(2, sqlDate);
			
			//SQL文実行
			ResultSet rs = pStmt.executeQuery();
			
			//確認用
//			System.out.println("SQL文:" + pStmt);
			
			//検索結果を格納
			while (rs.next()) {
				Schedule list = new Schedule(rs.getInt("schedule_id"),
								0, null,
								rs.getString("schedule"),
								null);
				resultSche.add(list);
			}
			
			//確認用
//			System.out.println("検索結果:" + resultSche);
			
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
	
	//予定の登録メソッド
	public boolean insert(Schedule sche){
		Connection conn = null;
		boolean result = false;
		
		try {
			//JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
					+ "Tokyo&connectTimeout =30000",
					"root", "password");
			
			//SQL文作成 
			String sql = "INSERT INTO schedules (user_id, date, schedule) VALUES (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//LocalDate→sqlDateの変換
			java.sql.Date sqlDate = java.sql.Date.valueOf(sche.getDate());
			
			pStmt.setInt(1, sche.getUserId());
			pStmt.setDate(2, sqlDate);
			pStmt.setString(3, sche.getSchedule());
			
			//SQL文実行
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//結果を返す
		return result;
	}
}
