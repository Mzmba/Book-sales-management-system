package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

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
import model.Book;
import util.Connect;
import util.StringNull;

public class ModifyBookInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 6640860073406267156L;
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("请输入待修改图书的编号：");
	private final JLabel lblNewLabel = new JLabel("图书编号：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("确认");
	private final JLabel lblNewLabel_2_1 = new JLabel("请输入修改后的图书信息：");
	private final JLabel lblNewLabel_1 = new JLabel("图书名：");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel_1_1 = new JLabel("图书作者：");
	private final JTextField textField_2 = new JTextField();
	private final JLabel lblNewLabel_1_1_1 = new JLabel("出版社：");
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblNewLabel_1_1_1_1 = new JLabel("图书价格：");
	private final JTextField textField_4 = new JTextField();
	private final JButton btnNewButton_1 = new JButton("确认修改");
	private final JLabel lblNewLabel_2_2 = new JLabel("警告：操作无法撤销，请仔细确认后修改！");
	private final JButton btnNewButton_1_1 = new JButton("重置输入");
	private Connect conutil = new Connect();
	private BookDao bookDao = new BookDao();

	/**
	 * Create the frame.
	 */
	public ModifyBookInterface() {
		setTitle("图书修改");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label.setBounds(19, 16, 395, 328);

		contentPane.add(this.label);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(35, 28, 260, 29);

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
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(91, 134, 250, 30);

		contentPane.add(this.textField_1);
		this.lblNewLabel_1_1.setBounds(35, 182, 60, 18);

		contentPane.add(this.lblNewLabel_1_1);
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(91, 176, 250, 30);

		contentPane.add(this.textField_2);
		this.lblNewLabel_1_1_1.setBounds(35, 222, 48, 18);

		contentPane.add(this.lblNewLabel_1_1_1);
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(91, 216, 250, 30);

		contentPane.add(this.textField_3);
		this.lblNewLabel_1_1_1_1.setBounds(35, 262, 60, 18);

		contentPane.add(this.lblNewLabel_1_1_1_1);
		this.textField_4.setColumns(10);
		this.textField_4.setBounds(91, 258, 250, 30);

		contentPane.add(this.textField_4);
		this.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Modifybookinformation();
			}
		});
		this.btnNewButton_1.setBounds(73, 356, 90, 30);
		this.btnNewButton_1.setEnabled(false);// 未载入书籍的状态下确认修改按钮不可用
		contentPane.add(this.btnNewButton_1);
		this.lblNewLabel_2_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.lblNewLabel_2_2.setBounds(35, 300, 353, 26);

		contentPane.add(this.lblNewLabel_2_2);
		this.btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delActiontxt();
				textField.setEditable(true);// 重置使得书籍编号可以重新输入
				btnNewButton_1.setEnabled(false);// 使得确认修改按钮不可用
			}
		});
		this.btnNewButton_1_1.setBounds(251, 356, 90, 30);

		contentPane.add(this.btnNewButton_1_1);
		setLocationRelativeTo(null);
	}

	/**
	 * 根据输入的编号载入要修改的书籍的信息
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
				textField.setEditable(false);// 准备修改该编号书籍，并暂时设置无法修改书籍编号。
				btnNewButton_1.setEnabled(true);// 使确认修改按钮可用
				textField_1.setText(rs.getString("book_name"));
				textField_2.setText(rs.getString("book_writer"));
				textField_3.setText(rs.getString("book_publish"));
				textField_4.setText(rs.getString("book_price"));
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

	// 清空
	private void delActiontxt() {
		this.textField.setText("");
		this.textField_1.setText("");
		this.textField_2.setText("");
		this.textField_3.setText("");
		this.textField_4.setText("");
	}

	/**
	 * 修改图书信息
	 */
	private void Modifybookinformation() {
		String bookId = this.textField.getText();
		String bookName = this.textField_1.getText();
		String bookWriter = this.textField_2.getText();
		String bookPublish = this.textField_3.getText();
		String bookPrice = this.textField_4.getText();
		if (StringNull.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookWriter)) {
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookPublish)) {
			JOptionPane.showMessageDialog(null, "图书出版社不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookPrice)) {
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");
			return;
		}
		Book book = new Book(Integer.parseInt(bookId), bookName, bookWriter, bookPublish,
				Double.parseDouble(bookPrice));
		Connection con = null;
		try {
			con = conutil.loding();
			bookDao.update(con, book);
			JOptionPane.showMessageDialog(null, "图书信息更新成功!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "更新失败！未知错误！");
			return;
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
