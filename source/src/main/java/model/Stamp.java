package model;

import java.io.Serializable;

public class Stamp implements Serializable {
	private String stamp;//スタンプ

	public Stamp(String stamp) {
		super();
		this.stamp = stamp;
	}

	//ゲッタとセッタ
	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
}
