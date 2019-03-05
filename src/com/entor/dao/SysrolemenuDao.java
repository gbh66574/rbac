package com.entor.dao;

import java.util.List;

import com.entor.entity.Sysrolemenu;
import com.entor.vo.Sysrolemenuvo;



public interface SysrolemenuDao {
public void add(Sysrolemenu sysrolemenu);
	
	public void addMore(List<Sysrolemenu> list);
	
	public void deleteById(int id);
	
	public void deleteMore(String ids);
	
	public void update(Sysrolemenu sysrolemenu);
	
	public Sysrolemenu queryById(int id);
	
	public List<Sysrolemenu> queryAll();
	
	public List<Sysrolemenu> queryByPage(int currentPage,int pageSize);
	public List<Sysrolemenuvo> srmqueryByPage(int currentPage,int pageSize);
	
	public List<Sysrolemenu> queryByPage(int currentPage,int pageSize,String condition);
	
	public int getTotals();
	
	public int getTotals(String condition);
}


