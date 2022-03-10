package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import model.Book;
import util.AutoFitTableColums;
import util.Connect;
import util.StringNull;

/**
 * @author 86181
 *
 */
/**
 * @author 86181
 *
 */
public class QueryBookInterface extends JFrame {

	Map<Double, String> Map_discount = new HashMap<>() {

		private static final long serialVersionUID = -3965570070464266911L;
		{

			put(1.0, "无折扣");
			put(0.9, "九折");
			put(0.8, "八折");
			put(0.7, "七折");
			put(0.6, "六折");
			put(0.5, "五折");
			put(0.4, "四折");
			put(0.3, "三折");
			put(0.2, "二折");
			put(0.1, "一折");
		}
	};
	private static final long serialVersionUID = 8644730853491728909L;
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("模糊查询：");
	private final JLabel label_1 = new JLabel("图 书 名：");
	private final JLabel label_2 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
	private final JLabel label_3 = new JLabel("出 版 社：");
	private final JLabel label_2_1 = new JLabel("价格区间：");
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	private final JTextField textField_2 = new JTextField();
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("至");
	private final JTextField textField_4 = new JTextField();
	private final JButton btnNewButton = new JButton("查询");
	private final JLabel label_4 = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("精确查询：");
	private final JLabel label_2_2 = new JLabel("图书编号：");
	private final JTextField textField_5 = new JTextField();
	private final JButton btnNewButton_1 = new JButton("查询");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable bookTable = new JTable();
	private Connect conutil = new Connect();
	private BookDao bookDao = new BookDao();

	/**
	 * Create the frame.
	 */
	public QueryBookInterface() {
		this.textField.setBounds(95, 58, 163, 30);
		this.textField.setColumns(10);
		setResizable(false);
		setTitle("图书查询");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 570);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		label.setBounds(6, 6, 310, 310);
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		contentPane.add(this.label);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.lblNewLabel.setBounds(18, 16, 93, 26);

		contentPane.add(this.lblNewLabel);
		this.label_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.label_1.setBounds(18, 62, 72, 20);

		contentPane.add(this.label_1);
		this.label_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.label_2.setBounds(18, 105, 70, 20);

		contentPane.add(this.label_2);
		this.label_3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.label_3.setBounds(18, 148, 64, 20);

		contentPane.add(this.label_3);
		this.label_2_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.label_2_1.setBounds(18, 191, 70, 20);

		contentPane.add(this.label_2_1);

		contentPane.add(this.textField);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(95, 101, 163, 30);

		contentPane.add(this.textField_1);
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(94, 144, 163, 30);

		contentPane.add(this.textField_2);
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(95, 187, 64, 30);

		contentPane.add(this.textField_3);
		this.lblNewLabel_1.setBounds(171, 191, 55, 18);

		contentPane.add(this.lblNewLabel_1);
		this.textField_4.setColumns(10);
		this.textField_4.setBounds(194, 187, 64, 30);

		contentPane.add(this.textField_4);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fuzzyquery();
			}
		});
		this.btnNewButton.setBounds(95, 239, 110, 43);

		contentPane.add(this.btnNewButton);
		this.label_4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label_4.setBounds(6, 328, 310, 197);

		contentPane.add(this.label_4);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.lblNewLabel_2.setBounds(18, 337, 93, 26);

		contentPane.add(this.lblNewLabel_2);
		this.label_2_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.label_2_2.setBounds(18, 375, 70, 20);

		contentPane.add(this.label_2_2);
		this.textField_5.setColumns(10);
		this.textField_5.setBounds(95, 371, 163, 30);

		contentPane.add(this.textField_5);
		this.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Precisequery();
			}
		});
		this.btnNewButton_1.setBounds(95, 413, 110, 43);

		contentPane.add(this.btnNewButton_1);
		this.scrollPane.setBounds(328, 6, 730, 519);

		contentPane.add(this.scrollPane);
		String[] columnNames = { "图书编号", "图书名", "图书作者", "出版社", "图书库存", "图书价格", "图书折扣" };
		bookTable.setFillsViewportHeight(true);
		bookTable.setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			private static final long serialVersionUID = -1050866242078483642L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.scrollPane.setViewportView(bookTable);
		this.fillTable(new Book());// 初始化图书信息,book为空，再通过模糊查询即可查询出所有书籍了。
		AutoFitTableColums.FitTableColumns(bookTable);// 自适应列宽
	}

	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = bookDao.query(con, book);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("book_id"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("book_writer"));
				v.add(rs.getString("book_publish"));
				v.add(rs.getString("book_amount"));
				v.add(rs.getString("book_price"));
				v.add(Map_discount.get(Double.parseDouble(rs.getString("book_discount"))));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 模糊搜索
	 */
	private void fuzzyquery() {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Book book = new Book();
		book.setBook_name(this.textField.getText());
		book.setBook_writer(this.textField_1.getText());
		book.setBook_publish(this.textField_2.getText());
		String min = this.textField_3.getText();
		String max = this.textField_4.getText();
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = bookDao.query(con, book, min, max);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("book_id"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("book_writer"));
				v.add(rs.getString("book_publish"));
				v.add(rs.getString("book_amount"));
				v.add(rs.getString("book_price"));
				v.add(Map_discount.get(Double.parseDouble(rs.getString("book_discount"))));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 精确搜索
	 */
	private void Precisequery() {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Book book = new Book();
		if (StringNull.isEmpty(this.textField_5.getText())) {
			this.fillTable(new Book());// 列出所有书籍
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		book.setBook_id(Integer.parseInt(this.textField_5.getText()));
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = bookDao.query2(con, book);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("book_id"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("book_writer"));
				v.add(rs.getString("book_publish"));
				v.add(rs.getString("book_amount"));
				v.add(rs.getString("book_price"));
				v.add(Map_discount.get(Double.parseDouble(rs.getString("book_discount"))));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
