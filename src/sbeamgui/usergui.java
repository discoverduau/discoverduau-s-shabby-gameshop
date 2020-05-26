package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.dlclist;
import javabin.fileutil;
import javabin.libray;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class usergui extends JFrame {

	private JPanel contentPane;

	String ac = fileutil.readTxt();
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	private JTable table;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usergui frame = new usergui();
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
	public usergui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setText("Hello, " + ac);
		//lblNewLabel.setText("Hello");
		lblNewLabel.setBounds(14, 13, 279, 92);
		contentPane.add(lblNewLabel);
		con = db.getConnection();
		if(con == null)
			JOptionPane.showMessageDialog(null, "Network Error!");
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setText("Wallet: " + dbu.getwallet(con, ac)+"");
		lblNewLabel_1.setBounds(307, 12, 237, 107);
		contentPane.add(lblNewLabel_1);
		
		JButton gameshop = new JButton("gameshop");
		gameshop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usergui.this.dispose();
				shopgui.runrun();
			}
		});
		gameshop.setBounds(593, 52, 113, 27);
		contentPane.add(gameshop);
		
		JButton lookup = new JButton("lookup orders");
		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usergui.this.dispose();
				ordergui.runrun();
				
			}
		});
		lookup.setBounds(14, 663, 137, 27);
		contentPane.add(lookup);
		
		JButton umod = new JButton("users' mod");
		umod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usergui.this.dispose();
				umodgui.runrun();
			}
		});
		umod.setBounds(671, 663, 113, 27);
		contentPane.add(umod);
		
		JLabel lblNewLabel_2 = new JLabel("library");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(329, 93, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 118, 784, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("dlclist");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(339, 401, 72, 18);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 424, 784, 230);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(con == null) {
					JOptionPane.showMessageDialog(null, "Network Error!");
				}
				else {
					Vector<String> v = new Vector<String>();
					v.add("gamenames");
					v.add("download url");
					DefaultTableModel df = new DefaultTableModel(v,0);
					Vector<String> v1 = new Vector<String>();
					v1.add("dlcnames");
					v1.add("download url");
					DefaultTableModel df1 = new DefaultTableModel(v1,0);
					ArrayList<libray> li = new ArrayList<libray>();
					ArrayList<dlclist> di = new ArrayList<dlclist>();
					li = dbu.getlib(con, ac);
					di = dbu.getdlclist(con, ac);
					for(libray l : li) {
						String[] line = {l.getGamename(),l.getDownloadurl()};
						df.addRow(line);
					}
					for(dlclist d : di) {
						String[] line = {d.getDlcname(),d.getDownloadurl()};
						df1.addRow(line);
					}
					table.setModel(df);
					table_1.setModel(df1);
				}
				
			}
		});
		refresh.setBounds(436, 92, 113, 27);
		contentPane.add(refresh);
	}
}
