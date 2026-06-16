package model;

import java.io.Serializable;

public class Stamp implements Serializable {
	private String stampPath;//スタンプ
	private int stampId;
	
	
	public Stamp(int stampId, String stampPath) {
		super();
		this.stampId = stampId;
		this.stampPath = stampPath;
	}

	//ゲッタとセッタ
	public String getStampPath() {
		return stampPath;
	}

	public void setStampPath(String stampPath) {
		this.stampPath = stampPath;
	}

	public int getStampId() {
		return stampId;
	}

	public void setStampId(int stampId) {
		this.stampId = stampId;
	}

}
