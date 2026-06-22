package model;

import java.io.Serializable;

public class UserInf implements Serializable {

	private int userInfId;
	private int userId;
	private int steak;
	private float sunny;
	private float cloudy;
	private float rainy;
	private float snowy;
	private float cold;
	private float ideal;
	private float hot;
	
	public UserInf(int userInfId, int userId, int steak, float sunny, float cloudy, float rainy, float snowy,
			float cold, float ideal, float hot) {
		super();
		this.userInfId = userInfId;
		this.userId = userId;
		this.steak = steak;
		this.sunny = sunny;
		this.cloudy = cloudy;
		this.rainy = rainy;
		this.snowy = snowy;
		this.cold = cold;
		this.ideal = ideal;
		this.hot = hot;
	}

	public int getUserInfId() {
		return userInfId;
	}

	public void setUserInfId(int userInfId) {
		this.userInfId = userInfId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSteak() {
		return steak;
	}

	public void setSteak(int steak) {
		this.steak = steak;
	}

	public float getSunny() {
		return sunny;
	}

	public void setSunny(float sunny) {
		this.sunny = sunny;
	}

	public float getCloudy() {
		return cloudy;
	}

	public void setCloudy(float cloudy) {
		this.cloudy = cloudy;
	}

	public float getRainy() {
		return rainy;
	}

	public void setRainy(float rainy) {
		this.rainy = rainy;
	}

	public float getSnowy() {
		return snowy;
	}

	public void setSnowy(float snowy) {
		this.snowy = snowy;
	}

	public float getCold() {
		return cold;
	}

	public void setCold(float cold) {
		this.cold = cold;
	}

	public float getIdeal() {
		return ideal;
	}

	public void setIdeal(float ideal) {
		this.ideal = ideal;
	}

	public float getHot() {
		return hot;
	}

	public void setHot(float hot) {
		this.hot = hot;
	}
	
	
}
