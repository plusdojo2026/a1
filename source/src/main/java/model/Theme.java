package model;

import java.io.Serializable;

public class Theme implements Serializable {
private int themeId;
private String theme;
private int stampId;
private int diaryFlag;

public Theme() {};

public Theme(int themeId, String theme, int stampId, int diaryFlag) {
	super();
	this.themeId = themeId;
	this.theme = theme;
	this.stampId = stampId;
	this.diaryFlag = diaryFlag;
}


/**
 * @return themeId
 */
public int getThemeId() {
	return themeId;
}


/**
 * @param themeId セットする themeId
 */
public void setThemeId(int themeId) {
	this.themeId = themeId;
}


/**
 * @return theme
 */
public String getTheme() {
	return theme;
}


/**
 * @param theme セットする theme
 */
public void setTheme(String theme) {
	this.theme = theme;
}


/**
 * @return stampId
 */
public int getStampId() {
	return stampId;
}


/**
 * @param stampId セットする stampId
 */
public void setStampId(int stampId) {
	this.stampId = stampId;
}


/**
 * @return diaryflag
 */
public int getDiaryFlag() {
	return diaryFlag;
}


/**
 * @param diaryflag セットする diaryflag
 */
public void setDiaryFlag(int diaryflag) {
	this.diaryFlag = diaryflag;
}


}
