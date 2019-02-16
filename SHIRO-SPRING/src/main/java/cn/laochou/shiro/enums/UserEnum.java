package cn.laochou.shiro.enums;

public enum UserEnum {
	
	EXISTS("EXISTS"),NONEXISTS("NONEXISTS");
	
	private String status;
	
	private UserEnum(String status) {
		this.status = status;
	}

	
	public String getStatus() {
		return this.status;
	}
}
