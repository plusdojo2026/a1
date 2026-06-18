package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Diary;

//import dto.Employee;

public class DiariesDAO {
	
	

	public List<Diary> select(Diary dry) {
		// 結果セットを格納するコレクション
		List<Diary> dryList = new ArrayList<Diary>();
	
		Connection conn = null;
		//↑一旦空っぽにする
	
		
		try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");
				
				// 日記テーブルではスタンプIDを保存する必要があるため、
				// スタンプのパスをスタンプIDに変換する
				//スタンプのSQL文
				String sql = "SELECT stamp_id FROM stamps WHERE stamp_path =?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1,dry.getStamp());
				
				// executeQueryでSQL文を実行して、検索結果をrsに格納する
				ResultSet rs = pStmt.executeQuery();
				
				// スタンプID保存用の変数、使わない０で初期化
				int stampId = 0;
				
				// 検索結果からスタンプIDを受け取る
				while (rs.next()) {
					stampId = rs.getInt("stamp_id");
				}
				
				//テーマのSQL文
				sql = "SELECT theme_id FROM themes WHERE theme = ?";
				/* PreparedStatement */ pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1,dry.getTheme());
				
				
				//SQLへ
				ResultSet rs2 = pStmt.executeQuery();
				int themeId = 0;
				
				while (rs2.next()) {
					themeId = rs.getInt("theme_id");
				}
				
				//↓例外処理
				if(stampId == 0 || themeId == 0) {
					throw new Exception();
				}
				
				//最終形態のSQL文
				sql = "INSERT INTO diaries"
						+ "(diary_id, user_id, date, weather_code, temp_min, temp_max, theme_id, stamp_id, diary, satisfaction, image)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						
				
				//JavaからsqlにDate変換
				java.sql.Date sqlDate = new java.sql.Date(dry.getDate().getTime());
				
						//↓どこに繋ぐか
						pStmt = conn.prepareStatement(sql);
						
						//↓全部入ってる　？を設定するための文、何を検索するのか
						pStmt.setInt(1,dry.getDiaryId());//？の左から１つめ
						pStmt.setInt(2,dry.getUserId());//２つめ
						pStmt.setDate(3,sqlDate);
						pStmt.setInt(4,dry.getWeatherCode());
						pStmt.setFloat(5,dry.getTempMin());
						pStmt.setFloat(6,dry.getTempMax());
						pStmt.setInt(7,themeId);
						pStmt.setInt(8, stampId);
						pStmt.setString(9,dry.getDiary());
						pStmt.setInt(10,dry.getSatisfaction());
						pStmt.setString(7,dry.getImage());
						
						//検索結果取得、db専用 sqlへ
						rs = pStmt.executeQuery();
												
						// 検索結果をコレクションに格納する　Beans
						while (rs.next()) {//rsに何かが入ってるのが分かったら次にいける　true or faulse
							//データ取ってくる
							Diary d = new Diary(rs.getInt("diary_id"), rs.getInt("user_id"), rs.getDate("date"), rs.getInt("weather_code"),
									rs.getFloat("temp_min"), rs.getFloat("temp_max"), rs.getInt("theme_id"), rs.getInt("stamp_id"),
									rs.getString("diary"), rs.getInt("satisfaction"), rs.getString("image"));
							dryList.add(d);//ArrayListに追加（newしてないと追加×）
						}

						// 検索結果が格納されたコレクションを返す
						return dryList;
						
						
			}catch (Exception d) {
				// ↓例外処理がコンソールに出る driverいなかったらと
				d.printStackTrace();
				return null;
			}
			finally {
				// データベースを切断する
				if (conn != null) {
					try {
						conn.close();
					}
					catch (Exception d) {
						d.printStackTrace();
					}
				}
			}
	}
				
}
