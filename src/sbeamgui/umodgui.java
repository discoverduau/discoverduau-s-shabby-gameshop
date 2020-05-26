package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

import javabin.fileutil;
import javabin.mod;
import javabin.umod;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class umodgui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	String ac = fileutil.readTxt();
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	private JTable table;

	/**
	 * Launch the application.
	 * 
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					umodgui frame = new umodgui();
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
	public umodgui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseModsTo = new JLabel("choose mods to play more happily");
		lblChooseModsTo.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblChooseModsTo.setBounds(198, 71, 515, 63);
		contentPane.add(lblChooseModsTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(mod.values()));
		comboBox.setBounds(87, 174, 121, 24);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(302, 174, 241, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = db.getConnection();
				if(con == null) {
					JOptionPane.showMessageDialog(null, "Network Error!");
				}
				else {
					String condition = textField.getText().trim();
					Vector<String> v = new Vector<String>();
					v.add("modname");
					v.add("userid");
					v.add("gamename");
					v.add("describe");
					v.add("downloadweb");
					DefaultTableModel df = new DefaultTableModel(v,0);
					ArrayList<umod> um = new ArrayList<umod>();
					if(comboBox.getSelectedItem().toString().equals("all")) {
						um = dbu.getumod(con);
					}
					else if(comboBox.getSelectedItem().toString().equals("gamename")) {
						um = dbu.getumodbygame(con, condition);
					}
					else if(comboBox.getSelectedItem().toString().equals("modname")) {
						um = dbu.getumodbyname(con, condition);
					}
					for(umod u: um) {
						String[] line = {u.getModname(),u.getId(),u.getGamename(),u.getDescribe(),u.getDownloadweb()};
						df.addRow(line);
					}
					table.setModel(df);
				}
			}
		});
		refresh.setBounds(630, 173, 113, 27);
		contentPane.add(refresh);
		

		
		JButton up = new JButton("upload your mod");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				umodgui.this.dispose();
				upmodgui.runrun();
			}
		});
		up.setBounds(630, 623, 200, 27);
		contentPane.add(up);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				umodgui.this.dispose();
				usergui.runrun();
			}
		});
		btnBack.setBounds(0, 0, 113, 27);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 208, 858, 411);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
