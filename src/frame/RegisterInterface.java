package frame;

import java.awt.Color;
import java.awt.Font;
/**
 * 注册
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import dao.ReaderDao;
import model.Reader;
import util.Connect;
import util.StringNull;

public class RegisterInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -2163005597082669731L;
	private JPanel contentPane;
	private JTextField reader_id;
	private JTextField reader_name;
	private JTextField reader_phone;
	private JTextField reader_password;
	ImageIcon img = new ImageIcon("img/Registerimg.jfif");
	JLabel jl_bg = new JLabel(img); // 背景图片载入到JLabel
	private Connect conutil = new Connect();
	JFrame frame = new JFrame();
	private final JLabel lblNewLabel = new JLabel("请输入注册信息：");

	/**
	 * Create the frame.
	 */
	public RegisterInterface() {
		this.lblNewLabel.setForeground(Color.ORANGE);
		this.lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 27));
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		setTitle("注册界面");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 455, 570);
		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		this.jl_bg.setBounds(-10, 0, 455, 570);
		this.getLayeredPane().add(jl_bg, Integer.valueOf(Integer.MIN_VALUE));
		((JComponent) this.getContentPane()).setOpaque(false); // 设置透明
		JLabel label = new JLabel("用户名：");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));

		reader_id = new JTextField();
		reader_id.setColumns(10);

		JLabel label_1 = new JLabel("姓  名：");
		label_1.setForeground(Color.ORANGE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));

		reader_name = new JTextField();
		reader_name.setColumns(10);

		JLabel label_2 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		label_2.setForeground(Color.ORANGE);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));

		reader_phone = new JTextField();
		reader_phone.setColumns(10);

		JLabel label_3 = new JLabel("密  码：");
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 16));

		reader_password = new JTextField();
		reader_password.setColumns(10);

		JButton button = new JButton("\u6CE8\u518C");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usearRegister(e);
			}
		});

		JButton buttonNot = new JButton("\u6E05\u7A7A");
		buttonNot.setForeground(Color.BLACK);
		buttonNot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delActiontxt();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(label_3).addComponent(label_2))
												.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(Alignment.LEADING,
												gl_contentPane.createSequentialGroup().addGap(29)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane
																		.createSequentialGroup().addComponent(label_1)
																		.addPreferredGap(ComponentPlacement.RELATED))
																.addGroup(gl_contentPane
																		.createSequentialGroup()
																		.addComponent(label, GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGap(26)))))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(button)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(buttonNot))
										.addComponent(reader_password).addComponent(reader_phone)
										.addComponent(reader_name)
										.addComponent(reader_id, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(111).addComponent(this.lblNewLabel)))
				.addGap(112)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(138)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(58))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(15)
										.addComponent(this.lblNewLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(reader_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(56)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(reader_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(57)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(reader_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))
						.addGap(50)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(reader_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3))
						.addGap(37).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(buttonNot))
						.addGap(48)));
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 读者注册
	 *
	 * @param e
	 */
	private void usearRegister(ActionEvent e) {

		String readerIdStr = this.reader_id.getText().toString();
		int readerId = 0;
		if (!StringNull.isEmpty(readerIdStr)) {
			readerId = Integer.parseInt(readerIdStr);
		}

		String readerName = this.reader_name.getText();
		String readerPhone = this.reader_phone.getText();
		String readerPassword = this.reader_password.getText();

		if (StringNull.isEmpty(readerIdStr)) {
			JOptionPane.showMessageDialog(null, "用户编号不能为空!");
			return;
		}
		if (StringNull.isEmpty(readerName)) {
			JOptionPane.showMessageDialog(null, "用户姓名不能为空！");
			return;
		}
		if (StringNull.isEmpty(readerPhone)) {
			JOptionPane.showMessageDialog(null, "用户手机号不能为空！");
			return;
		}
		if (StringNull.isEmpty(readerPassword)) {
			JOptionPane.showMessageDialog(null, "用户密码不能为空！");
			return;
		}

		Connection con = null;
		ReaderDao ud = new ReaderDao();
		try {
			Reader reader = new Reader(readerId, readerName, readerPhone, readerPassword);
			con = conutil.loding();
			Reader rs = ud.login(con, reader);

			if (String.valueOf(rs.getReader_id()).equals(readerIdStr)) {
				JOptionPane.showMessageDialog(null, "账号已存在，注册失败!");
				return;
			} else {
				ud.register(con, reader);
				JOptionPane.showMessageDialog(null, "注册成功!");
				return;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "账号已存在，注册失败!");
			e1.printStackTrace();
			return;
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 清空输入框
	private void delActiontxt() {
		this.reader_id.setText("");
		this.reader_name.setText("");
		this.reader_password.setText("");
		this.reader_phone.setText("");
	}
}