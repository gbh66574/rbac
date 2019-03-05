package com.entor.dao;

import java.util.List;

import com.entor.entity.UserRole;

public interface UserRoleDao {

	/**
	 * 鏂板
	 * @param student
	 */
	public void add(UserRole userRole);
	/**
	 * 鎵归噺鏂板
	 * @param list
	 */
	public void addMore(List<UserRole> list);
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
	public void update(UserRole userRole);
	/**
	 * 鏍规嵁涓婚敭鏌ヨ璁板綍
	 * @param id
	 * @return
	 */
	public UserRole queryById(int id);
	/**
	 * 鏌ヨ鎵�鏈夎褰�
	 * @return
	 */
	public List<UserRole> queryAll();
	/**
	 * 鍒嗛〉鏌ヨ璁板綍
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<UserRole> queryByPage(int currentPage,int pageSize);
	/**
	 * 鏉′欢鍒嗛〉鏌ヨ璁板綍
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<UserRole> queryByPage(int currentPage,int pageSize,String condition);
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
	public void deleteUserRolesByUids(String uids);
}
