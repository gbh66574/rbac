package com.entor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.entor.dao.SysuserroleDao;

import com.entor.dao.impl.SysuserroleDaoimpl;

import com.entor.entity.Sysuserrole;
import com.entor.util.Pager;
import com.entor.vo.Sysuserrolevo;

@Controller
public class Sysuserrolecontroller {
	private HttpServletResponse response;
	private HttpServletRequest request;

	@RequestMapping("/sysuserroleadd")
	public void add(Sysuserrole s) throws IOException {
		SysuserroleDao dao = new SysuserroleDaoimpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
		dao.add(s);
		jo.put("state", 0);
		jo.put("msg", "鎴愬姛澧炲姞璁板綍");
		} catch (Exception e) {
		jo.put("state", -1);
		jo.put("msg", "澧炲姞璁板綍澶辫触");
		} finally {
		String str = JSON.toJSONString(jo);
		System.out.println(str);
		out.write(str);
		out.flush();
		out.close();
		}

	}

	@RequestMapping("/sysuserroledeleteById")
	public String deleteById(int id, String currentPage, Map<String, Object> map) {
		SysuserroleDao dao = new SysuserroleDaoimpl();
		dao.deleteById(id);
		map.put("currentPage", currentPage);

		return "forward:/sysqueryByPage";
	}

	@RequestMapping("/sysuserroledeleteMore")
	public void deleteMore(String ids) throws IOException {
		SysuserroleDao dao = new SysuserroleDaoimpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "鍒犻櫎鎴愬姛");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "鍒犻櫎澶辫触");
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}

		
	}

	@RequestMapping("/sysuserroleupdate")
	public void update(Sysuserrole s) throws IOException {
		SysuserroleDao dao = new SysuserroleDaoimpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		
		try {
			dao.update(s);
			jo.put("state", 0);
			jo.put("msg", "淇敼鎴愬姛");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "淇敼澶辫触");
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}

		
	}

	@RequestMapping("/sysuserrolequeryById")
	public Sysuserrole queryById(int id, String currentPage, Map<String, Object> map) {
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		SysuserroleDao dao = new SysuserroleDaoimpl();
		Sysuserrole sysuserrole = dao.queryById(id);
		map.put("sysuserrole", sysuserrole);
		map.put("currentPage", currentPage);
		map.put("qname", qname);
		map.put("qusername", qusername);
		map.put("qsex", qsex);
		return sysuserrole;
	}

	@RequestMapping("/sysuserrolequeryByPage")
	@ResponseBody
	public String queryByPage( Map<String, Object> map) {
		SysuserroleDao dao = new SysuserroleDaoimpl();
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");
		String currentPage = request.getParameter("page");
		String rows = request.getParameter("rows");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%'";
		}
		if (qusername != null && !qusername.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and username like '%" + qusername + "%'";
		}
		if (qsex != null && !qsex.equals("") && !qsex.equals("-1") && !qname.equalsIgnoreCase("null")) {
			condition += " and sex = " + qsex;
		}
		// 瑜版挸澧犳い锟�
		int sp = 1;
		// 閹槒顔囪ぐ鏇熸殶
		int totals = dao.getTotals(condition);
		// 濮ｅ繘銆夌拋鏉跨秿閺侊拷
		int pageSize = Integer.parseInt(rows);
		// 閹銆夐弫锟�
		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(currentPage);
		} catch (Exception e) {
			sp = 1;
		}
		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}

		List<Sysuserrolevo> list = dao.sysuserrolequeryByPage(sp, pageSize);
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", totals);
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "list";
	}

	@ModelAttribute
	public void initDate(HttpServletResponse response, HttpServletRequest request) {
		System.out.println("鍒濆鍖栧弬鏁�");

		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}

	// 澶勭悊鍙傛暟鏃ユ湡鏍煎紡
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
