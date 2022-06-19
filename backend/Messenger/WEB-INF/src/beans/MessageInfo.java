package beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageInfo {
	// 属性

    @JsonProperty("RID")
	private int RID;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("message")
	private String message;

    @JsonProperty("year")
	private int year;

    @JsonProperty("month")
	private int month;

    @JsonProperty("day")
    private int day;

    @JsonProperty("hour")
    private int hour;

    @JsonProperty("minute")
    private int minute;

    @JsonProperty("second")
	private int second;

	//アクセッサ
	public int getRID() {
		return RID;
	}
	public void setRID(int rID) {
		RID = rID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}


}
