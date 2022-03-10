package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Book;
import util.StringNull;

/**
 * 图书数据处理类
 *
 */
public class BookDao {

	public BookDao() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 模糊查询图书方法1（不带价格区间）
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet query(Connection con, Book book) throws Exception {
		StringBuffer sql = new StringBuffer("select * from book");

		// 数据库模糊查询
		if (StringNull.isNotEmpty(book.getBook_name())) {
			sql.append(" and book_name like '%" + book.getBook_name() + "%'");
		}

		if (StringNull.isNotEmpty(book.getBook_writer())) {
			sql.append(" and book_writer like '%" + book.getBook_writer() + "%'");
		}

		if (StringNull.isNotEmpty(book.getBook_publish())) {
			sql.append(" and book_publish like '%" + book.getBook_publish() + "%'");
		}

		PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * 模糊查询图书方法2(带价格区间）
	 *
	 * @param con
	 * @param book
	 * @param min
	 * @param max
	 * @return
	 */
	public ResultSet query(Connection con, Book book, String min, String max) throws Exception {
		StringBuffer sql = new StringBuffer("select * from book");

		// 数据库模糊查询
		if (StringNull.isNotEmpty(book.getBook_name())) {
			sql.append(" and book_name like '%" + book.getBook_name() + "%'");
		}

		if (StringNull.isNotEmpty(book.getBook_writer())) {
			sql.append(" and book_writer like '%" + book.getBook_writer() + "%'");
		}

		if (StringNull.isNotEmpty(book.getBook_publish())) {
			sql.append(" and book_publish like '%" + book.getBook_publish() + "%'");
		}
		if (StringNull.isNotEmpty(min) && StringNull.isEmpty(max)) { // 有最小无最大
			sql.append(" and book_price between " + min + " and 2147483647");
			System.out.println("1" + sql);
		}
		if (StringNull.isEmpty(min) && StringNull.isNotEmpty(max)) { // 有最大无最小
			sql.append(" and book_price between " + -1 + " and " + max);
			System.out.println("2" + sql);
		}
		if (StringNull.isNotEmpty(min) && StringNull.isNotEmpty(max)) { // 都有
			sql.append(" and book_price between " + min + " and " + max);
			System.out.println("3" + sql);
		}

		PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * 精确查询图书(根据编号查询)
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet query2(Connection con, Book book) throws Exception {
		String sql = "select * from book where book_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, book.getBook_id());

		return pstmt.executeQuery();
	}

	/**
	 * 图书信息修改
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Book book) throws Exception {
		String sql = "update book set book_name= ?, book_writer= ?, book_publish= ?, book_price= ? where book_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBook_name());
		pstmt.setString(2, book.getBook_writer());
		pstmt.setString(3, book.getBook_publish());
		pstmt.setDouble(4, book.getBook_price());
		pstmt.setInt(5, book.getBook_id());
		return pstmt.executeUpdate();
	}

	/**
	 * 添加图书
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Book book) throws Exception {
		String sql = "insert into book values(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, book.getBook_id());
		pstmt.setString(2, book.getBook_name());
		pstmt.setString(3, book.getBook_writer());
		pstmt.setString(4, book.getBook_publish());
		pstmt.setInt(5, book.getBook_amount());
		pstmt.setDouble(6, book.getBook_price());
		return pstmt.executeUpdate();
	}

	/**
	 * 删除图书(精确删除)
	 *
	 * @param con
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, int bookId) throws Exception {
		String sql = "delete from book where book_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		return pstmt.executeUpdate();
	}

	/**
	 * 设置折扣
	 *
	 * @param con
	 * @param readerId
	 * @return
	 * @throws Exception
	 */
	public int discount(Connection con, Book book) throws Exception {
		String sql = "update book set book_discount=? where book_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, book.getBook_discount());
		pstmt.setInt(2, book.getBook_id());
		return pstmt.executeUpdate();
	}

	/**
	 * 库存修改
	 *
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int inventory(Connection con, Book book) throws Exception {
		String sql = "update book set book_amount=? where book_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, book.getBook_amount());
		pstmt.setInt(2, book.getBook_id());
		return pstmt.executeUpdate();
	}

}
