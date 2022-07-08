package beans;

public class Pc {
	protected String pcId = null;
	protected String ipAdress = null;
	protected Boolean isStudent = null;
	protected Boolean isLogin = null;
	protected Boolean handStatus = null;
	protected Boolean helpStatus = null;
	
	public Boolean getHandStatus() {
		return handStatus;
	}
	public void setHandStatus(Boolean handStatus) {
		this.handStatus = handStatus;
	}	public Boolean getHelpStatus() {
		return helpStatus;
	}
	public void setHelpStatus(Boolean helpStatus) {
		this.helpStatus = helpStatus;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public Boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
}
