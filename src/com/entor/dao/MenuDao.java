package com.entor.dao;

import java.util.List;

import com.entor.entity.Menu;
import com.entor.entity.Role;

public interface MenuDao {

	/**
	 * 鏂板
	 * @param student
	 */
	public void add(Menu menu);
	/**
	 * 鎵归噺鏂板
	 * @param list
	 */
	public void addMore(List<Menu> list);
	/**
	 * 鏍规嵁涓婚敭鍒犻櫎
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 鏍规嵁澶氫釜涓婚敭鎵归噺鍒犻櫎
	 * @param ids
	 */
	public void deleteMore(String ids);
	/**
	 * 鏇存柊
	 * @param student
	 */
	public void update(Menu menu);
	/**
	 * 鏍规嵁涓婚敭鏌ヨ璁板綍
	 * @param id
	 * @return
	 */
	public Menu queryById(int id);
	/**
	 * 鏌ヨ鎵�鏈夎褰�
	 * @return
	 */
	public List<Menu> queryAll();
	/**
	 * 鍒嗛〉鏌ヨ璁板綍
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Menu> queryByPage(int currentPage,int pageSize);
	/**
	 * 鏉′欢鍒嗛〉鏌ヨ璁板綍
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<Menu> queryByPage(int currentPage,int pageSize,String condition);
	/**
	 * 鏌ヨ鎬昏褰曟暟
	 * @return
	 */
	public int getTotals();
	/**
	 * 鏍规嵁鏉′欢鏌ヨ鎬昏褰曟暟
	 * @param condition
	 * @return
	 */
	public int getTotals(String condition);
	public List<Menu> queryAllMenusByrids(String rids);
	public List<Menu> queryAllMenusByUsername(String username);
}
