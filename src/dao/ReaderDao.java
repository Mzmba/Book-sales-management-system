package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reader;

/**
 * 用户数据处理类
 *
 *
 *
 */
public class ReaderDao {
	/**
	 * 用户注册
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public int register(Connection con, Reader reader) throws Exception {
		String sql = "insert into reader values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, reader.getReader_id());
		pstmt.setString(2, reader.getReader_name());
		pstmt.setString(3, reader.getReader_phone());
		pstmt.setString(4, reader.getReader_password());
		return pstmt.executeUpdate();
	}

	/**
	 * 查重用查找，用户id不能相同
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public Reader login(Connection con, Reader reader) throws Exception {
		Reader resultUser = null;
		String sql = "select * from reader where reader_name=? and reader_password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, reader.getReader_name());
		pstmt.setString(2, reader.getReader_password());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			resultUser = new Reader();
			resultUser.setReader_id(rs.getInt("reader_id"));
			resultUser.setReader_name(rs.getString("reader_name"));
			resultUser.setReader_phone(rs.getString("reader_phone"));
			resultUser.setReader_password(rs.getString("reader_password"));
		}
		return resultUser;
	}

	/**
	 * 直接通过用户Id查询用户所有信息
	 *
	 * @param con
	 * @param readerId
	 * @return
	 * @throws Exception
	 */
	public ResultSet query(Connection con, int readerId) throws Exception {
		String sql = "select * from reader where reader_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeQuery();
	}

	/**
	 * 通过Reader类获取用户编号查询用户是否存在
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public ResultSet query2(Connection con, Reader reader) throws Exception {
		String sql = "select * from reader where reader_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, reader.getReader_id());
		return pstmt.executeQuery();
	}

	/**
	 * 删除用户
	 *
	 * @param con
	 * @param readerId
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, int readerId) throws Exception {
		String sql = "delete from reader where reader_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeUpdate();
	}

	/**
	 * 查询所有用户
	 *
	 * @param con
	 * @param reader
	 * @return
	 * @throws SQLException
	 */
	public ResultSet queryall(Connection con) throws SQLException {
		String sql = new String("select * from reader");
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}

}