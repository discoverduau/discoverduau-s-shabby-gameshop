package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.comment;
import javabin.fileutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class comgui extends JFrame {

	private JPanel contentPane;
	private JTextField gamename;
	private JTextField commentid;
	private JTable table;
	
	String ac = fileutil.readTxt();
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
					comgui frame = new comgui();
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
	public comgui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCommentsArea = new JLabel("Welcome to comments area");
		lblCommentsArea.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblCommentsArea.setBounds(289, 36, 434, 101);
		contentPane.add(lblCommentsArea);
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comgui.this.dispose();
				shopgui.runrun();
			}
		});
		btnNewButton.setBounds(0, 0, 113, 27);
		contentPane.add(btnNewButton);
		
		con = db.getConnection();
		if(con == null)
			JOptionPane.showMessageDialog(null, "Network Error!");
		
		JLabel lblGamename = new JLabel("gamename");
		lblGamename.setBounds(102, 147, 72, 18);
		contentPane.add(lblGamename);
		
		gamename = new JTextField();
		gamename.setBounds(207, 144, 161, 24);
		contentPane.add(gamename);
		gamename.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("commentid");
		lblNewLabel.setBounds(46, 618, 72, 18);
		contentPane.add(lblNewLabel);
		
		commentid = new JTextField();
		commentid.setBounds(132, 615, 236, 24);
		contentPane.add(commentid);
		commentid.setColumns(10);
		
		JLabel lblShowYourReview = new JLabel("show your review to some comments(you have to make a comment first)");
		lblShowYourReview.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblShowYourReview.setBounds(58, 567, 789, 35);
		contentPane.add(lblShowYourReview);
		
		JButton btnValuable = new JButton("valuable");
		btnValuable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = commentid.getText();
				dbu.feelvaluable(con, ac, iid);
			}
		});
		btnValuable.setBounds(516, 614, 113, 27);
		contentPane.add(btnValuable);
		
		JButton btnHappay = new JButton("happay");
		btnHappay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = commentid.getText();
				dbu.feelhappy(con, ac, iid);
			}
		});
		btnHappay.setBounds(734, 614, 113, 27);
		contentPane.add(btnHappay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 181, 931, 381);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					String gn = gamename.getText();
					Vector<String> v = new Vector<String>();
					v.add("commentid");
					v.add("id");
					v.add("comtime");
					v.add("comtext");
					v.add("prizenum");
					v.add("happynum");
					DefaultTableModel df = new DefaultTableModel(v,0);
					ArrayList<comment> co = new ArrayList<comment>();
					co = dbu.getcomment(con, dbu.getgameid(con, gn));
					for(comment c: co) {
						String[] line = {c.getCommentid(),c.getId(),c.getComtime(),c.getComtext(),c.getPrizenum()+"",c.getHappynum()+""};
						df.addRow(line);
					}
					table.setModel(df);
				}
				
			}
		});
		btnRefresh.setBounds(472, 143, 113, 27);
		contentPane.add(btnRefresh);
	}
}
