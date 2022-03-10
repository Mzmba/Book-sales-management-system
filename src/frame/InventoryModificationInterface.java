package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.BookDao;
import model.Book;
import util.Connect;
import util.StringNull;

public class InventoryModificationInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 8452373596692580295L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("图书编号：");
	private final JLabel lblNewLabel_1 = new JLabel("请输入图书编号和更新后的库存数量：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("确认设置");
	private Connect conutil = new Connect();
	private final JLabel lblNewLabel_2 = new JLabel("库存数量：");
	private BookDao bookDao = new BookDao();
	private final JTextField textField_1 = new JTextField();

	/**
	 * Create the frame.
	 */
	public InventoryModificationInterface() {
		this.textField.setBounds(118, 46, 122, 30);
		this.textField.setColumns(10);
		setResizable(false);
		setTitle("库存修改");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 255);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel.setBounds(6, 44, 100, 29);

		contentPane.add(this.lblNewLabel);
		this.lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_1.setBounds(6, 18, 272, 23);

		contentPane.add(this.lblNewLabel_1);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inventoryModification();
			}
		});
		this.btnNewButton.setBounds(105, 142, 151, 60);

		contentPane.add(this.btnNewButton);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(6, 82, 100, 29);

		contentPane.add(this.lblNewLabel_2);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(118, 88, 122, 30);

		contentPane.add(this.textField_1);
	}

	protected void inventoryModification() {
		String bookId = this.textField.getText();
		if (StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		Connection con = null;
		try {
			con = conutil.loding();
			Book book = new Book(Integer.parseInt(bookId));
			book.setBook_amount(Integer.parseInt(this.textField_1.getText()));
			ResultSet rs = bookDao.query2(con, book);
			if (rs.next()) {
				bookDao.inventory(con, book);
				JOptionPane.showMessageDialog(null, "库存更新成功!");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "设置失败！未找到该编号的书籍");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "设置失败！未知错误！");
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
