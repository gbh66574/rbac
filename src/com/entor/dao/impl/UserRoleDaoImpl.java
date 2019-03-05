package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.UserRoleDao;
import com.entor.entity.UserRole;
import com.entor.util.DBUtils;

public class UserRoleDaoImpl implements UserRoleDao{

	@Override
	public void add(UserRole userRole) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_userRole(uid,rid) values(?,?)");
			pst.setInt(1, userRole.getUid());
			pst.setInt(2, userRole.getRid());
			System.out.println("鎴愬姛鏂板銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<UserRole> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_userRole(uid,rid) values(?,?)");
			for(int i=0;i<list.size();i++) {
				UserRole userRole = list.get(i);
				pst.setInt(1, userRole.getUid());
				pst.setInt(2, userRole.getRid());
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
			pst = con.prepareStatement("delete from hr_sys_userRole where id=?");
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
			pst = con.prepareStatement("delete from hr_sys_userRole where id in ("+ids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(UserRole userRole) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_userRole set uid=?,rid=? where id=?");
			pst.setInt(1, userRole.getUid());
			pst.setInt(2, userRole.getRid());
			pst.setInt(3, userRole.getId());
			System.out.println("鎴愬姛鏇存柊銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public UserRole queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserRole s = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userRole where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new UserRole();
				s.setId(rs.getInt("id"));
				s.setUid(rs.getInt("uid"));
				s.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryAll() {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userRole order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				UserRole s = new UserRole();
				s.setId(rs.getInt("id"));
				s.setUid(rs.getInt("uid"));
				s.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryByPage(int currentPage, int pageSize) {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userRole order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				UserRole s = new UserRole();
				s.setId(rs.getInt("id"));
				s.setUid(rs.getInt("uid"));
				s.setRid(rs.getInt("rid"));
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
	public List<UserRole> queryByPage(int currentPage, int pageSize, String condition) {
		List<UserRole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userRole "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				UserRole s = new UserRole();
				s.setId(rs.getInt("id"));
				s.setUid(rs.getInt("uid"));
				s.setRid(rs.getInt("rid"));
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
			pst = con.prepareStatement("select count(*) from hr_sys_userRole");
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
			pst = con.prepareStatement("select count(*) from hr_sys_userRole "+condition);
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
	public void deleteUserRolesByUids(String uids) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_userrole where uid in ("+uids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}}
}
