package model;

import java.io.Serializable;
import java.time.LocalDate;

public class News implements Serializable {
private int newsId;
private String subject;
private String text;
private int isDisplay;
private LocalDate submittedAt;


public News() {};

public News(int newsId,String subject,String text,int isDisplay,LocalDate submittedAt) {
	super();
	this.newsId=newsId;
	this.subject=subject;
	this.text=text;
	this.isDisplay=isDisplay;
	this.submittedAt=submittedAt;
}

/**
 * @return newsId
 */
public int getNewsId() {
	return newsId;
}

/**
 * @param newsId セットする newsId
 */
public void setNewsId(int newsId) {
	this.newsId = newsId;
}

/**
 * @return subject
 */
public String getSubject() {
	return subject;
}

/**
 * @param subject セットする subject
 */
public void setSubject(String subject) {
	this.subject = subject;
}

/**
 * @return text
 */
public String getText() {
	return text;
}

/**
 * @param text セットする text
 */
public void setText(String text) {
	this.text = text;
}

/**
 * @return isDisplay
 */
public int getIsDisplay() {
	return isDisplay;
}

/**
 * @param isDisplay セットする isDisplay
 */
public void setIsDisplay(int isDisplay) {
	this.isDisplay = isDisplay;
}

/**
 * @return submittedAt
 */
public LocalDate getSubmittedAt() {
	return submittedAt;
}

/**
 * @param submittedAt セットする submittedAt
 */
public void setSubmittedAt(LocalDate submittedAt) {
	this.submittedAt = submittedAt;
}

}
