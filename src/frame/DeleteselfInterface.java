package frame;

import java.awt.Color;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dao.ReaderDao;
import util.Connect;

public class DeleteselfInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -1878106319697766203L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("尊敬的用户您好，您正在执行删除账号操作");
	private final JButton btnNewButton = new JButton("确认删除");
	private final JLabel lblNewLabel_2 = new JLabel("警告：此操作不可逆！");
	private Connect conutil = new Connect();
	private ReaderDao readerDao = new ReaderDao();
	private final JLabel lblNewLabel_1 = new JLabel("请确认您的用户编号：");
	private final JLabel lblNewLabel_1_1 = new JLabel("-1");

	public DeleteselfInterface() {
		setResizable(false);
		setTitle("删除账号");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 255);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel.setBounds(7, 6, 380, 29);

		contentPane.add(this.lblNewLabel);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelf();
			}
		});
		this.btnNewButton.setBounds(105, 142, 151, 60);

		contentPane.add(this.btnNewButton);
		this.lblNewLabel_2.setForeground(Color.RED);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.lblNewLabel_2.setBounds(7, 96, 353, 26);

		contentPane.add(this.lblNewLabel_2);
		this.lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_1.setBounds(7, 47, 200, 29);

		contentPane.add(this.lblNewLabel_1);
		this.lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.lblNewLabel_1_1.setBounds(219, 47, 200, 29);

		contentPane.add(this.lblNewLabel_1_1);
		lblNewLabel_1_1.setText(String.valueOf(ReaderMainInterface.readerid));
	}

	protected void deleteSelf() {
		String ReaderId = this.lblNewLabel_1_1.getText();
		Connection con = null;
		try {
			con = conutil.loding();
			ResultSet rs = readerDao.query(con, Integer.parseInt(ReaderId));
			if (rs.next()) {
				readerDao.delete(con, Integer.parseInt(ReaderId));
				JOptionPane.showMessageDialog(null, "删除账号成功，欢迎再次使用。");
				System.exit(0);// 完全退出整个程序
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
