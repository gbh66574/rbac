package com.entor.entity;

public class Sysrolemenu {
	private int id;//菜单管理id
	private int rid;//角色id
	private int mid;//菜单id
	public Sysrolemenu() {
		super();
	}
	public Sysrolemenu(int id, int rid, int mid) {
		super();
		this.id = id;
		this.rid = rid;
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "Sysrolemenu [id=" + id + ", rid=" + rid + ", mid=" + mid + "]";
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
}
