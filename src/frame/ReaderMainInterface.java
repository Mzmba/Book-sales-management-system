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

public class ReaderMainInterface extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 2817048601123480207L;
	private JPanel contentPane;
	public static String readername;
	public static int readerid;
	private final JLabel label_1 = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("  用户操作：");
	private final JLabel lblNewLabel_2 = new JLabel("尊敬的用户：");
	private final JLabel lblNewLabel_2_2 = new JLabel("您目前处于用户主界面，点击相应按钮即可进入操作界面。");
	private final JLabel lblNewLabel_2_1 = new JLabel((String) null);
	private final JButton btnNewButton_3 = new JButton("图书查询");
	private final JButton btnNewButton_3_1 = new JButton("图书购买");
	private final JButton btnNewButton_3_1_1 = new JButton("删除账号");
	private final JButton btnNewButton_3_1_1_1 = new JButton("我的订单");

	/**
	 * Create the frame.
	 */
	public ReaderMainInterface() {

		setTitle("用户主界面");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 417);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		this.label_1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.label_1.setBounds(6, 6, 422, 194);
		setResizable(false);
		contentPane.add(this.label_1);
		this.lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel.setBounds(6, 6, 88, 23);

		contentPane.add(this.lblNewLabel);
		this.lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.lblNewLabel_2.setBounds(6, 212, 168, 23);

		contentPane.add(this.lblNewLabel_2);
		this.lblNewLabel_2_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		this.lblNewLabel_2_2.setBounds(6, 320, 427, 52);

		contentPane.add(this.lblNewLabel_2_2);
		this.lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 48));
		this.lblNewLabel_2_1.setBounds(6, 240, 422, 86);

		contentPane.add(this.lblNewLabel_2_1);
		this.btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryBookInterface queryBookInterface = new QueryBookInterface();
				queryBookInterface.setVisible(true);
			}
		});
		this.btnNewButton_3.setBounds(49, 41, 125, 67);

		contentPane.add(this.btnNewButton_3);
		this.btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BuyBookInterface buyBookInterface = new BuyBookInterface();
				buyBookInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_1.setBounds(226, 41, 125, 67);

		contentPane.add(this.btnNewButton_3_1);
		this.btnNewButton_3_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteselfInterface deleteselfInterface = new DeleteselfInterface();
				deleteselfInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_1_1.setBounds(226, 120, 125, 67);

		contentPane.add(this.btnNewButton_3_1_1);
		this.btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyOrderInterface myOrderInterface = new MyOrderInterface();
				myOrderInterface.setVisible(true);
			}
		});
		this.btnNewButton_3_1_1_1.setBounds(49, 120, 125, 67);

		contentPane.add(this.btnNewButton_3_1_1_1);
		readername = EnterInterface.readername;
		readerid = EnterInterface.readerid;
		this.lblNewLabel_2_1.setText(readername);
	}

}
