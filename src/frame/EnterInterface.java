package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.AdminDao;
import dao.ReaderDao;
import model.Admin;
import model.Reader;
import util.Connect;
import util.StringNull;

public class EnterInterface extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = -2873897152598765887L;
	private final JLabel lblNewLabel = new JLabel("图书销售管理系统");
	private final JRadioButton rdbtnNewRadioButton = new JRadioButton("用户登录");
	private final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("管理员登录");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JLabel lblNewLabel_1 = new JLabel("用户名：");
	private final JLabel lblNewLabel_1_1 = new JLabel("密  码：");
	private final JTextField textField = new JTextField();
	private final JPasswordField textField_1 = new JPasswordField();
	private final JButton btnNewButton = new JButton("登录");
	private final JButton btnNewButton_1 = new JButton("注册");
	private int action = 0;
	private Connect conutil = new Connect();
	private AdminDao adminDao = new AdminDao();
	private ReaderDao readerDao = new ReaderDao();
	private ImageIcon img = new ImageIcon("img/Enterimg.png");
	private JLabel jl_bg = new JLabel(img); // 背景图片载入到JLabel
	public static String adminname;
	public static String readername;
	public static int readerid;



	/**
	 * Create the frame.
	 */
	public EnterInterface() {

		this.textField.setBounds(94, 115, 216, 30);
		this.textField.setColumns(10);
		setTitle("登录界面");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 350);
		getContentPane().setLayout(null);
		this.jl_bg.setBounds(0, 0, 400, 350);
		this.getLayeredPane().add(jl_bg, Integer.valueOf(Integer.MIN_VALUE));
		((JComponent) this.getContentPane()).setOpaque(false); // 设置透明
		setLocationRelativeTo(null);
		this.lblNewLabel.setForeground(Color.ORANGE);
		this.lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 27));
		this.lblNewLabel.setToolTipText("");
		this.lblNewLabel.setBounds(84, 6, 216, 37);

		getContentPane().add(this.lblNewLabel);
		this.rdbtnNewRadioButton.setForeground(Color.ORANGE);
		this.rdbtnNewRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action = 1;
			}
		});
		this.rdbtnNewRadioButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		this.rdbtnNewRadioButton.setBounds(81, 55, 80, 20);

		getContentPane().add(this.rdbtnNewRadioButton);
		this.rdbtnNewRadioButton_1.setForeground(Color.ORANGE);
		this.rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action = 2;
			}
		});
		this.rdbtnNewRadioButton_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		this.rdbtnNewRadioButton_1.setBounds(207, 55, 94, 20);
		buttonGroup.add(rdbtnNewRadioButton);

		getContentPane().add(this.rdbtnNewRadioButton_1);
		buttonGroup.add(rdbtnNewRadioButton_1);
		this.lblNewLabel_1.setForeground(Color.BLACK);
		this.lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.lblNewLabel_1.setBounds(18, 112, 83, 29);

		getContentPane().add(this.lblNewLabel_1);
		this.lblNewLabel_1_1.setForeground(Color.BLACK);
		this.lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.lblNewLabel_1_1.setBounds(18, 161, 72, 28);

		getContentPane().add(this.lblNewLabel_1_1);

		getContentPane().add(this.textField);
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(94, 164, 216, 30);

		getContentPane().add(this.textField_1);
		this.btnNewButton.addActionListener(new ActionListener() {// 登录按钮事件监听器。
			@Override
			public void actionPerformed(ActionEvent e) {
				if (0 == action)
					JOptionPane.showMessageDialog(null, "请选择登录方式！");
				if (1 == action) {
					int toRmif = userLogin(e);
					if (1 == toRmif) {
						ReaderMainInterface Rmif = new ReaderMainInterface();
						Rmif.setVisible(true);
					}
				}
				if (2 == action) {
					int toAmif = adminLogin(e);
					if (1 == toAmif) {
						AdminMainInterface Amif = new AdminMainInterface();
						Amif.setVisible(true);
					}
				}
			}
		});

		this.btnNewButton.setBounds(220, 235, 90, 30);
		this.btnNewButton.setForeground(Color.BLACK);
		getContentPane().add(this.btnNewButton);

		this.btnNewButton_1.addActionListener(new ActionListener() { // 注册按钮事件监听器。
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterInterface ri = new RegisterInterface();
				ri.setVisible(true);
			}
		});
		this.btnNewButton_1.setBounds(94, 235, 90, 30);
		this.btnNewButton_1.setForeground(Color.BLACK);
		getContentPane().add(this.btnNewButton_1);

	}

	/*
	 * 管理员登录事件处理
	 */
	private int adminLogin(ActionEvent e) {
		String userName = this.textField.getText();
		String password = new String(this.textField_1.getPassword());// 获取密码
		// 提示框
		if (StringNull.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "管理员名不能为空！");
			return 0;
		}
		if (StringNull.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return 0;
		}
		Admin admin = new Admin(userName, password);
		Connection con = null;
		try {
			con = conutil.loding();// 连接数据库
			Admin curreatAdmin = adminDao.login(con, admin);// 调用管理员数据处理类进行登录验证。

			if (curreatAdmin != null) {
				dispose();// 关闭登录窗口
				// 提示框
				JOptionPane.showMessageDialog(null, "管理员登陆成功！");
				adminname = admin.getAdmin_name();
				return 1;
			} else {
				JOptionPane.showMessageDialog(null, "管理员名或者密码错误！");
				return 0;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		} finally {
			try {
				conutil.closeCon(con);// 关闭数据库连接
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/*
	 * 用户登录事件处理
	 */
	private int userLogin(ActionEvent e) {
		String userName1 = this.textField.getText();
		String password1 = new String(this.textField_1.getPassword());// 获取密码
		if (StringNull.isEmpty(userName1)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return 0;
		}
		if (StringNull.isEmpty(password1)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return 0;
		}
		Reader reader = new Reader(userName1, password1);
		Connection con = null;
		try {
			con = conutil.loding();// 连接数据库
			Reader curreatReader = readerDao.login(con, reader);

			if (curreatReader != null) {
				dispose();// 关闭登录窗口
				JOptionPane.showMessageDialog(null, "登陆成功！");
				readername = curreatReader.getReader_name();
				readerid = curreatReader.getReader_id();
				return 1;
			} else {

				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				return 0;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		} finally {
			try {
				conutil.closeCon(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
