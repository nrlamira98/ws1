package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import controller.UserController;
import model.User;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class SignUpView {

	private JFrame frame;
	private JFrame frmRegistration;
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtTelNo;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpView window = new SignUpView();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUpView() {
		frame = new JFrame(); // add code
		initialize();
		frmRegistration.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.getContentPane().setBackground(new Color(102, 204, 255));
		frmRegistration.setTitle("SIGN UP");
		frmRegistration.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Logo\\application-from-storage-icon.png"));
		frmRegistration.setBounds(100, 100, 510, 380);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistration.getContentPane().setLayout(null);
		
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = frmRegistration.getSize().height;
		int width = frmRegistration.getSize().width;
		int x = (sSize.width-width)/2;
		int y = (sSize.height-height)/2;
		frmRegistration.setLocation(x, y);
		
		JLabel lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setForeground(new Color(47, 79, 79));
		lblSignUp.setFont(new Font("Forte", Font.BOLD, 30));
		lblSignUp.setBounds(176, 11, 164, 30);
		frmRegistration.getContentPane().add(lblSignUp);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblName.setBounds(83, 80, 60, 14);
		frmRegistration.getContentPane().add(lblName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblPassword.setBounds(83, 115, 87, 14);
		frmRegistration.getContentPane().add(lblPassword);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblAddress.setBounds(83, 140, 87, 14);
		frmRegistration.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblEmail.setBounds(83, 197, 60, 14);
		frmRegistration.getContentPane().add(lblEmail);
		
		JLabel lblTelNo = new JLabel("Tel No :");
		lblTelNo.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblTelNo.setBounds(83, 224, 60, 14);
		frmRegistration.getContentPane().add(lblTelNo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 102, 0));
		comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Staff"}));
		comboBox.setBounds(412, 77, 72, 20);
		frmRegistration.getContentPane().add(comboBox);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtName.setBackground(new Color(240, 255, 255));
		txtName.setBounds(176, 78, 164, 20);
		frmRegistration.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtPassword.setBackground(new Color(240, 255, 255));
		txtPassword.setBounds(176, 110, 164, 20);
		frmRegistration.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtAddress.setBackground(new Color(240, 255, 255));
		txtAddress.setBounds(176, 138, 164, 46);
		frmRegistration.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtEmail.setBackground(new Color(240, 255, 255));
		txtEmail.setBounds(176, 195, 164, 20);
		frmRegistration.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtTelNo = new JTextField();
		txtTelNo.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtTelNo.setBackground(new Color(240, 255, 255));
		txtTelNo.setBounds(176, 222, 164, 20);
		frmRegistration.getContentPane().add(txtTelNo);
		txtTelNo.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(245, 255, 250));
		btnOk.setBackground(new Color(255, 102, 0));
		btnOk.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-ok-32.png"));
		btnOk.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setName(txtName.getText().trim());
				user.setPassword(txtPassword.getText());
				user.setAddress(txtAddress.getText().trim());
				user.setEmail(txtEmail.getText().trim());
				user.setNotel(txtTelNo.getText().trim());
				user.setLevel(comboBox.getSelectedIndex());
				user.setUsername(txtUsername.getText().trim());
				System.out.println(comboBox.getSelectedIndex());
			
				UserController userController = new UserController();
				try {
					if(userController.findUser(user)==1) {
						System.out.println("Email exists!!");
						JOptionPane.showMessageDialog(null, "Email already been used", "Fail", 0);
					}
					
					else {
					int success = userController.addUser(user);
					System.out.println("success Signup"+success);
					if(success>0)
					{
						JOptionPane.showMessageDialog(null, "Signup "+comboBox.getSelectedIndex()=="0"?"Admin":"Staff", "Success", 1);
						LoginView window = new LoginView();
						frmRegistration.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Signup "+comboBox.getSelectedIndex()=="0"?"Staff":"Admin", "Success", 1);
						LoginView window = new LoginView();
						frmRegistration.dispose();
						
					}
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//st.executeUpdate("insert into signup() values ('"+txtName.getText()+"','"+txtPassword.getText()+"','"+txtAddress.getText()+"','"+txtEmail.getText()+"','"+txtTel.getText()+"')");
			
			
			
			
		
			}
		});
		btnOk.setBounds(147, 293, 103, 37);
		frmRegistration.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(245, 255, 250));
		btnCancel.setBackground(new Color(255, 102, 0));
		btnCancel.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-cancel-32.png"));
		btnCancel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(260, 293, 115, 37);
		frmRegistration.getContentPane().add(btnCancel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(245, 255, 250));
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-undo-32.png"));
		btnBack.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView window = new LoginView();
				frame.dispose();
			}
		});
		btnBack.setBounds(381, 293, 103, 37);
		frmRegistration.getContentPane().add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 494, 55);
		frmRegistration.getContentPane().add(panel);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtUsername.setColumns(10);
		txtUsername.setBackground(new Color(240, 255, 255));
		txtUsername.setBounds(176, 253, 164, 20);
		frmRegistration.getContentPane().add(txtUsername);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblUsername.setBounds(83, 257, 87, 14);
		frmRegistration.getContentPane().add(lblUsername);
	}
}
