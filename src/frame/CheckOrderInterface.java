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

import dao.OrderDao;
import util.AutoFitTableColums;
import util.Connect;
import util.StringNull;

public class CheckOrderInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -1456853244039537583L;
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("  请输入待查询订单的编号(ID)：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("查询");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable orderTable = new JTable();
	private Connect conutil = new Connect();
	private OrderDao orderDao = new OrderDao();

	/**
	 * Create the frame.
	 */
	public CheckOrderInterface() {
		setResizable(false);
		setTitle("检查订单");
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
		this.lblNewLabel_2.setBounds(6, 6, 286, 29);

		contentPane.add(this.lblNewLabel_2);
		this.textField.setColumns(10);
		this.textField.setBounds(16, 38, 276, 30);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Precisequery();
			}
		});
		this.btnNewButton.setBounds(304, 18, 109, 55);

		contentPane.add(this.btnNewButton);
		this.scrollPane.setBounds(0, 104, 433, 421);

		contentPane.add(this.scrollPane);
		String[] columnNames = { "订单编号", "单价", "总价", "数量", "图书编号", "用户编号", "备注", };
		orderTable.setFillsViewportHeight(true);
		orderTable.setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			private static final long serialVersionUID = 2270348433879980994L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.scrollPane.setViewportView(orderTable);
		this.fillTable();// 初始化表格信息(查询出所有订单).
		AutoFitTableColums.FitTableColumns(orderTable);// 自适应列宽

	}

	private void fillTable() {// 初始化
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = orderDao.queryall(con);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("order_id"));
				v.add(rs.getString("order_price"));
				v.add(rs.getString("order_total"));
				v.add(rs.getString("order_amount"));
				v.add(rs.getString("order_book_id"));
				v.add(rs.getString("order_reader_id"));
				v.add(rs.getString("order_remark"));
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

	private void Precisequery() {
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		dtm.setRowCount(0);
		if (StringNull.isEmpty(this.textField.getText())) {
			this.fillTable();// 列出所有书籍
			JOptionPane.showMessageDialog(null, "订单编号不能为空！");
			return;
		}
		int Orderid = Integer.parseInt(this.textField.getText());
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = orderDao.query(con, Orderid);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("order_id"));
				v.add(rs.getString("order_price"));
				v.add(rs.getString("order_total"));
				v.add(rs.getString("order_amount"));
				v.add(rs.getString("order_book_id"));
				v.add(rs.getString("order_reader_id"));
				v.add(rs.getString("order_remark"));
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
