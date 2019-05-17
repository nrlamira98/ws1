package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Files;
import model.User;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.StorageController;
import controller.UserController;

public class AdminView {

	private JFrame frame;
	private JTable table;
	private JTextField txtSearch;
	private JTable table_1;
	private JTable table_2;
	StorageController sc = new StorageController();
	UserController us = new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView window = new AdminView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminView() {
		frame = new JFrame();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(51, 204, 255));
		frame.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		frame.setTitle("MENU");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Logo\\icons8-menu-64.png"));
		frame.setBounds(100, 100, 560, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = frame.getSize().height;
		int width = frame.getSize().width;
		int x = (sSize.width-width)/2;
		int y = (sSize.height-height)/2;
		frame.setLocation(x, y);
		
		final JPanel Menupnl = new JPanel();
		Menupnl.setForeground(new Color(47, 79, 79));
		Menupnl.setBackground(new Color(51, 204, 255));
		frame.getContentPane().add(Menupnl, "name_5378748285123");
		Menupnl.setLayout(null);
		Menupnl.setVisible(false);
		
		final Panel StaffView = new Panel();
		StaffView.setBackground(new Color(0, 204, 255));
		frame.getContentPane().add(StaffView, "name_514674936445276");
		StaffView.setLayout(null);
		StaffView.setVisible(false);
		
		JScrollPane scrollPaneStaff = new JScrollPane();
		scrollPaneStaff.setBounds(45, 85, 446, 141);
		StaffView.add(scrollPaneStaff);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				//txtFilename.setText(model.getValueAt(i, 3).toString());
				//txtDateTime.setText(model.getValueAt(i, 2).toString());
				//txtUserid.setText(model.getValueAt(i, 5).toString());
			}
		});
		scrollPaneStaff.setViewportView(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 446, 141);
		panel.add(table);
		
		JButton btnLoadStaffList = new JButton("Load Staff List");
		btnLoadStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model =(DefaultTableModel)table.getModel();
					ArrayList<User> users = us.StaffView();
					Object column[] = new Object[7];
					column[0]="id";
					column[1]="Name";
					column[2]="Password";
					column[3]="Address";
					column[4]="Email";
					column[5]="Telephone Number";
					column[6]="Username";
					model.setColumnIdentifiers(column);
					
					Object rowData[] = new Object[7];
				
	
					for(int i = 0; i<users.size(); i++) {
					//table_5.setModel(DbUtils.resultSetToTableModel(users);
						
					rowData[0] = users.get(i).getId();
					rowData[1] = users.get(i).getName();
					rowData[2] = users.get(i).getPassword();
					rowData[3] = users.get(i).getAddress();
					rowData[4] = users.get(i).getEmail();
					rowData[5] = users.get(i).getNotel();
					rowData[6] = users.get(i).getUsername();
					model.addRow(rowData);
					}
					
					table.setModel(model);
					
					
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				
			}
		});
		btnLoadStaffList.setBounds(199, 237, 141, 23);
		StaffView.add(btnLoadStaffList);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(45, 282, 89, 23);
		StaffView.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(158, 282, 89, 23);
		StaffView.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(278, 282, 89, 23);
		StaffView.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(true);
				StaffView.setVisible(false);
			}
		});
		btnBack.setBounds(402, 282, 89, 23);
		StaffView.add(btnBack);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(45, 41, 141, 20);
		StaffView.add(txtSearch);
		txtSearch.setColumns(10);
		
		final JPanel ViewTotal = new JPanel();
		ViewTotal.setBackground(new Color(0, 204, 255));
		frame.getContentPane().add(ViewTotal, "name_516803646766287");
		ViewTotal.setLayout(null);
		ViewTotal.setVisible(false);
		
		JScrollPane scrollTotal = new JScrollPane();
		scrollTotal.setBounds(64, 73, 423, 176);
		ViewTotal.add(scrollTotal);
		
		JPanel panel_2 = new JPanel();
		scrollTotal.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBounds(0, 0, 421, 174);
		panel_2.add(table_2);
		
		JButton btnLoadTotalFile = new JButton("Load Total File ");
		btnLoadTotalFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model =(DefaultTableModel)table_2.getModel();
					ArrayList<Files> users = sc.ActivityView();
					Object column[] = new Object[5];
					column[0]="User id";
					column[1]="File id";
					column[2]="File Name";
					column[3]="Date Time";
					column[4]="Description";
					model.setColumnIdentifiers(column);
					
					Object rowData[] = new Object[5];
				
	
					for(int i = 0; i<users.size(); i++) {
					//table_5.setModel(DbUtils.resultSetToTableModel(users);
						
					rowData[0] = users.get(i).getUserid();
					rowData[1] = users.get(i).getFile_id();
					rowData[2] = users.get(i).getFile_name();
					rowData[3] = users.get(i).getTimeStamp();
					rowData[4] = users.get(i).getStatus();
					model.addRow(rowData);
					}
					
					table_2.setModel(model);
					
					
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
			}
		});
		btnLoadTotalFile.setBounds(196, 23, 145, 23);
		ViewTotal.add(btnLoadTotalFile);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(true);
				ViewTotal.setVisible(false);
			}
		});
		btnBack_2.setBounds(423, 286, 89, 23);
		ViewTotal.add(btnBack_2);
		
		final JPanel ViewHistory = new JPanel();
		ViewHistory.setBackground(new Color(0, 204, 255));
		frame.getContentPane().add(ViewHistory, "name_515610133793377");
		ViewHistory.setLayout(null);
		ViewHistory.setVisible(false);
		
		JScrollPane scrollPaneHistory = new JScrollPane();
		scrollPaneHistory.setBounds(61, 65, 442, 157);
		ViewHistory.add(scrollPaneHistory);
		
		JPanel panel_1 = new JPanel();
		scrollPaneHistory.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 440, 155);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"User id","File id","File Name","Date Time","Description"
				}
			));
		table_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		panel_1.add(table_1);
		
		JButton btnLoadFileHistory = new JButton("Load File History ");
		btnLoadFileHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model =(DefaultTableModel)table_1.getModel();
					ArrayList<Files> users = sc.ActivityView();
					Object column[] = new Object[5];
					column[0]="User id";
					column[1]="File id";
					column[2]="File Name";
					column[3]="Date Time";
					column[4]="Description";
					model.setColumnIdentifiers(column);
					
					Object rowData[] = new Object[5];
				
	
					for(int i = 0; i<users.size(); i++) {
					//table_5.setModel(DbUtils.resultSetToTableModel(users);
						
					rowData[0] = users.get(i).getUserid();
					rowData[1] = users.get(i).getFile_id();
					rowData[2] = users.get(i).getFile_name();
					rowData[3] = users.get(i).getTimeStamp();
					rowData[4] = users.get(i).getStatus();
					model.addRow(rowData);
					}
					
					table_1.setModel(model);
					
					
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			
		});
		btnLoadFileHistory.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		btnLoadFileHistory.setBounds(195, 25, 153, 23);
		ViewHistory.add(btnLoadFileHistory);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(true);
				ViewHistory.setVisible(false);
			}
		});
		btnBack_1.setBounds(417, 285, 89, 23);
		ViewHistory.add(btnBack_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenu mnView = new JMenu("View");
		mnView.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mnFile.add(mnView);
		
		JMenuItem mntmStaffList = new JMenuItem("Staff list");
		mntmStaffList.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mntmStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(false);
				ViewHistory.setVisible(false);
				ViewTotal.setVisible(false);
				StaffView.setVisible(true);
			}
		});
		mnView.add(mntmStaffList);
		
		JMenuItem mntmFileStorage = new JMenuItem("File Storage");
		mntmFileStorage.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mntmFileStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(false);
				ViewHistory.setVisible(false);
				ViewTotal.setVisible(true);
				StaffView.setVisible(false);
			}
		});
		mnView.add(mntmFileStorage);
		
		JMenuItem mntmFileActivity = new JMenuItem("File Activity");
		mntmFileActivity.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mntmFileActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupnl.setVisible(false);
				ViewHistory.setVisible(true);
				ViewTotal.setVisible(false);
				StaffView.setVisible(false);
			}
		});
		mnView.add(mntmFileActivity);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mnFile.add(mntmExit);
		Menupnl.setVisible(true);
		
		
		
		
	}
}
