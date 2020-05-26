package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.fileutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class upmodgui extends JFrame {

	private JPanel contentPane;
	private JTextField gamename;
	private JTextField modname;
	private JTextField describe;
	private JTextField downloadweb;
	
	String ac = fileutil.readTxt();
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	private JTextField makedate;

	/**
	 * Launch the application.
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					upmodgui frame = new upmodgui();
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
	public upmodgui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Share your personal mod");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(218, 13, 385, 179);
		contentPane.add(lblNewLabel);
		
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upmodgui.this.dispose();
				umodgui.runrun();
			}
		});
		back.setBounds(0, 0, 113, 27);
		contentPane.add(back);
		
		JLabel lblGamename = new JLabel("gamename");
		lblGamename.setFont(new Font("宋体", Font.PLAIN, 20));
		lblGamename.setBounds(132, 191, 91, 27);
		contentPane.add(lblGamename);
		
		JLabel lblModname = new JLabel("modname");
		lblModname.setFont(new Font("宋体", Font.PLAIN, 20));
		lblModname.setBounds(132, 250, 72, 18);
		contentPane.add(lblModname);
		
		JLabel lblDescirbe = new JLabel("describe");
		lblDescirbe.setFont(new Font("宋体", Font.PLAIN, 20));
		lblDescirbe.setBounds(132, 308, 91, 18);
		contentPane.add(lblDescirbe);
		
		JLabel lblDownloadweb = new JLabel("downloadweb");
		lblDownloadweb.setFont(new Font("宋体", Font.PLAIN, 20));
		lblDownloadweb.setBounds(132, 361, 121, 27);
		contentPane.add(lblDownloadweb);
		
		gamename = new JTextField();
		gamename.setBounds(317, 194, 343, 24);
		contentPane.add(gamename);
		gamename.setColumns(10);
		
		modname = new JTextField();
		modname.setBounds(317, 249, 343, 24);
		contentPane.add(modname);
		modname.setColumns(10);
		
		describe = new JTextField();
		describe.setBounds(317, 307, 343, 24);
		contentPane.add(describe);
		describe.setColumns(10);
		
		downloadweb = new JTextField();
		downloadweb.setBounds(317, 364, 343, 24);
		contentPane.add(downloadweb);
		downloadweb.setColumns(10);
		
		JButton upload = new JButton("upload");
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = db.getConnection();
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					String md = makedate.getText();//xxxx-xx-xx
					String mn = modname.getText();
					String d = describe.getText();
					String down = downloadweb.getText();
					String gn = gamename.getText();
					if(md.length() == 0 || mn.length() == 0 || d.length() == 0 || down.length() == 0 || gn.length() == 0) {
						JOptionPane.showMessageDialog(null, "All information must be written!");
					}
					else {
						dbu.insertmod(con, ac, mn, d, down, gn, md);
						if(dbu.whethermod(con, ac, md) == 1) {
							JOptionPane.showMessageDialog(null, "Upload successfully!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Change fail!");
						}
					}
					
				}
				
			}
		});
		upload.setBounds(399, 482, 113, 27);
		contentPane.add(upload);
		
		JLabel lblNewLabel_1 = new JLabel("makedate(xxxx-xx-xx)");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(132, 414, 186, 18);
		contentPane.add(lblNewLabel_1);
		
		makedate = new JTextField();
		makedate.setBounds(317, 413, 343, 24);
		contentPane.add(makedate);
		makedate.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Attention: one person can just upload one mod each day! The format must be correct! Or upload fail!");
		lblNewLabel_2.setBounds(14, 147, 796, 31);
		contentPane.add(lblNewLabel_2);
	}
}
