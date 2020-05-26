package sbeamgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.fileutil;
import javabin.users;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class gui extends JFrame {

	private JPanel contentPane;
	DBfact dbfact = new DBfact();
	dbutil dbu = new dbutil();
	users us = new users();
	static Connection con = null;
	private JTextField Account;
	private JTextField Password;

	/**
	 * Launch the application.
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Visible Error!");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToSbeam = new JLabel("                 Welcome to sbeam !");
		lblWelcomeToSbeam.setFont(new java.awt.Font("Dialog", 1, 30));
		lblWelcomeToSbeam.setBounds(146, 83, 566, 141);
		contentPane.add(lblWelcomeToSbeam);
		
		JLabel lblLogIn = new JLabel("Please log in");
		lblLogIn.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLogIn.setBounds(366, 224, 147, 29);
		contentPane.add(lblLogIn);
		
		JLabel lblNewLabel = new JLabel("Account ID");
		lblNewLabel.setBounds(198, 274, 112, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(198, 340, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		Account = new JTextField();
		Account.setBounds(322, 271, 191, 24);
		contentPane.add(Account);
		Account.setColumns(10);
		
		Password = new JTextField();
		Password.setBounds(322, 337, 191, 24);
		contentPane.add(Password);
		Password.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = dbfact.getConnection();
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					String Ac = Account.getText();
					String Pa = Password.getText();
					if(Ac.length() == 0)
						JOptionPane.showMessageDialog(null, "Account ID cannot be empty!");
					else {
						if(Pa.equals(dbu.login(con, Ac))) {
							fileutil.wirteTxt(Ac);
							JOptionPane.showMessageDialog(null, "Log in successfully!");
							gui.this.dispose();
							if(Ac.equals("tutu"))// adminastrator
								admingui.runrun();
							else
								usergui.runrun();
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong password or the account is nonexistent!");
						}
					}
				}
			}
		});
		btnEnter.setBounds(561, 336, 113, 27);
		contentPane.add(btnEnter);
		
		JLabel lblNoAccountregister = new JLabel("No Account\uFF1FRegister\uFF01");
		lblNoAccountregister.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblNoAccountregister.setBounds(198, 421, 232, 29);
		contentPane.add(lblNoAccountregister);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.this.dispose();
				registergui.runrun();
			}
		});
		btnNewButton.setBounds(434, 424, 113, 27);
		contentPane.add(btnNewButton);
	}
}
