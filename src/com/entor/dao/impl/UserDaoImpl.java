package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.UserDao;
import com.entor.entity.Role;
import com.entor.entity.User;
import com.entor.util.DBUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void add(User user) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_user(username,password,empId,state,createTime) values(?,?,?,?,?)");
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getEmpId());
			pst.setInt(4, user.getState());
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			System.out.println("鎴愬姛鏂板銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<User> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_user(username,password,empId,state,createTime) values(?,?,?,?,?)");
			for(int i=0;i<list.size();i++) {
				User user = list.get(i);
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				pst.setInt(3, user.getEmpId());
				pst.setInt(4, user.getState());
				pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
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
			pst = con.prepareStatement("delete from hr_sys_user where id=?");
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
			pst = con.prepareStatement("delete from hr_sys_user where id in ("+ids+")");
			System.out.println("鎴愬姛鍒犻櫎銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(User user) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_user set username=?,password=?,empId=?,state=?,createTime=? where id=?");
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getEmpId());
			pst.setInt(4, user.getState());
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pst.setInt(6, user.getId());
			System.out.println("鎴愬姛鏇存柊銆�"+pst.executeUpdate()+"銆戞潯璁板綍");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public User queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		User s = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_user where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new User();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setEmpId(rs.getInt("empid"));
				s.setState(rs.getInt("state"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<User> queryAll() {
		List<User> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_user order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				User s = new User();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setEmpId(rs.getInt("empid"));
				s.setState(rs.getInt("state"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<User> queryByPage(int currentPage, int pageSize) {
		List<User> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_user order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				User s = new User();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setEmpId(rs.getInt("empid"));
				s.setState(rs.getInt("state"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
	public List<User> queryByPage(int currentPage, int pageSize, String condition) {
		List<User> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_user "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				User s = new User();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setEmpId(rs.getInt("empid"));
				s.setState(rs.getInt("state"));
				s.setCreateTime(rs.getTimestamp("createTime"));
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
			pst = con.prepareStatement("select count(*) from hr_sys_user");
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
			pst = con.prepareStatement("select count(*) from hr_sys_user "+condition);
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

	

}
