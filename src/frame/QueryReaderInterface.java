package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
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

import dao.ReaderDao;
import util.AutoFitTableColums;
import util.Connect;
import util.StringNull;

public class QueryReaderInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -1270102017594039325L;
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("  请输入待查询用户的编号(ID)：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("查询");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable bookTable = new JTable();
	private Connect conutil = new Connect();
	private ReaderDao readerDao = new ReaderDao();

	/**
	 * Create the frame.
	 */
	public QueryReaderInterface() {
		setResizable(false);
		setTitle("用户查询");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 570);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label.setBounds(6, 6, 427, 86);
		contentPane.add(this.label);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(6, 16, 286, 29);

		contentPane.add(this.lblNewLabel_2);
		this.textField.setColumns(10);
		this.textField.setBounds(16, 50, 276, 30);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Precisequery();
			}
		});
		this.btnNewButton.setBounds(304, 25, 109, 55);

		contentPane.add(this.btnNewButton);
		this.scrollPane.setBounds(6, 104, 433, 421);

		contentPane.add(this.scrollPane);
		String[] columnNames = { "用户编号", "用户名", "用户手机号", "用户密码" };
		bookTable.setFillsViewportHeight(true);
		bookTable.setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			private static final long serialVersionUID = 2270348433879980994L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.scrollPane.setViewportView(bookTable);
		this.fillTable();// 初始化表格信息(查询出所有读者).
		AutoFitTableColums.FitTableColumns(bookTable);// 自适应列宽
	}

	private void fillTable() {// 初始化
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = readerDao.queryall(con);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("reader_id"));
				v.add(rs.getString("reader_name"));
				v.add(rs.getString("reader_phone"));
				v.add(rs.getString("reader_password"));
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
		if (StringNull.isEmpty(this.textField.getText())) {
			this.fillTable();// 列出所有书籍
			JOptionPane.showMessageDialog(null, "用户编号不能为空！");
			return;
		}
		int Readerid = Integer.parseInt(this.textField.getText());
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = readerDao.query(con, Readerid);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("reader_id"));
				v.add(rs.getString("reader_name"));
				v.add(rs.getString("reader_phone"));
				v.add(rs.getString("reader_password"));
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
