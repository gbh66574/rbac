package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.SysuserroleDao;
import com.entor.entity.Sysuserrole;
import com.entor.util.DBUtils;
import com.entor.vo.Sysuserrolevo;

public class SysuserroleDaoimpl implements SysuserroleDao {

	public void add(Sysuserrole sysuserrole) {
		Connection con=DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_userrole(uid,rid) values(?,?)");
			pst.setInt(1, sysuserrole.getUid());
			pst.setInt(2, sysuserrole.getRid());
			System.out.println("成功增加"+pst.executeUpdate()+"条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Sysuserrole> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_userrole(uid,rid) values(?,?)");
			for(int i=0;i<list.size();i++) {
				Sysuserrole sysuserrole = list.get(i);
				pst.setInt(1, sysuserrole.getUid());
				pst.setInt(2, sysuserrole.getRid());
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
			pst = con.prepareStatement("delete from hr_sys_userrole where id=?");
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
			pst = con.prepareStatement("delete from hr_sys_userrole where id in ("+ids+")");
			System.out.println("成功删除"+pst.executeUpdate()+"条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}


	@Override
	public void update(Sysuserrole sysuserrole) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_userrole set uid=?,rid=? where id=?");
			pst.setInt(1, sysuserrole.getUid());
			pst.setInt(2, sysuserrole.getRid());
			System.out.println("成功更新"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Sysuserrole queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Sysuserrole sr = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userrole where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				sr = new Sysuserrole();
				sr.setUid(rs.getInt("uid"));
				sr.setRid(rs.getInt("rid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return sr;
	}

	@Override
	public List<Sysuserrole> queryAll() {
		List<Sysuserrole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userrole order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysuserrole sr = new Sysuserrole();
				sr.setId(rs.getInt("id"));
				sr.setUid(rs.getInt("uid"));
				sr.setRid(rs.getInt("rid"));
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
	public List<Sysuserrole> queryByPage(int currentPage, int pageSize) {
		List<Sysuserrole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userrole  order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysuserrole sr = new Sysuserrole();
				sr.setId(rs.getInt("id"));
				sr.setUid(rs.getInt("uid"));
				sr.setRid(rs.getInt("rid"));
				list.add(sr);
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
	public List<Sysuserrole> queryByPage(int currentPage, int pageSize, String condition) {
		List<Sysuserrole> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userrole "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysuserrole sr = new Sysuserrole();
				sr.setId(rs.getInt("id"));
				sr.setUid(rs.getInt("uid"));
				sr.setRid(rs.getInt("rid"));
				list.add(sr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}*/
	


	@Override
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_userrole");
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
			pst = con.prepareStatement("select count(*) from hr_sys_userrole "+condition);
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
	public List<Sysuserrolevo> sysuserrolequeryByPage(int currentPage, int pageSize) {
		List<Sysuserrolevo> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_userrole,hr_sys_role,hr_sys_user where hr_sys_userrole.uid=hr_sys_user.id and hr_sys_userrole.rid=hr_sys_role.id order by hr_sys_userrole.id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Sysuserrolevo sr = new Sysuserrolevo();
				sr.setId(rs.getInt("hr_sys_userrole.id"));
				sr.setUsername(rs.getString("hr_sys_user.username"));
				sr.setName(rs.getString("hr_sys_role.name"));
				list.add(sr);
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
	public List<Sysuserrole> queryByPage(int currentPage, int pageSize, String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
