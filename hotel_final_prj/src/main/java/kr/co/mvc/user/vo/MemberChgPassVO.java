package kr.co.mvc.user.vo;

public class MemberChgPassVO {
	private String id, pass, change_pass, change_pass2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getChange_pass() {
		return change_pass;
	}

	public void setChange_pass(String change_pass) {
		this.change_pass = change_pass;
	}

	public String getChange_pass2() {
		return change_pass2;
	}

	public void setChange_pass2(String change_pass2) {
		this.change_pass2 = change_pass2;
	}

	@Override
	public String toString() {
		return "MemberChgPassVO [id=" + id + ", pass=" + pass + ", change_pass=" + change_pass + ", change_pass2="
				+ change_pass2 + "]";
	}

	
	

	
}//class
	

	

