package javabin;

public class users {
	private String id;
	private String password;
	private String sex;
	private String country;
	private String birthday;
	private float wallet;
	private String userimage;
	
	public users() {
		this.id = id;
		this.password = password;
		this.sex = sex;
		this.country = country;
		this.birthday = birthday;
		this.wallet = wallet;
		this.userimage = userimage;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public float getWallet() {
		return wallet;
	}
	public void setWallet(float wallet) {
		this.wallet = wallet;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	
}
