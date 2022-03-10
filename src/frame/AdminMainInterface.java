package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AdminMainInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 4607476557963651822L;
	/**
	 *
	 */
	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("  图书管理：");
	private final JButton btnNewButton = new JButton("图书入库");
	private final JButton btnNewButton_1 = new JButton("图书修改");
	private final JButton btnNewButton_2 = new JButton("图书删除");
	private final JButton btnNewButton_3 = new JButton("图书查询");
	private final JButton btnNewButton_3_1 = new JButton("图书折扣");
	private final JButton btnNewButton_3_1_1 = new JButton("库存修改");
	private final JLabel label_1 = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("  用户管理：");
	private final JButton btnNewButton_4 = new JButton("用户查询");
	private final JButton btnNewButton_4_1 = new JButton("用户添加");
	private final JButton btnNewButton_4_2 = new JButton("用户删除");
	private String adminname;
	private final JLabel lblNewLabel_2 = new JLabel("尊敬的图书馆管理员：");
	private final JLabel lblNewLabel_2_1 = new JLabel("");
	private final JLabel lblNewLabel_2_2 = new JLabel("您目前处于管理员主界面，点击相应按钮即可进入操作界面。");
	private final JButton btnNewButton_3_2 = new JButton("检查订单");

	/**
	 * Create the frame.
	 */
	public AdminMainInterface() {
		adminname = EnterInterface.adminname;
		setResizable(false);
		setTitle("管理员主界面");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 570);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		label.setBounds(6, 6, 422, 202);
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		contentPane.add(this.label);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel.setBounds(6, 6, 88, 23);

		contentPane.add(this.lblNewLabel);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddBookInterface addBookInterface = new AddBookInterface();
				addBookInterface.setVisible(true);
			}
		});
		this.btnNewButton.setBounds(15, 36, 125, 67);

		contentPane.add(this.btnNewButton);
		this.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyBookInterface modifyBookInterface = new ModifyBookInterface();
				modifyBookInterface.setVisible(true);
			}
		});
		this.btnNewButton_1.setBounds(150, 36, 125, 67);

		contentPane.add(this.btnNewButton_1);
		this.btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteBookInterface deleteReaderInterface = new DeleteBookInterface();
				deleteReaderInterface.setVisible(true);
			}
		});
		this.btnNewButton_2.setBounds(291, 36, 125, 35);

		contentPane.add(this.btnNewButton_2);
		this.btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryBookInterface queryBookInterface = new QueryBookInterface();
				queryBookInterface.setVisible(true);
			}
		});
		this.btnNewButton_3.setBounds(15, 115, 125, 67);

		contentPane.add(this.btnNewButton_3);
		this.btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DisdocuntInterface disdocuntInterface = new DisdocuntInterface();
				disdocuntInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_1.setBounds(291, 132, 125, 45);

		contentPane.add(this.btnNewButton_3_1);
		this.btnNewButton_3_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InventoryModificationInterface inventoryModificationInterface = new InventoryModificationInterface();
				inventoryModificationInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_1_1.setBounds(291, 78, 125, 45);

		contentPane.add(this.btnNewButton_3_1_1);
		this.label_1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label_1.setBounds(6, 220, 422, 133);

		contentPane.add(this.label_1);
		this.lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_1.setBounds(6, 238, 88, 23);

		contentPane.add(this.lblNewLabel_1);
		this.btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryReaderInterface queryReaderInterface = new QueryReaderInterface();
				queryReaderInterface.setVisible(true);
			}
		});
		this.btnNewButton_4.setBounds(15, 273, 125, 67);

		contentPane.add(this.btnNewButton_4);
		this.btnNewButton_4_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddReaderInterface addReaderInterface = new AddReaderInterface();
				addReaderInterface.setVisible(true);
			}
		});
		this.btnNewButton_4_1.setBounds(150, 273, 125, 67);

		contentPane.add(this.btnNewButton_4_1);
		this.btnNewButton_4_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteReaderInterface deleteReaderInterface = new DeleteReaderInterface();
				deleteReaderInterface.setVisible(true);
			}
		});
		this.btnNewButton_4_2.setBounds(291, 273, 125, 67);

		contentPane.add(this.btnNewButton_4_2);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_2.setBounds(15, 365, 168, 23);

		contentPane.add(this.lblNewLabel_2);
		this.lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 48));
		this.lblNewLabel_2_1.setBounds(25, 389, 391, 94);
		lblNewLabel_2_1.setText(adminname);
		contentPane.add(this.lblNewLabel_2_1);
		this.lblNewLabel_2_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		this.lblNewLabel_2_2.setBounds(6, 473, 427, 52);

		contentPane.add(this.lblNewLabel_2_2);
		this.btnNewButton_3_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOrderInterface checkOrderInterface = new CheckOrderInterface();
				checkOrderInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_2.setBounds(150, 115, 125, 67);

		contentPane.add(this.btnNewButton_3_2);
	}
}
