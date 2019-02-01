package de.ilovejava.enums;

public enum Enum_MessageEnum {
	No_Perm("§cPermission Denid!"),
	No_Args("§cCommand not Found!"),
	TDG_Exists("§cThis TDG is already Exists!"),
	TDG_Create("§aThe TDG was Created"),
	TDG_NOT_EXISTS("§cThis TDG is Not Exists"),
	TDG_SUC_CHANGE("§aThe TDG was Changed"),
	TDG_SUC_DELETE("§cThe TDG was delet");
	
	String msg;
	
	private Enum_MessageEnum(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return this.msg;
	}
}
