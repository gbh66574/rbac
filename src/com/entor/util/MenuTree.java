package com.entor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.entor.entity.Menu;

/**
 * 此类用来生成easyui格式的树json字符串
 * @author Administrator
 *
 */
public class MenuTree {

	private List<Menu> list;
	
	/**
	 * 获取所有根节点
	 * @return
	 */
	public List<Map<String, Object>> createMenuTree(){
		List<Map<String, Object>> rootList = new ArrayList<>();
		for(Menu menu:list) {
			Map<String, Object> map = null;
			if(menu.getPcode()==null) {//说明是根节点
				map = new HashMap<String, Object>();
				map.put("id", menu.getCode());
				map.put("text", menu.getName());
				map.put("children", createSubMenuTree(menu.getCode()));
				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("url", menu.getUrl());
				attrMap.put("state", menu.getState());
				map.put("attributes", attrMap);
			}
			if(map!=null) {
				rootList.add(map);
			}
		}
		return rootList;
	}
	/**
	 * 递归获取父节点下的所有子节点
	 * @param pcode
	 * @return
	 */
	public List<Map<String, Object>> createSubMenuTree(String pcode){
		List<Map<String, Object>> leafList = new ArrayList<>();
		for(Menu menu:list) {
			Map<String, Object> map = null;
			if(menu.getPcode()!=null&&menu.getPcode().equals(pcode)) {//子节点
				map = new HashMap<String, Object>();
				map.put("id", menu.getCode());
				map.put("text", menu.getName());
				map.put("children", createSubMenuTree(menu.getCode()));
				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("url", menu.getUrl());
				attrMap.put("state", menu.getState());
				map.put("attributes", attrMap);
			}
			if(map!=null) {
				leafList.add(map);
			}
		}
		return leafList;
	}
	/**
	 * 返回树json格式字符串
	 * @return
	 */
	public String getMenuTreeJson() {
		return JSONArray.toJSONString(createMenuTree());
	}

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}
}
