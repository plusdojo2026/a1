package model;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable {

	private int diaryId;
	private int userId;
	private Date date;
	private int weatherCode;
	private float tempMin;
	private float tempMax;
	private int themeId; //テーマIDをテーマ名に変換して格納する。
	private int stampId; //スタンプIDをスタンプのパスに変換して格納する。
	private String diary;
	private int satisfaction;
	private String image;
	
	public Diary() {
		
	};
	
	public Diary(int diaryId, int userId, Date date, int weatherCode, float tempMin, float tempMax, int theme,
			int stamp, String diary, int satisfaction, String image) {
		super();
		this.diaryId = diaryId;
		this.userId = userId;
		this.date = date;
		this.weatherCode = weatherCode;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.themeId = theme;
		this.stampId = stamp;
		this.diary = diary;
		this.satisfaction = satisfaction;
		this.image = image;
	}


	public int getDiaryId() {
		return diaryId;
	}


	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getWeatherCode() {
		return weatherCode;
	}


	public void setWeatherCode(int weatherCode) {
		this.weatherCode = weatherCode;
	}


	public float getTempMin() {
		return tempMin;
	}


	public void setTempMin(float tempMin) {
		this.tempMin = tempMin;
	}


	public float getTempMax() {
		return tempMax;
	}


	public void setTempMax(float tempMax) {
		this.tempMax = tempMax;
	}


	public int getThemeId() {
		return themeId;
	}


	public void setThemeId(int theme) {
		this.themeId = theme;
	}


	public int getStampId() {
		return stampId;
	}


	public void setStampId(int stamp) {
		this.stampId = stamp;
	}


	public String getDiary() {
		return diary;
	}


	public void setDiary(String diary) {
		this.diary = diary;
	}


	public int getSatisfaction() {
		return satisfaction;
	}


	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
}
