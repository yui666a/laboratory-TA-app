package beans;

import java.util.Date;

public class Pc extends PcJson{
	protected Date lastRequestTime = null; //最終リクエスト時間
	protected Date lastHandTime = null; //最終挙手時間

	public Date getLastHandTime() {
		return lastHandTime;
	}

	public void setLastHandTime(Date lastHandTime) {
		this.lastHandTime = lastHandTime;
	}

	public Date getLastRequestTime() {
		return lastRequestTime;
	}

	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}

}
