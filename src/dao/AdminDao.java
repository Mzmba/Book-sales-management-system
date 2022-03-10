package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Admin;
/**
* 管理员数据处理类
*
*/
public class AdminDao {

	/**管理员登录
	 * @param con
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public Admin login(Connection con, Admin admin) throws Exception {// 登录查找信息
		Admin resultUser = null;
		String sql = "select * from admin where admin_name=? and admin_password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, admin.getAdmin_name());// 将指定参数设置为给定 Java String 值
		pstmt.setString(2, admin.getAdmin_password());
		ResultSet rs = pstmt.executeQuery();// 在此 PreparedStatement 对象中执行 SQL 查询，并返回该查询生成的 ResultSet 对象。
		if (rs.next()) {
			resultUser = new Admin();
			resultUser.setAdmin_id(rs.getInt("admin_id"));
			resultUser.setAdmin_name(rs.getString("admin_name"));
			resultUser.setAdmin_phone(rs.getString("admin_phone"));
			resultUser.setAdmin_password(rs.getString("admin_password"));
		}
		return resultUser;
	}
}
