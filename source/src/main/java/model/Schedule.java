package model;

import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable {
	private int scheduleId;
	private int userId;
	private Date date;
	private String schedule;
	private String colorId;
	
	public Schedule(int scheduleId, int userId, Date date, String schedule, String colorId) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.date = date;
		this.schedule = schedule;
		this.colorId = colorId;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	
	
}
