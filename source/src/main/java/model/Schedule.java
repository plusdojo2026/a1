package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Schedule implements Serializable {
	private int scheduleId;
	private int userId;
	private LocalDate date;
	private String schedule;
	private String colorName;
	
	public Schedule(int scheduleId, int userId, LocalDate date, String schedule, String colorName) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.date = date;
		this.schedule = schedule;
		this.colorName = colorName;
	}

	public int getScheduleId() {
		return scheduleId;
	}
	
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorId(String colorName) {
		this.colorName = colorName;
	}
	
	
}
