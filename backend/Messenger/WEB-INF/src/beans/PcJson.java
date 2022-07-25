package beans;

public class PcJson{

	protected String pcId = null; //icsXXX
	protected String ipAdress = null; //ドメインは133.44.118.158-228の間
	protected Boolean isStudent = null; // true:学生が利用するPC, false:TA,教員が利用するPC
	protected Boolean isLogin = null; // true:ログイン中, false:未ログイン
	protected String helpStatus = null; // 手を挙げていない: None
											//	手を挙げている: Troubled
											// TA教員対応中: Supporting
	protected int handOrder = -1;
	//--------アクセッサ関係-------------------------------------------------
	public int getHandOrder() {
		return handOrder;
	}
	public void setHandOrder(int handOrder) {
		this.handOrder = handOrder;
	}
	public String getHelpStatus() {
		return helpStatus;
	}
	public void setHelpStatus(String helpStatus) {
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
