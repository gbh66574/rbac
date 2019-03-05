package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.SysrolemenuDao;
import com.entor.entity.Sysrolemenu;
import com.entor.util.DBUtils;
import com.entor.vo.Sysrolemenuvo;

public class SysrolemenuDaoimpl implements SysrolemenuDao {

	public void add(Sysrolemenu sysrolemenu) {
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_rolemenu(rid,mid) values(?,?)");
			pst.setInt(1, sysrolemenu.getRid());
			pst.setInt(2, sysrolemenu.getMid());
			System.out.println("成功增加"+pst.executeUpdate()+"条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Sysrolemenu> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_rolemenu(rid,mid) values(?,?)");
			for(int i=0;i<list.size();i++) {
				Sysrolemenu sysrolemenu = list.get(i);
				pst.setInt(1, sysrolemenu.getRid());
				pst.setInt(2, sysrolemenu.getMid());
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_rolemenu where id=?");
			pst.setInt(1, id);
			System.out.println("成功删除"+pst.executeUpdate()+"条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}


	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_rolemenu where id in ("+ids+")");
			System.out.println("成功删除"+pst.executeUpdate()+"条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}


	@Override
	public void update(Sysrolemenu sysrolemenu) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_rolemenu set rid=?,mid=? where id=?");
			pst.setInt(1, sysrolemenu.getRid());
			pst.setInt(2, sysrolemenu.getMid());
			System.out.println("成功更新"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Sysrolemenu queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Sysrolemenu sl = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_rolemenu where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				sl = new Sysrolemenu();
				sl.setRid(rs.getInt("rid"));
				sl.setMid(rs.getInt("mid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return sl;
	}

	@Override
	public List<Sysrolemenu> queryAll() {
		List<Sysrolemenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_rolemenu order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysrolemenu sl = new Sysrolemenu();
				sl.setId(rs.getInt("id"));
				sl.setRid(rs.getInt("rid"));
				sl.setMid(rs.getInt("mid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Sysrolemenu> queryByPage(int currentPage, int pageSize) {
		List<Sysrolemenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_rolemenu  order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysrolemenu sl = new Sysrolemenu();
				sl.setId(rs.getInt("id"));
				sl.setRid(rs.getInt("rid"));
				sl.setMid(rs.getInt("mid"));
				list.add(sl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}
	

	/*@Override
	public List<Sysrolemenu> queryByPage(int currentPage, int pageSize, String condition) {
		List<Sysrolemenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_rolemenu "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysrolemenu sl = new Sysrolemenu();
				sl.setId(rs.getInt("id"));
				sl.setRid(rs.getInt("rid"));
				sl.setMid(rs.getInt("mid"));
				list.add(sl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}
	*/


	@Override
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_rolemenu");
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_rolemenu "+condition);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}
	@Override
	public List<Sysrolemenuvo> srmqueryByPage(int currentPage, int pageSize) {
		List<Sysrolemenuvo> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_rolemenu,hr_sys_role,hr_sys_menu where hr_sys_rolemenu.rid=hr_sys_role.id and hr_sys_rolemenu.mid=hr_sys_menu.id order by hr_sys_rolemenu.id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysrolemenuvo sl = new Sysrolemenuvo();
				sl.setId(rs.getInt("hr_sys_rolemenu.id"));
				sl.setUsername(rs.getString("hr_sys_role.name"));
				sl.setName(rs.getString("hr_sys_menu.name"));
				list.add(sl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Sysrolemenu> queryByPage(int currentPage, int pageSize, String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
