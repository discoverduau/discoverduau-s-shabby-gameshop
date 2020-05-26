package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.fileutil;
import javabin.wishlist;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class wishlistgui extends JFrame {

	private JPanel contentPane;
	
	String ac = fileutil.readTxt();
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	private JTable table;

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wishlistgui frame = new wishlistgui();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "visible Error!");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public wishlistgui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblNewLabel.setBounds(441, 45, 238, 67);
		lblNewLabel.setText(ac+"'s wishlist");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wishlistgui.this.dispose();
				shopgui.runrun();
			}
		});
		btnNewButton.setBounds(0, 0, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = db.getConnection();
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					Vector<String> v = new Vector<String>();
					v.add("gamename");
					v.add("price");
					v.add("producer");
					v.add("presentage");
					v.add("adddate");
					DefaultTableModel df = new DefaultTableModel(v,0);
					ArrayList<wishlist> wi = new ArrayList<wishlist>();
					wi = dbu.getwishlist(con, ac);
					for(wishlist w: wi) {
						String[] line = {w.getGamename(),w.getPrice()+"",w.getProducer(),w.getPresentage()+"",w.getAdddate()};
						df.addRow(line);
					}
					table.setModel(df);
				}
				
			}
		});
		btnRefresh.setBounds(670, 109, 113, 27);
		contentPane.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 177, 989, 430);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
