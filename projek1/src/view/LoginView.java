package view;

import java.awt.EventQueue;
import model.User;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import controller.UserController;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class LoginView {

	private JFrame frame;
	private JFrame frmLoginForm;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		frame = new JFrame(); // add code
		initialize();
		frmLoginForm.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginForm = new JFrame();
		frmLoginForm.getContentPane().setBackground(new Color(102, 204, 255));
		frmLoginForm.setBackground(SystemColor.inactiveCaption);
		frmLoginForm.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		frmLoginForm.setForeground(new Color(0, 0, 0));
		frmLoginForm.setTitle("LOGIN FORM");
		frmLoginForm.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Logo\\application-from-storage-icon.png"));
		frmLoginForm.setBounds(100, 100, 510, 340);
		frmLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginForm.getContentPane().setLayout(null);
		
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = frmLoginForm.getSize().height;
		int width = frmLoginForm.getSize().width;
		int x = (sSize.width-width)/2;
		int y = (sSize.height-height)/2;
		frmLoginForm.setLocation(x, y);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUsername.setBounds(75, 106, 109, 20);
		lblUsername.setForeground(new Color(47, 79, 79));
		frmLoginForm.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPassword.setBounds(75, 157, 109, 20);
		lblPassword.setForeground(new Color(47, 79, 79));
		frmLoginForm.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtUsername.setForeground(new Color(0, 0, 0));
		txtUsername.setBackground(new Color(240, 255, 255));
		txtUsername.setBounds(184, 109, 176, 20);
		frmLoginForm.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(245, 255, 250));
		btnOk.setBackground(new Color(255, 102, 0));
		btnOk.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-login-32.png"));
		btnOk.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Boolean login = false;
						String name = txtUsername.getText().trim();
						String password = passwordField.getText();
						
						UserController usercontroller = new UserController();
						User user = new User();
						user.setUsername(name);
						user.setPassword(password);
						try {
							int success = usercontroller.doLogin(user);
							System.out.println(success);
							if(success==0)
							{
								System.out.println("Admin");
								JOptionPane.showMessageDialog(null, "Login Admin", "Success", 1);
								AdminView window = new AdminView();
								frmLoginForm.dispose();
								int good = usercontroller.searchId(user);
								login = true;
								
							}
							else if(success==1)
							{
								System.out.println("Staff");
								JOptionPane.showMessageDialog(null, "Login Staff", "Success", 1);
								StorageView window = new StorageView();
								frmLoginForm.dispose();
								login = true;
							}
							
							else {
								JOptionPane.showMessageDialog(null, "Not Success", "Login Authentication", 0);
								login = false;
							}
							
					
						} catch (ClassNotFoundException | SQLException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		});
		btnOk.setBounds(241, 213, 108, 35);
		frmLoginForm.getContentPane().add(btnOk);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(245, 255, 250));
		btnSignUp.setBackground(new Color(255, 102, 0));
		btnSignUp.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-add-user-male-32.png"));
		btnSignUp.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpView window = new SignUpView();
				frame.dispose();
			}
		});
		btnSignUp.setBounds(20, 255, 122, 35);
		frmLoginForm.getContentPane().add(btnSignUp);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(245, 255, 250));
		btnCancel.setBackground(new Color(255, 102, 0));
		btnCancel.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-cancel-32.png"));
		btnCancel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				passwordField.setText("");
			}
		});
		btnCancel.setBounds(359, 213, 121, 35);
		frmLoginForm.getContentPane().add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setBackground(new Color(240, 255, 255));
		passwordField.setBounds(184, 160, 176, 20);
		frmLoginForm.getContentPane().add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 494, 70);
		frmLoginForm.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN ");
		lblLogin.setBounds(190, 11, 111, 42);
		panel.add(lblLogin);
		lblLogin.setFont(new Font("Forte", Font.BOLD, 30));
		lblLogin.setForeground(new Color(47, 79, 79));
	}
}
