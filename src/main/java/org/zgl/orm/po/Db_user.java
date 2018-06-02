package org.zgl.orm.po;

import java.sql.*;
import java.util.*;
public class Db_user {

	private String baseInfo;
	private String password;
	private String task;
	private String signIn;
	private String giftBag;
	private Integer id;
	private String weath;
	private String account;
	private String friends;

	public String getBaseInfo(){
		return baseInfo;
	}
	public void setBaseInfo(String baseInfo){
		this. baseInfo = baseInfo;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this. password = password;
	}
	public String getTask(){
		return task;
	}
	public void setTask(String task){
		this. task = task;
	}
	public String getSignIn(){
		return signIn;
	}
	public void setSignIn(String signIn){
		this. signIn = signIn;
	}
	public String getGiftBag(){
		return giftBag;
	}
	public void setGiftBag(String giftBag){
		this. giftBag = giftBag;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this. id = id;
	}
	public String getWeath(){
		return weath;
	}
	public void setWeath(String weath){
		this. weath = weath;
	}
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this. account = account;
	}
	public String getFriends(){
		return friends;
	}
	public void setFriends(String friends){
		this. friends = friends;
	}
}
