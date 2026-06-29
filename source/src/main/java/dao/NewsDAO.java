package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.News;

public class NewsDAO {
	
	//お知らせを全て取得するメソッド
public ArrayList<News>sellectAll(){
	ArrayList<News> NewsList = new ArrayList<>();
	Connection conn = null;
	try {
		// JDBCドライバを読み込む　mysql使ってたら変更いらない
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"a1", "UA7T7B9jN2n7HnBT");

		// SQL文を準備する
		String sql = "SELECT*FROM news";
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
	

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		// 結果表をコレクションにコピーする
		
		
		while (rs.next()) {
			News ne= new News();
			ne.setNewsId(rs.getInt("news_id"));
			ne.setSubject(rs.getString("subject"));
			ne.setText(rs.getString("text"));
			ne.setIsDisplay(rs.getInt("is_display"));
			ne.setSubmittedAt(rs.getDate("submitted_at").toLocalDate());
			NewsList.add(ne);
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
	
	
	return NewsList;
}
//登録メソッド
public boolean insert(News news) {
	Connection conn = null;
	boolean result = false;
	
	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
				,"a1", "UA7T7B9jN2n7HnBT");

		// SQL文を準備する
		String sql =" INSERT INTO news(subject,text,is_display) VALUES (?,?,?)";
		
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);

		if (news.getSubject() != null) {
			pStmt.setString(1,news.getSubject ());
		} else {
			pStmt.setString(1, "");
		}
		pStmt.setString(2,news.getText ());
		if (news.getText() != null) {
			pStmt.setInt(3,news.getIsDisplay ());
		} else {
			pStmt.setString(3, "");
		}
		
		
	
	
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
//編集メソッド
public boolean update(News news) {
	Connection conn = null;
	boolean result = false;
	
	

	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
				,"a1", "UA7T7B9jN2n7HnBT");

		// SQL文を準備する 
		String sql =" UPDATE News SET subject=?,text=?,is_display=? WHERE news_id=?";
		
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);

	
			pStmt.setString(1,news.getSubject ());
	
			pStmt.setString(2,news.getText());
		
			pStmt.setInt(3,news.getIsDisplay ());
		
			pStmt.setInt(4,news.getNewsId ());
			
		
	
	
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
	

//	削除メソッド
public boolean delete(News news) {
	Connection conn = null;
	boolean result = false;
	
	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
				,"a1", "UA7T7B9jN2n7HnBT");

		// SQL文を準備する
		String sql =" Delete FROM News WHERE  news_id=?";
		
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setInt(1,news.getNewsId());
		
			
	
		
	
	
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
public boolean changes(News news) {
	Connection conn = null;
	boolean result = false;
	
	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?useSSL= false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000"
				,"a1", "UA7T7B9jN2n7HnBT");

		// SQL文を準備する
		String sql =" update News  SET is_display=? WHERE  news_id=?";
		
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setInt(1,news.getIsDisplay());
		pStmt.setInt(2,news.getNewsId());
			
	
		
	
	
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
	


