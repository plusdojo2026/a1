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
INSERT INTO users(mail,name,pass,is_admin) VALUES('satoshi@sample.com','真田サトシ','satoshi','1')
--ヒカリののユーザー情報登録文
INSERT INTO users(mail,name,pass) VALUES('hikari@sample.com','相澤ヒカリ','hikari')

--ここから日記

--発表日一年前のヒカリの日記
INSERT INTO 

--スタンプ
--テーマ
--スケジュール
--ご意見
--お知らせ