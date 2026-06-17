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
						"root", "password");
				
				// SELECT文を準備する
				String sql = "SELECT user_id,mail,name,pass,place,is_admin FROM users WHERE mail=? AND pass=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, us.getMail());
				pStmt.setString(2, us.getPass());
				
				ResultSet rs = pStmt.executeQuery();
				
				if (rs.next()) {
					user = new User(rs.getInt("userId"), rs.getString("mail"),
							rs.getString("name"),"",rs.getString("place"),rs.getInt("isAdmin"));
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
}
