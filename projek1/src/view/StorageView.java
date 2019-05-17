package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

import com.mysql.jdbc.PreparedStatement;

import controller.FileDatabase;
import controller.FileTypeFilter;
import controller.StorageController;
import database.DBConnection;
import model.Files;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.lang.ArrayIndexOutOfBoundsException;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

public class StorageView {

	private JFrame frame;
	private JFrame frmMenu;
	private JTextField txtUserid;
	private JTextField txtFilename;
	private JTextField txtfilename;
	private JTable table_2;
	private JTextField textSearch;
	private JTable table_3;
	private JTable table_4;
	private JTextField txtDateTime;
	private JTextField textContext;
	private JTable table_5;
	private JButton btnDuplicate;
	private JButton btnDelete1;
	//private JLabel lblWelcome;
	//private String name;
	StorageController sc = new StorageController();
	Files files = new Files();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StorageView window = new StorageView();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public StorageView() throws IOException {
		frame = new JFrame(); // add code
		initialize();
		frmMenu.setVisible(true);
		/*this.name = name;
		String getvalue = lblWelcome.getText();
		lblWelcome.setText(getvalue+" "+name);*/
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		
		frmMenu = new JFrame();
		frmMenu.getContentPane().setBackground(new Color(0, 204, 255));
		frmMenu.setBackground(new Color(51, 204, 255));
		frmMenu.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		frmMenu.setTitle("MENU");
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Logo\\icons8-menu-64.png"));
		frmMenu.setBounds(100, 100, 560, 440);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setVisible(true);
		frmMenu.getContentPane().setLayout(new CardLayout(0, 0));
		
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = frmMenu.getSize().height;
		int width = frmMenu.getSize().width;
		int x = (sSize.width-width)/2;
		int y = (sSize.height-height)/2;
		frmMenu.setLocation(x, y);
		
		final JPanel Menupanel = new JPanel();
		Menupanel.setBackground(new Color(0, 204, 255));
		frmMenu.getContentPane().add(Menupanel, "name_524437230173815");
		Menupanel.setLayout(null);
		Menupanel.setVisible(true);
		
		final JPanel Addpanel = new JPanel();
		Addpanel.setBackground(new Color(102, 204, 255));
		frmMenu.getContentPane().add(Addpanel, "name_524158596133877");
		Addpanel.setVisible(false);
		Addpanel.setLayout(null);
		
		final JPanel Editpanel = new JPanel();
		Editpanel.setForeground(new Color(0, 0, 0));
		Editpanel.setBackground(new Color(51, 204, 255));
		frmMenu.getContentPane().add(Editpanel, "name_524158608819071");
		Editpanel.setLayout(null);
		Editpanel.setVisible(false);
		
		final JPanel Activitypanel = new JPanel();
		Activitypanel.setBackground(new Color(51, 204, 255));
		frmMenu.getContentPane().add(Activitypanel, "name_524158620729848");
		Activitypanel.setLayout(null);
		Activitypanel.setVisible(false);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-undo-32.png"));
		btnBack.setBounds(395, 267, 110, 34);
		btnBack.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupanel.setVisible(true);
				Addpanel.setVisible(false);
			}
		});
		Addpanel.add(btnBack);
		

		textContext = new JTextField();
		textContext.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		textContext.setBounds(40, 50, 444, 106);
		Addpanel.add(textContext);
		textContext.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(255, 102, 0));
		btnAdd.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-plus-32.png"));
		btnAdd.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\bengkel1"));
				fs.setDialogTitle("Save a file");
				fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
				fs.setFileFilter(new FileTypeFilter (".doc", "Word File"));
				fs.setFileFilter(new FileTypeFilter (".docx", "Document File"));
				fs.setFileFilter(new FileTypeFilter (".jpg", "JPEG File"));
				int result = fs.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					String content = textContext.getText();
					File fi = fs.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(fi.getPath());
						fw.write(content);
						fw.flush();
						fw.close();
						
						files.setFilepath(fi.getPath());
						int success = sc.SearchFile(files);
						if (success == 1)
							System.out.println("File already exists");
						else {
							sc.AddFile(fi);
							textContext.setText(" ");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		btnAdd.setBounds(141, 181, 112, 34);
		Addpanel.add(btnAdd);
		
		JButton btnOpen_1 = new JButton("Open");
		btnOpen_1.setBackground(new Color(255, 102, 0));
		btnOpen_1.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-opened-folder-32.png"));
		btnOpen_1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnOpen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\bengkel1"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileTypeFilter (".txt", "Text File"));
				fs.setFileFilter(new FileTypeFilter (".doc", "Word File"));
				fs.setFileFilter(new FileTypeFilter (".docx", "Document File"));
				fs.setFileFilter(new FileTypeFilter (".jpg", "JPEG File"));
				int result = fs.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fs.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
						String s = " ";
						String line = " ";
						FileDatabase fileDB = new FileDatabase();
					//	fileDB.UpdateDB(fi,0,0);
						while((line = br.readLine()) != null) {
							s += line ;
						}
						textContext.setText(s);
						if (br != null) {
							br.close();
						}
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		btnOpen_1.setBounds(263, 181, 110, 34);
		Addpanel.add(btnOpen_1);
		
		JLabel lblUserId = new JLabel("User id :");
		lblUserId.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblUserId.setBounds(40, 44, 73, 14);
		Editpanel.add(lblUserId);
		
		JLabel lblFileName = new JLabel("File name :");
		lblFileName.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblFileName.setBounds(40, 69, 89, 14);
		Editpanel.add(lblFileName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 135, 471, 171);
		Editpanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		table_4 = new JTable();
		table_4.setBounds(0, 0, 469, 170);
		panel.add(table_4);
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"File id", "File Name", "Date Time", "File Path", "User id"
			}
		));
		table_4.getColumnModel().getColumn(0).setPreferredWidth(132);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(134);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(157);
		table_4.setFont(new Font("Century Gothic", Font.BOLD, 11));
		table_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table_4.getSelectedRow();
				TableModel model = table_4.getModel();
				txtFilename.setText(model.getValueAt(i, 3).toString());
				txtDateTime.setText(model.getValueAt(i, 2).toString());
				txtUserid.setText(model.getValueAt(i, 4).toString());
			}
		});
		
		txtUserid = new JTextField();
		txtUserid.setBackground(new Color(240, 255, 255));
		txtUserid.setBounds(123, 42, 201, 20);
		Editpanel.add(txtUserid);
		txtUserid.setColumns(10);
		
		txtFilename = new JTextField();
		txtFilename.setBackground(new Color(240, 255, 255));
		txtFilename.setBounds(123, 67, 201, 20);
		Editpanel.add(txtFilename);
		txtFilename.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setBackground(new Color(255, 69, 0));
		btnUpdate.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-downloading-updates-32.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table_4.getSelectedRow();
					String id= (table_4.getModel().getValueAt(row, 0).toString());
					String name = table_4.getModel().getValueAt(row, 3).toString();
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Please enter the file or directory name which has to be Renamed : ");
					  
				   File oldfile = new File(name.replace("\\" , "\\\\"));
				   
				   System.out.println(name);
				   
					File newfile = new File(txtFilename.getText().replace("\\" , "\\\\"));  
				   
					  System.out.println(newfile);
					  
				
					 
					  if(!oldfile.exists())
					{
					  
					  System.out.println("File or directory does not exist.");
					  
					  System.exit(0);

					  }
					 
					  System.out.print("please enter the new file or directory name : ");

					  
					  
					  System.out.println("Old File or directory name : "+ oldfile);
					  System.out.println("New File or directory name : "+ newfile);
					  
					 String line1 = oldfile.getName();
					 String line2 = newfile.getName();
				    
					 sc.getData(line1,line2);
				    
					  boolean Rename = oldfile.renameTo(newfile);
					  

					    if(!Rename)
					{

					  System.out.println("File or directory does not rename successfully.");

					    System.exit(0);
					  
					  }
					 
					 else {

					  System.out.println("File or directory rename is successfully.");

					  }
					
					    String filename = txtFilename.getText();
					    String userid = txtUserid.getText();
					    
					    files.setStatus("Update");
					    
					      
					        
					   sc.UpdateFile(newfile, filename, userid , id);
					   sc.Activity(newfile, filename, id, files);
					
					DefaultTableModel model =(DefaultTableModel)table_4.getModel();
					JOptionPane.showMessageDialog(null, "Updated successfully!");
					
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}

		});
		btnUpdate.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnUpdate.setBounds(367, 35, 115, 32);
		Editpanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(245, 255, 250));
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-trash-32.png"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDelete1.setVisible(false);
				int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete the file?", "Delete", JOptionPane.YES_NO_OPTION);
				if(confirm==0) {
					try {
						
						int row = table_4.getSelectedRow();
						String id= (table_4.getModel().getValueAt(row, 0).toString());
						String path = table_4.getModel().getValueAt(row, 3).toString();
						File deletes = new File(path.replace("\\" , "\\\\"));
						
						if(deletes.delete()) 
				        { 
				            System.out.println("File deleted successfully"); 
				        } 
				        else
				        { 
				            System.out.println("Failed to delete the file"); 
				        }
						
						File newfile = new File(txtFilename.getText().replace("\\" , "\\\\"));
						String filename = txtFilename.getText();
						String userid = txtUserid.getText();
						
						files.setStatus("Delete");
						sc.DeleteFile(id);
						sc.Activity(newfile, filename, id, files);
						
						DefaultTableModel model =(DefaultTableModel)table_4.getModel();
						JOptionPane.showMessageDialog(null, "Deleted successfully!");
					}
					catch(Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				else {
					DefaultTableModel model =(DefaultTableModel)table_4.getModel();
				}
				
			}
		});
		btnDelete.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnDelete.setBounds(367, 78, 115, 32);
		Editpanel.add(btnDelete);
		
		JButton btnLoadStorage = new JButton("Load Storage");
		btnLoadStorage.setForeground(new Color(245, 255, 250));
		btnLoadStorage.setBackground(new Color(255, 69, 0));
		btnLoadStorage.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-downloads-folder-32.png"));
		btnLoadStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					DefaultTableModel model =(DefaultTableModel)table_4.getModel();
					ArrayList<Files> users = sc.LoadView();
					Object column[] = new Object[5];
					column[0]="File id";
					column[1]="File Name";
					column[2]="Date Time";
					column[3]="File path";
					column[4]="User id";
					model.setColumnIdentifiers(column);
					
					Object rowData[] = new Object[5];
				
	
					for(int i = 0; i<users.size(); i++) {
					//table_5.setModel(DbUtils.resultSetToTableModel(users);
						
					rowData[0] = users.get(i).getFile_id();
					rowData[1] = users.get(i).getFile_name();
					rowData[2] = users.get(i).getTimeStamp();
					rowData[3] = users.get(i).getFilepath();
					rowData[4] = users.get(i).getUserid();
					model.addRow(rowData);
					}
					
					table_4.setModel(model);
					txtFilename.setText("");
					txtDateTime.setText("");
					
					
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
			}
		});
		btnLoadStorage.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnLoadStorage.setBounds(40, 317, 162, 32);
		Editpanel.add(btnLoadStorage);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setForeground(new Color(245, 255, 250));
		btnBack_1.setBackground(new Color(255, 69, 0));
		btnBack_1.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-undo-32.png"));
		btnBack_1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model =(DefaultTableModel)table_4.getModel();
				model.setRowCount(0);
				model.setColumnCount(0);
				frmMenu.getContentPane().setVisible(true);
				Editpanel.setVisible(false);
				txtFilename.setText("");
				txtUserid.setText("");
				txtDateTime.setText("");
				textSearch.setText("");
			}
		});
		btnBack_1.setBounds(424, 317, 110, 33);
		Editpanel.add(btnBack_1);
		
		textSearch = new JTextField();
		textSearch.setBackground(new Color(240, 255, 255));
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					DefaultTableModel model =(DefaultTableModel)table_4.getModel();
					TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
					table_4.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter(textSearch.getText()));
					
					String query = "Select * from content where name =? ";
					Connection con=DBConnection.doConnection();
					PreparedStatement preparedStatement= (PreparedStatement) con.prepareStatement(query);
					preparedStatement.setString(1, textSearch.getText());
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()) {
						
						String filename = rs.getString("name");
						txtFilename.setText(filename);
						
						String timestamp= rs.getString("timestamp");
						txtDateTime.setText(timestamp);
						
						String userid =rs.getString("user_id");
						txtUserid.setText(userid);
					}
					
					else
					{
						txtFilename.setText("");
						txtUserid.setText("");
						txtDateTime.setText("");
					}
				}
					
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			
		});
		textSearch.setBounds(40, 13, 138, 20);
		Editpanel.add(textSearch);
		textSearch.setColumns(10);
		
		table_3 = new JTable();
		table_3.setBounds(164, 257, 15, -1);
		Editpanel.add(table_3);
		
		JLabel lblDateTime = new JLabel("Date Time :");
		lblDateTime.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblDateTime.setBounds(40, 94, 73, 14);
		Editpanel.add(lblDateTime);
		
		txtDateTime = new JTextField();
		txtDateTime.setBackground(new Color(240, 255, 255));
		txtDateTime.setColumns(10);
		txtDateTime.setBounds(123, 92, 201, 20);
		Editpanel.add(txtDateTime);
		
				final JPanel Duplicate = new JPanel();
				Duplicate.setBackground(new Color(51, 204, 255));
				Duplicate.setBounds(0, 0, 544, 367);
				Editpanel.add(Duplicate);
				Duplicate.setVisible(false);
				Duplicate.setLayout(null);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(40, 132, 474, 174);
				Duplicate.add(scrollPane_1);
				
				JPanel panel_1 = new JPanel();
				scrollPane_1.setViewportView(panel_1);
				panel_1.setLayout(null);
				
				table_5 = new JTable();
				table_5.setBounds(0, 0, 472, 172);
				panel_1.add(table_5);
				table_5.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
					}
				));
				table_5.setBackground(new Color(240, 255, 255));
				table_5.setFont(new Font("Century Gothic", Font.BOLD, 11));
				table_5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = table_5.getSelectedRow();
						TableModel model = table_5.getModel();
						txtFilename.setText(model.getValueAt(i, 1).toString());
						txtDateTime.setText(model.getValueAt(i, 3).toString());
					}
				});
		
				JButton btnBack_3 = new JButton("Back");
				btnBack_3.setBounds(424, 317, 110, 33);
				btnBack_3.setForeground(new Color(245, 255, 250));
				btnBack_3.setBackground(new Color(255, 69, 0));
				btnBack_3.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-undo-32.png"));
				btnBack_3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
				btnBack_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel model =(DefaultTableModel)table_5.getModel();
						model.setRowCount(0);
						model.setColumnCount(0);
						Duplicate.setVisible(false);
						Editpanel.setVisible(true);
						btnLoadStorage.setVisible(true);
						lblUserId.setVisible(true);
						txtUserid.setVisible(true);
						textSearch.setVisible(true);
						lblDateTime.setBounds(40, 94, 73, 14);
						txtDateTime.setBounds(123, 92, 201, 20);
						lblFileName.setBounds(40, 69, 89, 14);
						txtFilename.setBounds(123, 67, 201, 20);
						table_4.setVisible(true);
						table_5.setVisible(false);
						btnBack_1.setVisible(true);
						txtFilename.setText("");
						txtDateTime.setText("");
						btnDuplicate.setVisible(true);
						btnDelete.setVisible(true);
						scrollPane_1.setVisible(false);
						scrollPane.setVisible(true);
						DefaultTableModel models =(DefaultTableModel)table_4.getModel();
						models.setRowCount(0);
						models.setColumnCount(0);
				
					}
				});
				Duplicate.add(btnBack_3);
				
				btnDelete1 = new JButton("Delete");
				btnDelete1.setBounds(367, 78, 115, 32);
				btnDelete1.setForeground(new Color(245, 255, 250));
				btnDelete1.setBackground(new Color(255, 69, 0));
				btnDelete1.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-trash-32.png"));
				btnDelete1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete the file?", "Delete", JOptionPane.YES_NO_OPTION);
						if(confirm==0) {
							try {
								
								int row = table_5.getSelectedRow();
								String id= (table_5.getModel().getValueAt(row, 0).toString());
								String name = table_5.getModel().getValueAt(row, 1).toString();
								File deletes = new File(name.replace("\\" , "\\\\"));
								
								if(deletes.delete()) 
						        { 
						            System.out.println("File deleted successfully"); 
						        } 
						        else
						        { 
						            System.out.println("Failed to delete the file"); 
						        }
								
								File newfile = new File(txtFilename.getText().replace("\\" , "\\\\"));
								String filename = txtFilename.getText();
								String userid = txtUserid.getText();
								
								files.setStatus("Delete");
								sc.DeleteFile(id);
								sc.Activity(newfile, filename, id, files);
								
								DefaultTableModel model =(DefaultTableModel)table_5.getModel();
								JOptionPane.showMessageDialog(null, "Deleted successfully!");
							}
							catch(Exception e2) {
								JOptionPane.showMessageDialog(null, e2);
							}
						}
						else {
							DefaultTableModel model =(DefaultTableModel)table_5.getModel();
						}
						
					}
				});
				btnDelete1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
				Duplicate.add(btnDelete1);
		
				JButton btnLoadFiles = new JButton("Load Duplicate File");
				btnLoadFiles.setBounds(40, 316, 197, 33);
				Duplicate.add(btnLoadFiles);
				btnLoadFiles.setForeground(new Color(245, 255, 250));
				btnLoadFiles.setBackground(new Color(255, 69, 0));
				btnLoadFiles.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-downloads-folder-32.png"));
				btnLoadFiles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
					
						DefaultTableModel model =(DefaultTableModel)table_5.getModel();
						ArrayList<Files> users = sc.DuplicateView();
						Object column[] = new Object[3];
						column[0]="File Name";
						column[1]="Date Time";
						column[2]="Description";
						model.setColumnIdentifiers(column);
						
						Object rowData[] = new Object[3];
					
		
						for(int i = 0; i<users.size(); i++) {
						//table_5.setModel(DbUtils.resultSetToTableModel(users);
							
						rowData[0] = users.get(i).getFile_name();
						rowData[1] = users.get(i).getTimeStamp();
						rowData[2] = users.get(i).getStatus();
						model.addRow(rowData);
						}
						
						table_5.setModel(model);
						txtFilename.setText("");
						txtDateTime.setText("");
						
						
						}
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
				});
				btnLoadFiles.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
				btnDuplicate = new JButton("Duplicate");
				btnDuplicate.setForeground(new Color(245, 255, 250));
				btnDuplicate.setBackground(new Color(255, 69, 0));
				btnDuplicate.setFont(new Font("Century Gothic", Font.BOLD, 11));
				btnDuplicate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnLoadStorage.setVisible(false);
						btnLoadFiles.setVisible(true);
						lblUserId.setVisible(false);
						txtUserid.setVisible(false);
						textSearch.setVisible(false);
						Duplicate.setVisible(true);
						txtDateTime.setBounds(123, 80, 201, 20);
						lblDateTime.setBounds(40, 82, 73, 14);
						lblFileName.setBounds(40, 43, 89, 14);
						txtFilename.setBounds(123, 41, 201, 20);
						table_4.setVisible(false);
						table_5.setVisible(true);
						btnBack_1.setVisible(false);
						txtFilename.setText("");
						txtUserid.setText("");
						txtDateTime.setText("");
						textSearch.setText("");
						btnDuplicate.setVisible(false);
						btnDelete1.setVisible(true);
						scrollPane.setVisible(false);
						scrollPane_1.setVisible(true);
					}
				});
				btnDuplicate.setBounds(245, 318, 124, 32);
				Editpanel.add(btnDuplicate);	
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 79, 503, 230);
		Activitypanel.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JButton btnViewRecentActivity = new JButton("View Recent Activity");
		btnViewRecentActivity.setForeground(new Color(245, 255, 250));
		btnViewRecentActivity.setBackground(new Color(255, 69, 0));
		btnViewRecentActivity.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-database-view-32.png"));
		btnViewRecentActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Select * from activity ";
					Connection con=DBConnection.doConnection();
					PreparedStatement preparedStatement;
					preparedStatement = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = preparedStatement.executeQuery(query);
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnViewRecentActivity.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnViewRecentActivity.setBounds(157, 32, 210, 36);
		Activitypanel.add(btnViewRecentActivity);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.setForeground(new Color(245, 255, 250));
		btnBack_2.setBackground(new Color(255, 69, 0));
		btnBack_2.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-undo-32.png"));
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model =(DefaultTableModel)table_2.getModel();
				model.setRowCount(0);
				model.setColumnCount(0);
				Activitypanel.setVisible(false);
				frmMenu.getContentPane().setVisible(true);
			}
		});
		btnBack_2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnBack_2.setBounds(426, 320, 108, 30);
		Activitypanel.add(btnBack_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 544, 34);
		frmMenu.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setForeground(new Color(47, 79, 79));
		mnFile.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-document-32.png"));
		mnFile.setFont(new Font("Century", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.setForeground(new Color(47, 79, 79));
		mntmAdd.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-create-document-32.png"));
		mntmAdd.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupanel.setVisible(false);
				Editpanel.setVisible(false);
				Activitypanel.setVisible(false);
				Addpanel.setVisible(true);
			}
		});
		mnFile.add(mntmAdd);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.setForeground(new Color(47, 79, 79));
		mntmEdit.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-downloads-folder-32.png"));
		mntmEdit.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupanel.setVisible(false);
				Addpanel.setVisible(false);
				Activitypanel.setVisible(false);
				Editpanel.setVisible(true);
			}
		});
		mnFile.add(mntmEdit);
		
		JMenuItem mntmActivity = new JMenuItem("Activity");
		mntmActivity.setForeground(new Color(47, 79, 79));
		mntmActivity.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-database-view-32.png"));
		mntmActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menupanel.setVisible(false);
				Addpanel.setVisible(false);
				Editpanel.setVisible(false);
				Activitypanel.setVisible(true);
			}
		});
		mntmActivity.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnFile.add(mntmActivity);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setForeground(new Color(47, 79, 79));
		mntmExit.setIcon(new ImageIcon("D:\\Users\\Nurul Amira\\Downloads\\icons8-exit-32.png"));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						Boolean login = true;
						if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Are you really want to quit?",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){
							System.exit(0);
							login = false;
						}
					}
		});
		
		mntmExit.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnFile.add(mntmExit);
		
		
	}
}
