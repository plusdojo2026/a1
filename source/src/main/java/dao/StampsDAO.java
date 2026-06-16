package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Stamp;

public class StampsDAO {
	//画像一覧表示するメソッド
	public List<Stamp> select(Stamp stampImg){ //引数　stampImg
		
		Connection conn = null;//SQLに接続するよ
		
		ArrayList<Stamp> stamplist = new ArrayList<Stamp>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= "
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"root", "password");
			
			// SQL文を準備する  変わる情報が入る時は、？で置き換えるよ
			String sql = "SELECT stamp_path FROM stamps ORDER BY stamp_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//pStmt.setString(1, stampImg.getStampPath());  ★一覧表示の時はいらない！（全部持ってくるとき）
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {		
				Stamp stamp = new Stamp(
					rs.getInt("stamp_id"),
					rs.getString("stamp_path")
				);
				stamplist.add(stamp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			stamplist = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			stamplist = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					stamplist = null;
				}
			}
		}
		
		return stamplist;
	}
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Stamp stampImg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= "
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"root", "password");
			
			// SQL文を準備する
				String sql = "INSERT INTO stamps VALUES (0, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, stampImg.getStampPath());
				
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
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
		
		return result;
	}

}
