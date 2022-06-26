package servlet;

public class Person {
	protected String pcId;
	protected Boolean isStudent = null;
	
	Person(String pId, Boolean isS){
		this.pcId = pId;
		this.isStudent = isS;
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
