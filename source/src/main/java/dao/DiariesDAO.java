package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Diary;
import model.DiaryView;

//import dto.Employee;

public class DiariesDAO {
	
	
	//日記登録メソッド
	public int insert(Diary dry) {
		// 結果セットを格納するコレクション
		//List<Diary> dryList = new ArrayList<Diary>();
	
		Connection conn = null;
		//↑一旦空っぽにする
		int newId=0;
		
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
				/*String sql = "SELECT stamp_id FROM stamps WHERE stamp_path =?";
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
				 PreparedStatement  pStmt = conn.prepareStatement(sql);
				
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
				*/
				
				//最終形態のSQL文
				String sql = "INSERT INTO diaries"
						+ "(diary_id, user_id, date, weather_code, temp_min, temp_max, theme_id, stamp_id, diary, satisfaction, image)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
						
				
				//JavaからsqlにDate変換
				java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
				
						//↓どこに繋ぐか
						PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						
						//↓全部入ってる　？を設定するための文、何を検索するのか
						pStmt.setInt(1,0);//？の左から１つめ
						pStmt.setInt(2,dry.getUserId());//２つめ
						pStmt.setDate(3,sqlDate);
						pStmt.setInt(4,dry.getWeatherCode());
						pStmt.setFloat(5,dry.getTempMin());
						pStmt.setFloat(6,dry.getTempMax());
						pStmt.setInt(7,dry.getThemeId());
						pStmt.setInt(8, dry.getStampId());
						pStmt.setString(9,dry.getDiary());
						pStmt.setInt(10,dry.getSatisfaction());
						pStmt.setString(11,dry.getImage());
						
						//検索結果取得、db専用 sqlへ
						pStmt.executeUpdate();
						ResultSet rs = pStmt.getGeneratedKeys();
												
						// 検索結果をコレクションに格納する　Beans
						while (rs.next()) {//rsに何かが入ってるのが分かったら次にいける　true or faulse
							newId = rs.getInt(1);						
						}
						System.out.println(newId);

			}catch (Exception d) {
				// ↓例外処理がコンソールに出る driverいなかったらと
				d.printStackTrace();
				
			}finally {
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
		// 検索結果が格納されたコレクションを返す
			return newId;
	}
	
	//日記をアップデートする
	public boolean update(Diary diary) {
		Connection conn = null;
		//↑一旦空っぽにする
		int ans=0;
		boolean result=false;
		
		try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");
				
				String sql = "UPDATE diaries SET user_id=?, date=?, weather_code=?, temp_min=?,"
						+ "temp_max=?, theme_id=?, stamp_id=?, diary=?, satisfaction=?, image=? WHERE diary_id=?";
				System.out.println(sql);
				//JavaからsqlにDate変換
				java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
				//↓どこに繋ぐか
				PreparedStatement pStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				//↓全部入ってる　？を設定するための文、何を検索するのか
				
				pStmt.setInt(1,diary.getUserId());//？の左から１つめ
				pStmt.setDate(2,sqlDate);//２つめ
				pStmt.setInt(3,diary.getWeatherCode());
				pStmt.setFloat(4,diary.getTempMin());
				pStmt.setFloat(5,diary.getTempMax());
				pStmt.setInt(6,diary.getThemeId());
				pStmt.setInt(7, diary.getStampId());
				pStmt.setString(8,diary.getDiary());
				pStmt.setInt(9,diary.getSatisfaction());
				pStmt.setString(10,diary.getImage());
				pStmt.setInt(11,diary.getDiaryId());
		
				//検索結果取得、db専用 sqlへ
				ans = pStmt.executeUpdate();
				System.out.println(ans+"：結果");
				
				if(ans==1) {
					result=true;
				}
			
				
				}catch (Exception d) {
					// ↓例外処理がコンソールに出る driverいなかったらと
					d.printStackTrace();
					
				}finally {
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
	// 検索結果が格納されたコレクションを返す
		return result;
	}
	
	//日記が存在するかしないか検索するメソッド
	public boolean diaryExist(int userId,LocalDate date) {
		// 結果を受け取る用のlistをつくる
		ArrayList<Diary> loginCheck = new ArrayList<>();
		
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
				String sql = "SELECT diary FROM diaries WHERE user_id = ? AND date = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
					
				//LocalDate→sqlDateの変換
				java.sql.Date sqlDate = java.sql.Date.valueOf(date);
					
				pStmt.setInt(1, userId);
				pStmt.setDate(2, sqlDate);
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
	
				// 結果表をコレクションにコピーする
				while (rs.next()) {		
					Diary diaryCheck = new Diary(
							0, 
							0, 
							null, 
							0,
							0, 
							0, 
							0,
							0,
							rs.getString("diary"), 
							0, 
							null);
					// 結果をlistに入れる
					loginCheck.add(diaryCheck);
				}
				
				// Listのなかが空ならfalse、存在するならtrueをresultに代入するif文 list名.eｍｐｔｙ（）；
				if(!loginCheck.isEmpty()) {
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
	
	//指定したユーザーと日付の日記表示メソッド
	public List<DiaryView> select(Diary dry){
		Connection conn = null;
		List<DiaryView> dryList = new ArrayList<DiaryView>();

		try {
			//JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
					+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
					+ "Tokyo&connectTimeout =30000",
					"root", "password");
			
			//SQL文作成 ユーザーIDと日付を基に日記を検索する
			String sql = "SELECT diary_id, date, weather_code, temp_min, temp_max, themes.theme, "
					+ "stamps.stamp_path, diary, satisfaction, image "
					+ "FROM diaries "
					+ "JOIN themes "
					+ "ON diaries.theme_id = themes.theme_id "
					+ "JOIN stamps "
					+ "ON diaries.stamp_id = stamps.stamp_id "
					+ "WHERE user_id = ? AND date = ?";	
				
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//LocalDate→sqlDateの変換
			java.sql.Date sqlDate = java.sql.Date.valueOf(dry.getDate());
			
			pStmt.setInt(1, dry.getUserId());
			pStmt.setDate(2, sqlDate);
			
			//SQL文実行
			ResultSet rs = pStmt.executeQuery();
			
			//検索結果を格納
			while (rs.next()) {
				DiaryView list = new DiaryView(rs.getInt("diary_id"),
								0,
								null,
								rs.getInt("weather_code"),
								rs.getFloat("temp_min"),
								rs.getFloat("temp_max"),
								rs.getString("theme"),
								rs.getString("stamp_path"),
								rs.getString("diary"),
								rs.getInt("satisfaction"),
								rs.getString("image"));
				dryList.add(list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			dryList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			dryList = null;
		} finally {
			//データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					dryList = null;
				}
			}
		}
		
		//結果を返す
		return dryList;
	}	
		//指定した日記IDの日記表示メソッド
		public List<DiaryView> selectD(int diaryId){
			Connection conn = null;
			List<DiaryView> dryList = new ArrayList<DiaryView>();

			try {
				//JDBCドライバの読み込み
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//データベースに接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
						+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
						+ "Tokyo&connectTimeout =30000",
						"root", "password");
				
				//SQL文作成 ユーザーIDと日付を基に日記を検索する
				String sql = "SELECT diary_id, date, weather_code, temp_min, temp_max, themes.theme, "
						+ "stamps.stamp_path, diary, satisfaction, image "
						+ "FROM diaries "
						+ "JOIN themes "
						+ "ON diaries.theme_id = themes.theme_id "
						+ "JOIN stamps "
						+ "ON diaries.stamp_id = stamps.stamp_id "
						+ "WHERE diaries.diary_id = ? ";	
					
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				
				
				pStmt.setInt(1, diaryId);
				
				
				//SQL文実行
				ResultSet rs = pStmt.executeQuery();
				
				//検索結果を格納
				while (rs.next()) {
					DiaryView list = new DiaryView(rs.getInt("diary_id"),
									0,
									null,
									rs.getInt("weather_code"),
									rs.getFloat("temp_min"),
									rs.getFloat("temp_max"),
									rs.getString("theme"),
									rs.getString("stamp_path"),
									rs.getString("diary"),
									rs.getInt("satisfaction"),
									rs.getString("image"));
					dryList.add(list);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				dryList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				dryList = null;
			} finally {
				//データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						dryList = null;
					}
				}
			}
			
			//結果を返す
			return dryList;
		}
		
		//指定したユーザーと日付の日記表示メソッド
		public List<DiaryView> select(int diaryId){
			Connection conn = null;
			List<DiaryView> dryList = new ArrayList<DiaryView>();

			try {
				//JDBCドライバの読み込み
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//データベースに接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
						+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
						+ "Tokyo&connectTimeout =30000",
						"root", "password");
				
				//SQL文作成 ユーザーIDと日付を基に日記を検索する
				String sql = "SELECT diary_id, date, weather_code, temp_min, temp_max, themes.theme, "
						+ "stamps.stamp_path, diary, satisfaction, image "
						+ "FROM diaries "
						+ "JOIN themes "
						+ "ON diaries.theme_id = themes.theme_id "
						+ "JOIN stamps "
						+ "ON diaries.stamp_id = stamps.stamp_id "
						+ "WHERE diaries.diary_id = ? ";	
					
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				
				
				pStmt.setInt(1, diaryId);
				
				
				//SQL文実行
				ResultSet rs = pStmt.executeQuery();
				
				//検索結果を格納
				while (rs.next()) {
					DiaryView list = new DiaryView(rs.getInt("diary_id"),
									0,
									null,
									rs.getInt("weather_code"),
									rs.getFloat("temp_min"),
									rs.getFloat("temp_max"),
									rs.getString("theme"),
									rs.getString("stamp_path"),
									rs.getString("diary"),
									rs.getInt("satisfaction"),
									rs.getString("image"));
					dryList.add(list);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				dryList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				dryList = null;
			} finally {
				//データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						dryList = null;
					}
				}
			}
			
			//結果を返す
			return dryList;
		}
		
		//指定したユーザーの最新の日記IDを持ってくるメソッド
		public DiaryView selectNewDiaryId(int userId){
			Connection conn = null;
			DiaryView dry = null;

			try {
				//JDBCドライバの読み込み
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//データベースに接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL="
						+ "false&allowPublicKeyRetrieval=true&serverTimezone=Asia/"
						+ "Tokyo&connectTimeout =30000",
						"root", "password");
				
				//SQL文作成 ユーザーIDと日付を基に日記を検索する
				String sql = "SELECT user_id,diary_id, date, weather_code, temp_min, temp_max, themes.theme, "
						+ "stamps.stamp_path, diary, satisfaction, image "
						+ "FROM diaries "
						+ "JOIN themes "
						+ "ON diaries.theme_id = themes.theme_id "
						+ "JOIN stamps "
						+ "ON diaries.stamp_id = stamps.stamp_id "
						+ "WHERE user_id = ? ORDER BY diary_id DESC LIMIT 1";	
					System.out.print(sql);
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setInt(1, userId);
				
				//SQL文実行
				ResultSet rs = pStmt.executeQuery();
				
				//検索結果を格納
				while (rs.next()) {
					dry = new DiaryView(rs.getInt("diary_id"),
									rs.getInt("user_id"),
									rs.getDate("date").toLocalDate(),
									rs.getInt("weather_code"),
									rs.getFloat("temp_min"),
									rs.getFloat("temp_max"),
									rs.getString("theme"),
									rs.getString("stamp_path"),
									rs.getString("diary"),
									rs.getInt("satisfaction"),
									rs.getString("image"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			
			} finally {
				//データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					
					}
				}
			}
			
			//結果を返す
			return dry;
		}
	
	//日記を更新するメソッド
}
				

