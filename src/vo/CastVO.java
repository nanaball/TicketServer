package vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class CastVO {
	
	private int id;
	private String date;
	private String musicalNa;
	private String time;
	private String casting;
	
	public CastVO() {
		super();
	}

	public CastVO(int id, String date, String musicalNa, String time, String casting) {
		super();
		this.id = id;
		this.date = date;
		this.musicalNa = musicalNa;
		this.time = time;
		this.casting = casting;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMusicalNa() {
		return musicalNa;
	}

	public void setMusicalNa(String musicalNa) {
		this.musicalNa = musicalNa;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	@Override
	public String toString() {
		return "CastVO [id=" + id + ",  date=" + date + ", musicalNa=" + musicalNa + ", time=" + time + ", casting="
				+ casting + "]";
	}

	



}