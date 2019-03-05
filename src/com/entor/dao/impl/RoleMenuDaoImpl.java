package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.RoleMenuDao;
import com.entor.entity.RoleMenu;
import com.entor.util.DBUtils;

public class RoleMenuDaoImpl implements RoleMenuDao{

	@Override
	public void add(RoleMenu roleMenu) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_roleMenu(rid,mid) values(?,?)");
			pst.setInt(1, roleMenu.getRid());
			pst.setInt(2, roleMenu.getMid());
			System.out.println("鎴愬姛鏂板銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<RoleMenu> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_roleMenu(rid,mid) values(?,?)");
			for(int i=0;i<list.size();i++) {
				RoleMenu roleMenu = list.get(i);
				pst.setInt(1, roleMenu.getRid());
				pst.setInt(2, roleMenu.getMid());
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
			pst = con.prepareStatement("delete from hr_sys_roleMenu where id=?");
			pst.setInt(1, id);
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
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
			pst = con.prepareStatement("delete from hr_sys_roleMenu where id in ("+ids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(RoleMenu roleMenu) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_roleMenu set rid=?,mid=? where id=?");
			pst.setInt(1, roleMenu.getRid());
			pst.setInt(2, roleMenu.getMid());
			pst.setInt(3, roleMenu.getId());
			System.out.println("鎴愬姛鏇存柊銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public RoleMenu queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		RoleMenu s = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_roleMenu where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new RoleMenu();
				s.setId(rs.getInt("id"));
				s.setRid(rs.getInt("rid"));
				s.setMid(rs.getInt("mid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return s;
	}

	@Override
	public List<RoleMenu> queryAll() {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_roleMenu order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				RoleMenu s = new RoleMenu();
				s.setId(rs.getInt("id"));
				s.setRid(rs.getInt("rid"));
				s.setMid(rs.getInt("mid"));
				list.add(s);
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
	public List<RoleMenu> queryByPage(int currentPage, int pageSize) {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_roleMenu order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				RoleMenu s = new RoleMenu();
				s.setId(rs.getInt("id"));
				s.setRid(rs.getInt("rid"));
				s.setMid(rs.getInt("mid"));
				list.add(s);
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
	public List<RoleMenu> queryByPage(int currentPage, int pageSize, String condition) {
		List<RoleMenu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_roleMenu "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				RoleMenu s = new RoleMenu();
				s.setId(rs.getInt("id"));
				s.setRid(rs.getInt("rid"));
				s.setMid(rs.getInt("mid"));
				list.add(s);
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
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_roleMenu");
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
			pst = con.prepareStatement("select count(*) from hr_sys_roleMenu "+condition);
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
	public void deleteRoleMenusByRids(String rids) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_rolemenu where rid in ("+rids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}}

}
