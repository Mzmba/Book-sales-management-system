package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;

public class OrderDao {
	/**
	 * 添加订单
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Order order) throws Exception {

		String sql = "insert into `order` values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, order.getOrder_id());
		pstmt.setDouble(2, order.getOrder_price());
		pstmt.setDouble(3, order.getOrder_total());
		pstmt.setInt(4, order.getOrder_amount());
		pstmt.setString(5, order.getOrder_remark());
		pstmt.setInt(6, order.getOrder_book_id());
		pstmt.setInt(7, order.getOrder_reader_id());
		return pstmt.executeUpdate();
	}

	/**
	 * 查询所有订单
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws SQLException
	 */
	public ResultSet queryall(Connection con) throws SQLException {
		String sql = new String("select * from `order`");
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}

	/**
	 * 通过订单编号精确搜索订单
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public ResultSet query(Connection con, int orderid) throws Exception {

		String sql = "select * from `order` where order_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, orderid);
		return pstmt.executeQuery();
	}

	/**
	 * 通过用户编号精确搜索订单
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public ResultSet query2(Connection con, int readerid) throws Exception {

		String sql = "select * from `order` where order_reader_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, readerid);
		return pstmt.executeQuery();
	}
}
