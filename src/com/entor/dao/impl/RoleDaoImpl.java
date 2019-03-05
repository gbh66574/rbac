package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.RoleDao;
import com.entor.entity.Role;
import com.entor.util.DBUtils;

public class RoleDaoImpl implements RoleDao{

	@Override
	public void add(Role role) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_role(name,remark) values(?,?)");
			pst.setString(1, role.getName());
			pst.setString(2, role.getRemark());
			System.out.println("鎴愬姛鏂板銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Role> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_role(name,remark) values(?,?)");
			for(int i=0;i<list.size();i++) {
				Role role = list.get(i);
				pst.setString(1, role.getName());
				pst.setString(2, role.getRemark());
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
			pst = con.prepareStatement("delete from hr_sys_role where id=?");
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
			pst = con.prepareStatement("delete from hr_sys_role where id in ("+ids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(Role role) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_role set name=?,remark=? where id=?");
			pst.setString(1, role.getName());
			pst.setString(2, role.getRemark());
			pst.setInt(3, role.getId());
			System.out.println("鎴愬姛鏇存柊銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Role queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Role s = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_role where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new Role();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setRemark(rs.getString("remark"));
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
	public List<Role> queryAll() {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_role order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Role s = new Role();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setRemark(rs.getString("remark"));
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
	public List<Role> queryByPage(int currentPage, int pageSize) {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_role order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Role s = new Role();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setRemark(rs.getString("remark"));
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
	public List<Role> queryByPage(int currentPage, int pageSize, String condition) {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_role "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Role s = new Role();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setRemark(rs.getString("remark"));
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
			pst = con.prepareStatement("select count(*) from hr_sys_role");
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
			pst = con.prepareStatement("select count(*) from hr_sys_role "+condition);
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
	public List<Role> queryAllRolesByUids(String uids) {
		List<Role> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select r.* from hr_sys_user u,hr_sys_role r,hr_sys_userrole ur where u.id=ur.uid and r.id=ur.rid and u.id in ("+uids+") order by r.id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Role s = new Role();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setRemark(rs.getString("remark"));
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
}
