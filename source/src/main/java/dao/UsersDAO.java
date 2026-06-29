package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UsersDAO {
	//引数はus
	public User isLoginOK(User us) {
			Connection conn = null;
			User user = null;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
						+ "useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"a1", "UA7T7B9jN2n7HnBT");
				
				// SELECT文を準備する
				String sql = "SELECT user_id,mail,name,pass,place,is_admin FROM users WHERE mail=? AND pass=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, us.getMail());
				pStmt.setString(2, us.getPass());
				
				ResultSet rs = pStmt.executeQuery();
				
				if (rs.next()) {
					user = new User(rs.getInt("user_id"), rs.getString("mail"),
							rs.getString("name"),"",rs.getString("place"),rs.getInt("is_admin"));
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
			
			return user;
			
	}
		
	public boolean insert(User us) {
		Connection conn = null;
		boolean result = false;
	
		try {
			
			// JDBCドライバを読み込む
			//船乗り運転手を持ってくる
			Class.forName("com.mysql.cj.jdbc.Driver");//接続するための道具
			
			// データベースに接続する　
			//地図を完成させ、通行所を同封させる
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"a1", "UA7T7B9jN2n7HnBT");
			
			//sql文を準備する
			String sql = "INSERT INTO users VALUES (0, ?, ?, ?,default,default)";
			//船を用意し、必要なものを持っていく。
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
		
		if (us.getMail() != null) {
			pStmt.setString(1, us.getMail());
		} else {
			pStmt.setString(1, "");
		}
		if (us.getName() != null) {
			pStmt.setString(2, us.getName());
		} else {
			pStmt.setString(2, "");
		}
		if (us.getPass() != null) {
			pStmt.setString(3, us.getPass());
		} else {
			pStmt.setString(3, "");
		}
		
		
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

		// 結果を返す
		return result;
	}
	public boolean update(User us) {
		Connection conn = null;
		boolean result = false;
	
		try {
			
			// JDBCドライバを読み込む
			//船乗り運転手を持ってくる
			Class.forName("com.mysql.cj.jdbc.Driver");//接続するための道具
			
			// データベースに接続する　
			//地図を完成させ、通行所を同封させる
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "useSSL= false&allowPublicKeyRetrieval=true&serverTimezone="
					+ "Asia/Tokyo&connectTimeout=30000",
					"a1", "UA7T7B9jN2n7HnBT");
			
			//sql文を準備する
			String sql = "UPDATE users set pass=? WHERE mail=? and name=?";
			//船を用意し、必要なものを持っていく。
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			if (us.getPass() != null) {
				pStmt.setString(1, us.getPass());
			} else {
				pStmt.setString(1, "");
			}
			if (us.getMail() != null) {
				pStmt.setString(2, us.getMail());
			} else {
				pStmt.setString(2, "");
			}
			if (us.getName() != null) {
				pStmt.setString(3, us.getName());
			} else {
				pStmt.setString(3, "");
			}
			
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

			// 結果を返す
			return result;
	}
}
