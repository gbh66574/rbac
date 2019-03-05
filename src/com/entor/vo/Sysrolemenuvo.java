package com.entor.vo;

public class Sysrolemenuvo {
	private int id;//菜单管理id
	private int rid;//角色id
	private int mid;//菜单id
	private String username;
	private String name;
	
	
	
	public Sysrolemenuvo() {
		super();
	}
	public Sysrolemenuvo(int id, int rid, int mid, String username, String name) {
		super();
		this.id = id;
		this.rid = rid;
		this.mid = mid;
		this.username = username;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Sysrolemenuvo [id=" + id + ", rid=" + rid + ", mid=" + mid + ", username=" + username + ", name=" + name
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
