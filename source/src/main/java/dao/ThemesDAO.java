package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Theme;

public class ThemesDAO {

	//テーマを全て取得するメソッド
	public ArrayList<Theme> selectAll(){
		
		ArrayList<Theme> themeList = new ArrayList<>();
		Connection conn = null;
		

		try {
			// JDBCドライバを読み込む　mysql使ってたら変更いらない
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM themes";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Theme th = new Theme();
				th.setThemeId(rs.getInt("theme_id"));
				th.setTheme(rs.getString("theme"));
				th.setStampId(rs.getInt("stamp_id"));
				th.setDiaryFlag(rs.getInt("diary_flag"));
				themeList.add(th);
			}
		} catch (SQLException e) {
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
		
		
		return themeList;
	}
	
	//スタンプ表示？	

	//テーマ登録
	public boolean insert(Theme theme) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
					,"root", "password");

			// SQL文を準備する
			String sql =" INSERT INTO themes(theme,stamp_id,diary_flag) VALUES (?,?,?)";
			
			System.out.println(sql);
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (theme.getTheme() != null) {
				pStmt.setString(1,theme.getTheme ());
			} else {
				pStmt.setString(1, "");
			}
			pStmt.setInt(2,theme.getThemeId ());
			if (theme.getStampId() != 0) {
				pStmt.setInt(3,theme.getStampId ());
			} else {
				pStmt.setString(3, "");
			}
			
			pStmt.setInt(4,theme.getDiaryFlag ());
			
		
		
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		 catch (SQLException e) {
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

		// 結果を返す
		return result;
		}

	//テーマ編集
	public boolean update(Theme theme) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
					,"root", "password");

			// SQL文を準備する
			String sql =" UPDATE Themes SET  theme_id=?,theme=?,stamp_id=?,diary_flag=?";
			
			System.out.println(sql);
			PreparedStatement pStmt = conn.prepareStatement(sql);

		
				pStmt.setInt(1,theme.getThemeId ());
		
				pStmt.setString(2,theme.getTheme ());
			
				pStmt.setInt(3,theme.getStampId ());
			
			pStmt.setInt(4,theme.getDiaryFlag ());
			
		
		
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		 catch (SQLException e) {
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
		// 結果を返す
		return result;
		
	}
}
	

