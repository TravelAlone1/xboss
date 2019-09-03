package com.lx.app.model;

import lombok.Data;
import java.util.Date;
@Data
public class User extends BaseObject {
	private int id;
	private String userName;
	private String password;
	private String realName;
	private String salt;
	private String  business;
	private String email;
	private String headPicture;
	private Date addDate;
	private Date updateDate;
	private int state;

	private int isSystem;

	public String getCredentialsSalt(){
		return this.userName+this.salt;
	}
}
