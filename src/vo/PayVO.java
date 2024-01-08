package vo;

public class PayVO {
	private String user;
	private String price;
	private String date;
	private String musical;
	private String seat;
	private String name;
	
	public PayVO(String price, String date, String musical, String seat, String name) {
		super();
		this.price = price;
		this.date = date;
		this.musical = musical;
		this.seat = seat;
		this.name = name;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getMusical() {
		return musical;
	}


	public void setMusical(String musical) {
		this.musical = musical;
	}


	public String getSeat() {
		return seat;
	}


	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "PayVO [user=" + user + ", price=" + price + ", date=" + date + ", musical=" + musical + ", seat=" + seat
				+ ", name=" + name + "]";
	}


	
	
	
}
