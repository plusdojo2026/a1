package model;

import java.io.Serializable;
import java.time.LocalDate;

public class DiaryView implements Serializable {

	private int diaryId;
	private int userId;
	private LocalDate date;
	private int weatherCode;
	private float tempMin;
	private float tempMax;
	private String theme; //テーマIDをテーマ名に変換して格納する。
	private String stampPath; //スタンプIDをスタンプのパスに変換して格納する。
	private String diary;
	private int satisfaction;
	private String image;
	
	public DiaryView() {
		
	}

	public DiaryView(int diaryId, int userId, LocalDate date, int weatherCode, float tempMin, float tempMax,
			String theme, String stampPath, String diary, int satisfaction, String image) {
		super();
		this.diaryId = diaryId;
		this.userId = userId;
		this.date = date;
		this.weatherCode = weatherCode;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.theme = theme;
		this.stampPath = stampPath;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStampPath() {
		return stampPath;
	}

	public void setStampPath(String stampPath) {
		this.stampPath = stampPath;
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
	};
	
	
	
}
