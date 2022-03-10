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

public class AddBookInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -7230175691567354083L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("图书编号：");
	private final JTextField textField = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("图书名：");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel_1_1 = new JLabel("图书作者：");
	private final JTextField textField_2 = new JTextField();
	private final JLabel lblNewLabel_1_1_1 = new JLabel("出版社：");
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblNewLabel_1_1_1_1 = new JLabel("图书库存：");
	private final JTextField textField_4 = new JTextField();
	private final JLabel lblNewLabel_1_1_1_1_1 = new JLabel("图书价格：");
	private final JTextField textField_5 = new JTextField();
	private final JButton btnNewButton = new JButton("添加");
	private final JButton btnNewButton_1 = new JButton("清空");
	private final JLabel lblNewLabel_2 = new JLabel("请输入要入库的图书信息：");
	private final JLabel label = new JLabel("");
	private Connect conutil = new Connect();
	private BookDao bookDao = new BookDao();

	/**
	 * Create the frame.
	 */
	public AddBookInterface() {
		setResizable(false);
		this.textField.setBounds(113, 78, 250, 30);
		this.textField.setColumns(10);
		setTitle("图书入库");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label.setBounds(19, 16, 395, 418);
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		contentPane.add(this.label);

		this.lblNewLabel.setBounds(41, 84, 60, 18);
		contentPane.add(this.lblNewLabel);

		contentPane.add(this.textField);
		this.lblNewLabel_1.setBounds(41, 140, 48, 18);

		contentPane.add(this.lblNewLabel_1);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(113, 134, 250, 30);

		contentPane.add(this.textField_1);
		this.lblNewLabel_1_1.setBounds(41, 196, 60, 18);

		contentPane.add(this.lblNewLabel_1_1);
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(113, 190, 250, 30);

		contentPane.add(this.textField_2);
		this.lblNewLabel_1_1_1.setBounds(41, 252, 48, 18);

		contentPane.add(this.lblNewLabel_1_1_1);
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(113, 246, 250, 30);

		contentPane.add(this.textField_3);
		this.lblNewLabel_1_1_1_1.setBounds(41, 308, 60, 18);

		contentPane.add(this.lblNewLabel_1_1_1_1);
		this.textField_4.setColumns(10);
		this.textField_4.setBounds(113, 302, 250, 30);

		contentPane.add(this.textField_4);
		this.lblNewLabel_1_1_1_1_1.setBounds(41, 364, 60, 18);

		contentPane.add(this.lblNewLabel_1_1_1_1_1);
		this.textField_5.setColumns(10);
		this.textField_5.setBounds(113, 358, 250, 30);

		contentPane.add(this.textField_5);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
		this.btnNewButton.setBounds(75, 463, 90, 30);

		contentPane.add(this.btnNewButton);
		this.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delActiontxt();
			}
		});
		this.btnNewButton_1.setBounds(277, 463, 90, 30);

		contentPane.add(this.btnNewButton_1);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(38, 37, 240, 29);

		contentPane.add(this.lblNewLabel_2);
		setLocationRelativeTo(null);

	}

	// 图书入库
	protected void addBook() {
		String bookId = this.textField.getText();
		String bookName = this.textField_1.getText();
		String bookPublish = this.textField_2.getText();
		String bookWriter = this.textField_3.getText();
		String bookAmount = this.textField_4.getText();
		String bookPrice = this.textField_5.getText();
		if (StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "图书名不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookPublish)) {
			JOptionPane.showMessageDialog(null, "图书出版社不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookWriter)) {
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookAmount)) {
			JOptionPane.showMessageDialog(null, "图书库存不能为空！");
			return;
		}
		if (StringNull.isEmpty(bookPrice)) {
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");
			return;
		}
		Connection con = null;
		try {
			Book book = new Book(Integer.parseInt(bookId), bookName, bookWriter, bookPublish,
					Integer.parseInt(bookAmount), Double.parseDouble(bookPrice));
			con = conutil.loding();
			ResultSet rs = bookDao.query2(con, book);// 先检查此书是否已添加

			if (rs.next()) { // 此书已添加
				JOptionPane.showMessageDialog(null, "添加失败，此书已添加！");
				return;
			} else {// 此书未添加
				bookDao.add(con, book);
				JOptionPane.showMessageDialog(null, "添加成功！");
				return;
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

	// 清空输入框
	private void delActiontxt() {
		this.textField.setText("");
		this.textField_1.setText("");
		this.textField_2.setText("");
		this.textField_3.setText("");
		this.textField_4.setText("");
		this.textField_5.setText("");
	}
}
