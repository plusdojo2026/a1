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
			
			String weathersql = "SELECT weather_code,"
					+ "AVG(CASE WHEN weather_code = 0 then "
					+ "(SELECT AVG(satisfaction) from diaries where weather_code = 0) END) AS avg_sunny,"
					+ "AVG(CASE WHEN weather_code = 1 then "
					+ "(SELECT AVG(satisfaction) from diaries where weather_code = 1) END) AS avg_cloudy,"
					+ "AVG(CASE WHEN weather_code = 2 then "
					+ "(SELECT AVG(satisfaction) from diaries where weather_code = 2) END) AS avg_rainy,"
					+ "AVG(CASE WHEN weather_code = 3 then "
					+ "(SELECT AVG(satisfaction) from diaries where weather_code = 3) END) AS avg_snowy "
					+ "FROM diaries "
					+ "WHERE weather_code IN (0,1,2,3)";
			
			String tempsql = "SELECT temp_max,"
					+ "AVG(CASE WHEN temp_max < 10 then "
					+ "(SELECT AVG(satisfaction) from diaries where temp_max < 10) END) AS avg_cold,"
					+ "AVG(CASE WHEN temp_max >= 10 AND temp_max <= 30 then "
					+ "(SELECT AVG(satisfaction) from diaries where temp_max >= 10 AND temp_max <= 30) END) AS avg_ideal,"
					+ "AVG(CASE WHEN temp_max > 30 then "
					+ "(SELECT AVG(satisfaction) from diaries where temp_max > 30) END) AS avg_hot,"
					+ "FROM diaries ";
			
			String inSql = "INSERT INTO  users_inf "
					+ "avg_sunny,avg_cloudy,avg_rainy,avg_snowy,"
					+ "avg_cold,avg_ideal,avg_hot"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			
			PreparedStatement wepStmt = conn.prepareStatement(weathersql);
			PreparedStatement tempStmt = conn.prepareStatement(tempsql);
			PreparedStatement inpStmt = conn.prepareStatement(inSql);
			
			ResultSet rs = wepStmt.executeQuery();
			ResultSet rs1 = tempStmt.executeQuery();
			
			if (rs.next() && rs1.next()) {
				//userinfのusi
				UserInf usi = new UserInf(0, 0, 0,
							   rs.getFloat("avg_sunny"), 
							   rs.getFloat("avg_cloudy"),
							   rs.getFloat("avg_rainy"),
							   rs.getFloat("avg_snowy"),
							   rs.getFloat("avg_cold"),
							   rs.getFloat("avg_ideal"),
							   rs.getFloat("avg_hot"));
				uib.add(usi);		   
				
				inpStmt.setFloat(1,rs.getFloat("avg_sunny"));
				inpStmt.setFloat(2,rs.getFloat("avg_cloudy"));
				inpStmt.setFloat(3,rs.getFloat("avg_rainy"));
				inpStmt.setFloat(4,rs.getFloat("avg_snowy"));
				inpStmt.setFloat(5,rs.getFloat("avg_cold"));
				inpStmt.setFloat(6,rs.getFloat("avg_ideal"));
				inpStmt.setFloat(7,rs.getFloat("avg_hot"));
				
				inpStmt.executeUpdate();
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
