package com.entor.dao;

import java.util.List;

import com.entor.entity.Sysuserrole;
import com.entor.vo.Sysuserrolevo;



public interface SysuserroleDao {
public void add(Sysuserrole sysuserrole);
	
	public void addMore(List<Sysuserrole> list);
	
	public void deleteById(int id);
	
	public void deleteMore(String ids);
	
	public void update(Sysuserrole sysuserrole);
	
	public Sysuserrole queryById(int id);
	
	public List<Sysuserrole> queryAll();
	
	public List<Sysuserrole> queryByPage(int currentPage,int pageSize);
	public List<Sysuserrolevo> sysuserrolequeryByPage(int currentPage,int pageSize);
	
	public List<Sysuserrole> queryByPage(int currentPage,int pageSize,String condition);
	
	public int getTotals();
	
	public int getTotals(String condition);
}


