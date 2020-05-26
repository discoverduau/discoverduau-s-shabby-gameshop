package javabin;

public class wishlist {
	private String gamename;
	private int price;
	private String producer;
	private float presentage;
	private String adddate;
	
	public wishlist() {
		this.gamename = gamename;
		this.price = price;
		this.producer = producer;
		this.presentage = presentage;
		this.adddate = adddate;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public float getPresentage() {
		return presentage;
	}

	public void setPresentage(float presentage) {
		this.presentage = presentage;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	
	
}
