package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Survey implements Serializable {
	private int surveyId;
	private String subject;
	private String text;
	private LocalDate filledDate;
	
	// コンストラクタ
	public Survey() {
		
	};
	
	public Survey(int surveyId, String subject, String text, LocalDate filledDate) {
		this.surveyId = surveyId;
		this.subject = subject;
		this.text = text;
		this.filledDate = filledDate;
	}

	// 各フィールドのgetterとsetterのメソッド
	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getFilledDate() {
		return filledDate;
	}

	public void setFilledDate(LocalDate filledDate) {
		this.filledDate = filledDate;
	}
	
	
	
}
