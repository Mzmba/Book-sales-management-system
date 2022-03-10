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
import javax.swing.border.EmptyBorder;

import dao.ReaderDao;
import util.Connect;
import util.StringNull;

public class DeleteReaderInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -1878106319697766203L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("用户编号：");
	private final JLabel lblNewLabel_1 = new JLabel("请输入待注销的用户编号：");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("确认注销");
	private final JLabel lblNewLabel_2 = new JLabel("警告：此操作不可逆！");
	private Connect conutil = new Connect();
	private ReaderDao readerDao = new ReaderDao();

	/**
	 * Create the frame.
	 */
	public DeleteReaderInterface() {
		this.textField.setBounds(118, 46, 122, 30);
		this.textField.setColumns(10);
		setResizable(false);
		setTitle("用户注销");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 255);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel.setBounds(6, 44, 100, 29);

		contentPane.add(this.lblNewLabel);
		this.lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_1.setBounds(6, 18, 224, 23);

		contentPane.add(this.lblNewLabel_1);

		contentPane.add(this.textField);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteReader();
			}
		});
		this.btnNewButton.setBounds(105, 142, 151, 60);

		contentPane.add(this.btnNewButton);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.lblNewLabel_2.setBounds(7, 96, 353, 26);

		contentPane.add(this.lblNewLabel_2);
	}

	protected void deleteReader() {
		String ReaderId = this.textField.getText();
		if (StringNull.isEmpty(ReaderId)) {
			JOptionPane.showMessageDialog(null, "用户编号不能为空！");
			return;
		}
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = readerDao.query(con, Integer.parseInt(ReaderId));
			if (rs.next()) {
				readerDao.delete(con, Integer.parseInt(ReaderId));
				JOptionPane.showMessageDialog(null, "删除成功!");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "删除失败！未找到该编号用户！");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败！未知错误！");
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
