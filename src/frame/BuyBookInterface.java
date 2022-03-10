package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dao.BookDao;
import dao.OrderDao;
import model.Book;
import model.Order;
import util.Connect;
import util.StringNull;

public class BuyBookInterface extends JFrame {
	Map<Double, String> Map_discount = new HashMap<>() {
		/**
		 *
		 */
		private static final long serialVersionUID = 8712518570218706151L;

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
	private static final long serialVersionUID = 7807996091489029699L;
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("请输入待购买图书的编号：");
	private final JLabel lblNewLabel = new JLabel("图书编号：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("载入");
	private final JLabel lblNewLabel_2_1 = new JLabel("请确认待购买图书信息：");
	private final JLabel lblNewLabel_1 = new JLabel("图书名：");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel_1_1 = new JLabel("图书作者：");
	private final JTextField textField_2 = new JTextField();
	private final JLabel lblNewLabel_1_1_1 = new JLabel("出版社：");
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblNewLabel_1_1_1_1 = new JLabel("图书价格：");
	private final JTextField textField_4 = new JTextField();
	private final JButton btnNewButton_1 = new JButton("购买");
	private final JButton btnNewButton_1_1 = new JButton("重置");
	private final JLabel lblNewLabel_1_1_1_1_1 = new JLabel("图书折扣：");
	private final JTextField textField_5 = new JTextField();
	private final JLabel label_1 = new JLabel("");
	private final JLabel lblNewLabel_2_1_1 = new JLabel("请输入购买书籍数量：");
	private final JTextField textField_6 = new JTextField();
	private final JLabel lblNewLabel_2_1_1_1 = new JLabel("价格总计：");
	private final JLabel lblNewLabel_3 = new JLabel("NaN");
	private Connect conutil = new Connect();
	private BookDao bookDao = new BookDao();
	private OrderDao orderDao = new OrderDao();
	Double temp_discount;
	private final JButton btnNewButton_2 = new JButton("确认");
	private final JLabel lblNewLabel_2_1_1_2 = new JLabel("备注：");
	private final JTextField textField_7 = new JTextField();

	/**
	 * Create the frame.
	 */
	public BuyBookInterface() {

		this.textField_6.setEditable(false);
		this.textField_6.setText("1");
		this.textField_6.setBounds(251, 371, 90, 30);
		this.textField_6.setColumns(10);
		setTitle("购买图书");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label.setBounds(19, 16, 395, 330);
		contentPane.add(this.label);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(35, 28, 260, 29);
		setResizable(false);
		contentPane.add(this.lblNewLabel_2);
		this.lblNewLabel.setBounds(35, 69, 60, 18);

		contentPane.add(this.lblNewLabel);
		this.textField.setColumns(10);
		this.textField.setBounds(107, 63, 188, 30);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Loadbook();
			}
		});
		this.btnNewButton.setBounds(307, 63, 90, 30);

		contentPane.add(this.btnNewButton);
		this.lblNewLabel_2_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2_1.setBounds(35, 99, 260, 29);

		contentPane.add(this.lblNewLabel_2_1);
		this.lblNewLabel_1.setBounds(35, 140, 48, 18);

		contentPane.add(this.lblNewLabel_1);
		this.textField_1.setEditable(false);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(91, 134, 250, 30);

		contentPane.add(this.textField_1);
		this.lblNewLabel_1_1.setBounds(35, 182, 60, 18);

		contentPane.add(this.lblNewLabel_1_1);
		this.textField_2.setEditable(false);
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(91, 176, 250, 30);

		contentPane.add(this.textField_2);
		this.lblNewLabel_1_1_1.setBounds(35, 222, 48, 18);

		contentPane.add(this.lblNewLabel_1_1_1);
		this.textField_3.setEditable(false);
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(91, 216, 250, 30);

		contentPane.add(this.textField_3);
		this.lblNewLabel_1_1_1_1.setBounds(35, 262, 60, 18);

		contentPane.add(this.lblNewLabel_1_1_1_1);
		this.textField_4.setEditable(false);
		this.textField_4.setColumns(10);
		this.textField_4.setBounds(91, 258, 250, 30);

		contentPane.add(this.textField_4);
		this.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BuyBook();
			}
		});
		this.btnNewButton_1.setBounds(91, 443, 90, 30);
		this.btnNewButton_1.setEnabled(false);// 未载入书籍的状态下不可购买
		contentPane.add(this.btnNewButton_1);
		this.btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setEditable(true);// 重置使得书籍编号可以重新输入
				btnNewButton_1.setEnabled(false);// 使购买按钮不可用
				delActiontxt();
			}
		});
		this.btnNewButton_1_1.setBounds(251, 443, 90, 30);

		contentPane.add(this.btnNewButton_1_1);
		this.lblNewLabel_1_1_1_1_1.setBounds(35, 302, 60, 18);

		contentPane.add(this.lblNewLabel_1_1_1_1_1);
		this.textField_5.setEditable(false);
		this.textField_5.setColumns(10);
		this.textField_5.setBounds(91, 300, 250, 30);

		contentPane.add(this.textField_5);
		this.label_1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label_1.setBounds(19, 358, 395, 115);

		contentPane.add(this.label_1);
		this.lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2_1_1.setBounds(35, 369, 260, 29);

		contentPane.add(this.lblNewLabel_2_1_1);

		contentPane.add(this.textField_6);
		this.lblNewLabel_2_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2_1_1_1.setBounds(251, 410, 260, 29);

		contentPane.add(this.lblNewLabel_2_1_1_1);
		this.lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		this.lblNewLabel_3.setForeground(Color.RED);
		this.lblNewLabel_3.setBounds(343, 413, 205, 29);

		contentPane.add(this.lblNewLabel_3);
		this.btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Updateprice();
			}
		});
		this.btnNewButton_2.setBounds(343, 371, 54, 30);

		contentPane.add(this.btnNewButton_2);
		this.lblNewLabel_2_1_1_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2_1_1_2.setBounds(35, 410, 60, 29);

		contentPane.add(this.lblNewLabel_2_1_1_2);
		this.textField_7.setText("无备注");
		this.textField_7.setColumns(10);
		this.textField_7.setBounds(91, 410, 148, 30);

		contentPane.add(this.textField_7);
		setLocationRelativeTo(null);

	}

	/**
	 * 载入待购买书籍
	 */
	private void Loadbook() {
		Book book = new Book();
		if (StringNull.isEmpty(this.textField.getText())) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			delActiontxt();
			return;
		}
		book.setBook_id(Integer.parseInt(this.textField.getText()));
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = bookDao.query2(con, book);
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "未找到该书籍!");
				delActiontxt();
			} else {
				textField.setEditable(false);// 暂时设置无法修改书籍编号。
				btnNewButton_1.setEnabled(true);// 使购买按钮可用
				textField_6.setEditable(true);// 使购买数量按钮可用
				textField_1.setText(rs.getString("book_name"));
				textField_2.setText(rs.getString("book_writer"));
				textField_3.setText(rs.getString("book_publish"));
				textField_4.setText(rs.getString("book_price"));
				textField_5.setText(Map_discount.get(Double.parseDouble(rs.getString("book_discount"))));
				temp_discount = rs.getDouble("book_discount");
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

	private void Updateprice() {
		int num = Integer.parseInt(textField_6.getText());
		Double price = Double.parseDouble(textField_4.getText());
		Double discount = temp_discount;
		Double totalprice = price * discount * num;
		lblNewLabel_3.setText(String.format("%.4f", totalprice));
	}

	private void BuyBook() {
		int num = Integer.parseInt(textField_6.getText());
		Double price = Double.parseDouble(textField_4.getText());
		Double discount = temp_discount;
		Double orderprice = price * discount;
		Double totalprice = Double.parseDouble(lblNewLabel_3.getText());
		String bookId = this.textField.getText();
		String remark = this.textField_7.getText();
		int readerid = ReaderMainInterface.readerid;
		Connection con = null;
		try {
			con = conutil.loding();
			Book book = new Book(Integer.parseInt(bookId));
			Order order = new Order(orderprice, totalprice, num, remark, Integer.parseInt(bookId), readerid);

			ResultSet rs = bookDao.query2(con, book);
			if (rs.next()) {
				int inventory = rs.getInt("book_amount");
				int newinventory = inventory - num;
				if (newinventory >= 0) {
					book.setBook_amount(newinventory);
					bookDao.inventory(con, book);
					orderDao.add(con, order);
					JOptionPane.showMessageDialog(null, "购买成功!订单添加成功，可在我的订单中查看。");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "购买失败，该图书库存不足！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "购买失败！未知错误！");
			return;
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 清空
	private void delActiontxt() {
		this.textField.setText("");
		this.textField_1.setText("");
		this.textField_2.setText("");
		this.textField_3.setText("");
		this.textField_4.setText("");
		this.textField_5.setText("");
		this.textField_6.setText("1");
	}
}