package beans;

public class Pc {
	
	protected String pcId = null; //icsXXX
	protected String ipAdress = null; //ドメインは133.44.118.158-228の間
	protected Boolean isStudent = null; // true:学生が利用するPC, false:TA,教員が利用するPC
	protected Boolean isLogin = null; // true:ログイン中, false:未ログイン
	protected Boolean handStatus = null; // true:挙手をしている状態, false:挙手をしていない状態
	protected Boolean helpStatus = null; // true:TA教員ヘルプ中, false:ヘルプしていない (*この変数はまだ利用していない)
	
	//--------アクセッサ関係-------------------------------------------------
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
