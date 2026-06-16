package model;

import java.io.Serializable;

public class Stamp implements Serializable {
	private String stampPath;//スタンプ
	private int stampId;
	
	
	public Stamp(String stampPath, int stampId) {
		super();
		this.stampPath = stampPath;
		this.stampId = stampId;
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
