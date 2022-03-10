package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 建立数据库连接的工具类
 *
 * @author 86181
 *
 */
public class Connect {
	Connection con;// 数据库连接
	// 数据库URL
	static final String jdbcUrl = "jdbc:mysql://localhost/lsms_db?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String jdbcUsername = "root"; // 数据库账户名
	static final String jdbcPassword = "123456"; // 数据库账户名的密码

	/**
	 * 连接数据库
	 *
	 * @return
	 */
	public Connection loding() {// 加载数据库
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 加载驱动 mysql 8.0以上版本
			System.out.println("成功加载数据库驱动！");
		} catch (Exception e) {
			System.out.println("加载数据库驱动出错！");
			e.printStackTrace();// 在命令行打印异常信息在程序中出错的位置及原因
		}
		// 连接数据库
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			System.out.println("成功连接数据库服务器！！");
		} catch (Exception e1) {
			System.out.println("连接数据库服务器出现错误！！");
		}

		return con;
	}

	/**
	 * 关闭数据库
	 *
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(java.sql.Connection con) throws Exception {// 关闭数据库
		if (con != null) {
			con.close();
		}
	}
}
