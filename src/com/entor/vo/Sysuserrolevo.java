package com.entor.vo;

public class Sysuserrolevo {
	private int id;
	private int uid;
	private int rid;
	private String name;
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Sysuserrolevo [id=" + id + ", uid=" + uid + ", rid=" + rid + ", name=" + name + ", username=" + username
				+ "]";
	}
	public Sysuserrolevo(int id, int uid, int rid, String name, String username) {
		super();
		this.id = id;
		this.uid = uid;
		this.rid = rid;
		this.name = name;
		this.username = username;
	}
	public Sysuserrolevo() {
		super();
	}
	
	
}
