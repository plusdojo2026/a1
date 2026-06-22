package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserInf;

public class UsersInfDAO {
	//リストの検索
	public ArrayList<UserInf> select(UserInf ui){
		
		//マイバックを用意する user inf bagでuibとします
		ArrayList<UserInf> uib = new ArrayList<UserInf>();
		
		Connection conn = null;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
					+ "Tokyo&connectTimeout=30000",
					"root", "password");
			
			// SELECT文を準備する
			String sql = "SELECT sunny,cloudy,rainy,snowy,cold,ideal,hot FROM users_inf "
					+ "where user_inf_id=? and user_id=? and steak=? and"
					+ "sunny=? and cloudy=? and rainy=? and snowy=? and cold=? and ideal=? and hot=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setFloat(1, ui.getUserInfId());
			pStmt.setFloat(1, ui.getUserId());
			pStmt.setFloat(1, ui.getSteak());
			pStmt.setFloat(1, ui.getSunny());
			pStmt.setFloat(2, ui.getCloudy());
			pStmt.setFloat(3, ui.getRainy());
			pStmt.setFloat(3, ui.getSnowy());
			pStmt.setFloat(4, ui.getCold());
			pStmt.setFloat(5, ui.getIdeal());
			pStmt.setFloat(6, ui.getHot());
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				UserInf usi = new UserInf(0, 0, 0,
							   rs.getFloat("sunny"), 
							   rs.getFloat("cloudy"),
							   rs.getFloat("rainy"),
							   rs.getFloat("snowy"),
							   rs.getFloat("cold"),
							   rs.getFloat("ideal"),
							   rs.getFloat("hot"));
				uib.add(usi);
							   
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
		
		return uib;
		
	}
}
