package beans;

import java.util.Date;

public class Pc extends PcJson{
	protected Date lastRequestTime = null; //最終リクエスト時間

	public Date getLastRequestTime() {
		return lastRequestTime;
	}

	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}

}
