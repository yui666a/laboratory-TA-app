package beans;

public class Person {
	protected String pcId;
	protected Boolean isStudent = null;
	
	public Person(String pcId, Boolean isStudent){
		this.pcId = pcId;
		this.isStudent = isStudent;
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
