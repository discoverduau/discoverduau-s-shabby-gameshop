package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datebase_interface.DBfact;
import datebase_interface.dbutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javabin.SelectWhat;
import javabin.users;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class registergui extends JFrame {

	private JPanel contentPane;
	private JTextField Ac;
	private JTextField Pa;
	private JTextField Country;
	private JTextField Birthday;
	
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	/**
	 * Launch the application.
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registergui frame = new registergui();
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
	public registergui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("              Welcome to join Sbeam");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblNewLabel.setBounds(26, 32, 713, 156);
		contentPane.add(lblNewLabel);
		
		JLabel lblInputYour = new JLabel("input your personal information");
		lblInputYour.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblInputYour.setBounds(243, 212, 320, 40);
		contentPane.add(lblInputYour);
		
		JLabel lblNewLabel_1 = new JLabel("Account ID");
		lblNewLabel_1.setBounds(76, 278, 161, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(76, 338, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(76, 382, 72, 18);
		contentPane.add(lblSex);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(76, 424, 72, 18);
		contentPane.add(lblCountry);
		
		JLabel lblNewLabel_3 = new JLabel("Birthday(xxxx.xx.xx)");
		lblNewLabel_3.setBounds(76, 471, 190, 18);
		contentPane.add(lblNewLabel_3);
		
		Ac = new JTextField();
		Ac.setBounds(302, 289, 201, 24);
		contentPane.add(Ac);
		Ac.setColumns(10);
		
		Pa = new JTextField();
		Pa.setBounds(302, 335, 201, 24);
		contentPane.add(Pa);
		Pa.setColumns(10);
		
		JComboBox Sex = new JComboBox();
		Sex.setModel(new DefaultComboBoxModel(SelectWhat.values()));
		Sex.setBounds(302, 379, 201, 24);
		contentPane.add(Sex);
		
		Country = new JTextField();
		Country.setBounds(302, 421, 201, 24);
		contentPane.add(Country);
		Country.setColumns(10);
		
		Birthday = new JTextField();
		Birthday.setBounds(302, 468, 201, 24);
		contentPane.add(Birthday);
		Birthday.setColumns(10);
		
		JButton btnNewButton = new JButton("register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = db.getConnection();
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					users us = new users();
					if(Ac.getText().length() == 0 || Pa.getText().length() == 0 || Country.getText().length() == 0 || Birthday.getText().length() == 0)
						JOptionPane.showMessageDialog(null, "All information must be written!");
					else {
						us.setId(Ac.getText());
						us.setPassword(Pa.getText());
						if(Sex.getSelectedItem().toString().equals("male"))
							us.setSex("male");
						else
							us.setSex("female");
						us.setCountry(Country.getText());
						us.setBirthday(Birthday.getText());
						if(dbu.whetherhasid(con, us.getId()) >= 1) {
							JOptionPane.showMessageDialog(null, "Account ID has been exist!");
						}
						else {
							dbu.insertusers(con, us.getId(), us.getPassword(), us.getSex(), us.getCountry(), us.getBirthday());
							
							registergui.this.dispose();
							gui.runrun();
						}
						
					}
				}

				
				
			}
		});
		btnNewButton.setBounds(599, 467, 113, 27);
		contentPane.add(btnNewButton);
	}
}
