CREATE DATABASE a1;

USE a1;

CREATE TABLE users(user_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	   mail VARCHAR (254) NOT NULL UNIQUE,
			 	   name VARCHAR (50) NOT NULL,
			 	   pass VARCHAR (20) NOT NULL,
			 	   place VARCHAR (20) DEFAULT 'tokyo',
			 	   is_admin TINYINT DEFAULT 0
			 	   );
			 	  
CREATE TABLE diaries(diary_id INT AUTO_INCREMENT PRIMARY KEY,
					 user_id INT REFERENCES users(user_id),
					 date DATE,
					 weather_code INT,
			 	     temp_min FLOAT,
			 	     temp_max FLOAT,
			 	     theme_id INT REFERENCES themes(theme_id),
			 	     stamp_id INT REFERENCES stamps(stamp_id),
			 	     diary VARCHAR (300),
			 	     satisfaction INT,
			 	     image VARCHAR (2000),
			 	     UNIQUE (user_id,date)
			 	     );
			 	   
CREATE TABLE schedules(schedule_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	   	   user_id INT REFERENCES users(user_id),
			 	   	   date DATE NOT NULL,
			 	  	   schedule VARCHAR (40) NOT NULL,
			 	   	   color_id INT DEFAULT 1 REFERENCES colors(color_id)
			 	   	   );
			 	   
CREATE TABLE surveys(survey_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	     subject VARCHAR (50) NOT NULL,
			 	     text VARCHAR (400) NOT NULL,
			 	     filled_date DATE DEFAULT CURRENT_DATE
			 	     );
			 	   
CREATE TABLE advices(advice_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	     weak INT,
			 	     advice VARCHAR (100) NOT NULL
			 	     );
			 	   
CREATE TABLE news(news_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	  subject VARCHAR (30) NOT NULL,
			 	  text VARCHAR (400) NOT NULL,
			 	  is_display INT DEFAULT 0,
			 	  submitted_at DATE DEFAULT CURRENT_DATE
			 	  );
			 	   
CREATE TABLE users_inf(user_info_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	   	   user_id INT UNIQUE REFERENCES users(user_id),
			 	       streak INT DEFAULT 0,
			 	       sunny FLOAT,
			 	       cloudy FLOAT,
			 	       rainy FLOAT,
			 	       snowy FLOAT,
			 	       cold FLOAT,
			 	       ideal FLOAT,
			 	       hot FLOAT
			 	       );
			 	   
CREATE TABLE themes(theme_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	    theme VARCHAR (20),
			 	    stamp_id INT REFERENCES stamps(stamp_id),
			 	    diary_flag INT DEFAULT 0
			 	    );
			 	   
CREATE TABLE stamps(stamp_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	    stamp_path VARCHAR (2000) NOT NULL
			 	    );
			 	   
CREATE TABLE colors(color_id INT AUTO_INCREMENT PRIMARY KEY, 
			 	    color_code VARCHAR (10) NOT NULL
			 	    );