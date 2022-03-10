package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

/**
 * 折扣窗口类
 *
 */
public class DisdocuntInterface extends JFrame {
	private static final long serialVersionUID = -8454321123152133152L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("图书编号：");
	private final JLabel lblNewLabel_1 = new JLabel("请输入图书编号和要设置的折扣：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("确认设置");
	private Connect conutil = new Connect();
	private final JLabel lblNewLabel_2 = new JLabel("设置折扣：");
	private BookDao bookDao = new BookDao();
	private final String[] discounts = { "无折扣", "九折", "八折", "七折", "六折", "五折", "四折", "三折", "二折", "一折" };
	private final Double[] realdisDoubles = { 1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1 };
	DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>(discounts);
	private final JComboBox<String> comboBox = new JComboBox<>(comboModel);

	/**
	 * Create the frame.
	 */
	public DisdocuntInterface() {
		this.textField.setBounds(118, 46, 122, 30);
		this.textField.setColumns(10);
		setResizable(false);
		setTitle("图书折扣");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 255);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel.setBounds(6, 44, 100, 29);
		setLocationRelativeTo(null);
		contentPane.add(this.lblNewLabel);
		this.lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_1.setBounds(6, 18, 240, 23);

		contentPane.add(this.lblNewLabel_1);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				discount();
			}
		});
		this.btnNewButton.setBounds(105, 142, 151, 60);

		contentPane.add(this.btnNewButton);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(6, 82, 100, 29);

		contentPane.add(this.lblNewLabel_2);
		this.comboBox.setBounds(118, 85, 122, 28);
		contentPane.add(this.comboBox);
		this.comboBox.setSelectedIndex(0);
	}

	protected void discount() {
		String bookId = this.textField.getText();
		if (StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		Connection con = null;
		try {
			con = conutil.loding();
			Book book = new Book(Integer.parseInt(bookId));
			book.setBook_discount(realdisDoubles[this.comboBox.getSelectedIndex()]);
			ResultSet rs = bookDao.query2(con, book);
			if (rs.next()) {
				bookDao.discount(con, book);
				JOptionPane.showMessageDialog(null, "设置折扣成功!");
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