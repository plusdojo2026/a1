CREATE DATABASE a1;

USE a1;

CREATE TABLE users(user_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	   mail VARCHAR (254) NOT NULL UNIQUE,
			 	   name VARCHAR (50) NOT NULL,
			 	   pass VARCHAR (20) NOT NULL,
			 	   place VARCHAR (20) DEFAULT 'tokyo',
			 	   is_admin TINYINT DEFAULT 0
			 	   );
			 	  
CREATE TABLE stamps(stamp_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	    stamp_path VARCHAR (2000) NOT NULL
			 	    );
			 	    
CREATE TABLE themes(theme_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	    theme VARCHAR (20),
			 	    stamp_id INT,
			 	    diary_flag INT DEFAULT 0,
			 	    FOREIGN KEY(stamp_id) REFERENCES stamps(stamp_id)
			 	    );
			 	    
CREATE TABLE diaries(diary_id INT AUTO_INCREMENT PRIMARY KEY,
					 user_id INT,
					 date DATE,
					 weather_code INT,
			 	     temp_min FLOAT,
			 	     temp_max FLOAT,
			 	     theme_id INT REFERENCES themes(theme_id),
			 	     stamp_id INT REFERENCES stamps(stamp_id),
			 	     diary VARCHAR (300),
			 	     satisfaction INT,
			 	     image VARCHAR (2000),
			 	     UNIQUE (user_id,date),
			 	     FOREIGN KEY(user_id) REFERENCES users(user_id),
			 	     FOREIGN KEY(theme_id) REFERENCES themes(theme_id),
			 	     FOREIGN KEY(stamp_id) REFERENCES stamps(stamp_id)
			 	     );
			 	   
CREATE TABLE schedules(schedule_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	   	   user_id INT REFERENCES users(user_id),
			 	   	   date DATE NOT NULL,
			 	  	   schedule VARCHAR (40) NOT NULL,
			 	   	   color_id INT DEFAULT 1 ,
			 	   	   FOREIGN KEY(user_id) REFERENCES users(user_id)
			 	   	   );
			 	   
CREATE TABLE surveys(survey_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	     subject VARCHAR (50) NOT NULL,
			 	     text VARCHAR (400) NOT NULL,
			 	     filled_date DATE DEFAULT (CURRENT_DATE)
			 	     );
			 	   
CREATE TABLE news(news_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	  subject VARCHAR (30) NOT NULL,
			 	  text VARCHAR (400) NOT NULL,
			 	  is_display INT DEFAULT 0,
			 	  submitted_at DATE DEFAULT (CURRENT_DATE)
			 	  );
			 	   

--ここからINSERT文

--サトシの管理者情報登録文
INSERT INTO users(mail,name,pass,is_admin) VALUES('satoshi@sample.com','真田サトシ','satoshi','1');
--ヒカリののユーザー情報登録文
INSERT INTO users(mail,name,pass) VALUES('hikari@sample.com','相澤ヒカリ','hikari');

--スタンプ
INSERT INTO stamps(stamp_path) VALUES('flower.png');
INSERT INTO stamps(stamp_path) VALUES('cycling.png');
INSERT INTO stamps(stamp_path) VALUES('weather.png');
INSERT INTO stamps(stamp_path) VALUES('meal.png');
INSERT INTO stamps(stamp_path) VALUES('live.png');
INSERT INTO stamps(stamp_path) VALUES('book.png');
INSERT INTO stamps(stamp_path) VALUES('friend.png');
--テーマ
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('お花',1,0);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('自転車',2,1);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('天気・空',3,0);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('食事',4,0);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('ライブ',5,1);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('本',6,0);
INSERT INTO themes(theme,stamp_id,diary_flag) VALUES('身近な人',7,0);
--ここから日記

--発表日一年前のヒカリの日記
INSERT INTO diaries(user_id, date, weather_code, temp_min, temp_max, theme_id, stamp_id, diary, satisfaction, image)
	     VALUES(2, '2025-06-30',0, 18.1, 27.4, 1, 1,'今日はコスモスを見ました。新しい発見がありました。',4,'flower.png');
	     
--ヒカリの日記
INSERT INTO diaries(user_id, date, weather_code, temp_min, temp_max, theme_id, stamp_id, diary, satisfaction, image)
	     VALUES(2, '2026-06-26',0, 18.1, 26.4, 3, 3,'今日は羊雲を見ました。アプリを通して新しいものを調べるきっかけになりました。',5,'cloudy.png');
--スケジュール
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-12','外食');
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-24','日帰り旅');
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-30','美術館');
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-30','図書館');
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-30','博物館');
INSERT INTO schedules(user_id,date,schedule) VALUES(2,'2026-06-30','サイクリング');
--ご意見
INSERT INTO surveys(subject,text,filled_date) VALUES('テーマについて','テーマの種類を増やしてほしいです','2026-06-22');
--お知らせ
INSERT INTO news(subject,text,is_display,submitted_at) VALUES('テーマについて','テーマの種類を増やしました。',0,'2026-06-24');