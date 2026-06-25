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
			

			String weathersql = "SELECT "
				    + "AVG(CASE WHEN weather_code = 0 THEN satisfaction END) AS avg_sunny, "
				    + "AVG(CASE WHEN weather_code = 1 THEN satisfaction END) AS avg_cloudy, "
				    + "AVG(CASE WHEN weather_code = 2 THEN satisfaction END) AS avg_rainy, "
				    + "AVG(CASE WHEN weather_code = 3 THEN satisfaction END) AS avg_snowy "
				    + "FROM diaries "
				    + "WHERE user_id = ?  AND weather_code IN (0,1,2,3)";
			

			System.out.println(weathersql);
			System.out.println();
			System.out.println();
			System.out.println();

			PreparedStatement wepStmt = conn.prepareStatement(weathersql);
			
			wepStmt.setInt(1, ui.getUserId());
			
			ResultSet rs = wepStmt.executeQuery();
			if (rs.next()) {
				//userinfのusi
				UserInf usi = new UserInf(0, ui.getUserId(), 0,
							   rs.getFloat("avg_sunny"),
							   rs.getFloat("avg_cloudy"),
							   rs.getFloat("avg_rainy"),
							   rs.getFloat("avg_snowy"),
							   0,0,0
							   );
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
	
	public ArrayList<UserInf> select1(UserInf ui1){
			
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
			
		
				String tempsql = "SELECT "
					    + "AVG(CASE WHEN temp_max < 10 THEN satisfaction END) AS avg_cold, "
					    + "AVG(CASE WHEN temp_max >= 10 AND temp_max <= 30 THEN satisfaction END) AS avg_ideal, "
					    + "AVG(CASE WHEN temp_max > 30 THEN satisfaction END) AS avg_hot "
					    + "FROM diaries WHERE user_id = ?";
				
				PreparedStatement tempStmt = conn.prepareStatement(tempsql);

				tempStmt.setInt(1, ui1.getUserId());
				
				ResultSet rs1 = tempStmt.executeQuery();
				
				if (rs1.next()) {
					//userinfのusi
					UserInf usi = new UserInf(0, ui1.getUserId(), 0, 0, 0, 0, 0,
								   rs1.getFloat("avg_cold"),
								   rs1.getFloat("avg_ideal"),
								   rs1.getFloat("avg_hot"));
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
//public ArrayList<UserInf> select2(UserInf ui2){
//		
//		//マイバックを用意する user inf bagでuibとします
//		ArrayList<UserInf> uib = new ArrayList<UserInf>();
//		
//		Connection conn = null;
//	
//		try {
//			// JDBCドライバを読み込む
//			Class.forName("com.mysql.cj.jdbc.Driver");
//	
//			// データベースに接続する
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
//					+ "useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
//					+ "Tokyo&connectTimeout=30000",
//					"root", "password");			
//			
//			String inSql = "INSERT INTO users_inf "
//					+ "(user_id ,avg_sunny,avg_cloudy,avg_rainy,avg_snowy,"
//					+ "avg_cold,avg_ideal,avg_hot) "
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//			
//			PreparedStatement inpStmt = conn.prepareStatement(inSql);
//				inpStmt.setInt(1,ui2.getUserId());
//				inpStmt.setFloat(2,ui2.getSunny());
//				inpStmt.setFloat(3,ui2.getCloudy());
//				inpStmt.setFloat(4,ui2.getRainy());
//				inpStmt.setFloat(5,ui2.getSnowy());
//				inpStmt.setFloat(6,ui2.getCold());
//				inpStmt.setFloat(7,ui2.getIdeal());
//				inpStmt.setFloat(8,ui2.getHot());
//				
//				inpStmt.executeUpdate();
//			
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return uib;
//		
//	}
}
