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
			String sql = "SELECT * FROM thmes";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Theme th = new Theme();
				th.setThemeId(rs.getInt("theme_id"));
				th.setTheme(rs.getString("theme"));
				th.setStampId(rs.getInt("stamp_id"));
				th.setDiary_flag(rs.getInt("diary_flag"));
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
	
	
	
	
	
}
