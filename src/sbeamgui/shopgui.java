package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import javabin.fileutil;
import javabin.gameshop;
import javabin.shop;
import javabin.evaluation;

public class shopgui extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JTextField gameid;
	private JTable table;
	private JTextField comment;
	
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
					shopgui frame = new shopgui();
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
	public shopgui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1098, 715);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		con = db.getConnection();
		if(con == null)
			JOptionPane.showMessageDialog(null, "Network Error!");
		
		JLabel lblNewLabel = new JLabel("Welcome to gameshop");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblNewLabel.setBounds(233, 13, 457, 65);
		contentPane.add(lblNewLabel);
		
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopgui.this.dispose();
				usergui.runrun();
			}
		});
		back.setBounds(0, 0, 113, 27);
		contentPane.add(back);
		
		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(shop.values()));
		type.setBounds(152, 91, 98, 24);
		contentPane.add(type);
		
		search = new JTextField();
		search.setBounds(324, 91, 143, 24);
		contentPane.add(search);
		search.setColumns(10);
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					Vector<String> v = new Vector<String>();
					v.add("gamename");
					v.add("systemneed");
					v.add("evaluation");
					v.add("price");
					v.add("agelim");
					v.add("producer");
					v.add("presentage");
					v.add("classes");
					v.add("introduction");
					v.add("downloadweb");
					v.add("ondate");
					DefaultTableModel df = new DefaultTableModel(v,0);
					ArrayList<gameshop> gs = new ArrayList<gameshop>();
					if(type.getSelectedItem().toString().equals("all")) {
						gs = dbu.getgameall(con);
					}
					else if(type.getSelectedItem().toString().equals("gamename")){
						gs = dbu.getgamebyname(con,search.getText() );
					}
					else if(type.getSelectedItem().toString().equals("newgames")) {
						gs = dbu.getgamebynew(con);
					}
					else if(type.getSelectedItem().toString().equals("top10games")) {
						gs = dbu.gettop10game(con);
					}
					for(gameshop g: gs) {
						String[] line = {g.getGamename(),g.getSystemneed(),g.getEvaluation(),g.getPrice()+"",g.getAgelim()+"",g.getProducer(),g.getPresentage()+"",g.getClasses(),g.getIntroduction(),g.getDownloadweb(),g.getOndate()};
						df.addRow(line);
					}
					table.setModel(df);
				}
			}
		});
		btnNewButton.setBounds(577, 91, 113, 27);
		contentPane.add(btnNewButton);
		
		JComboBox eva = new JComboBox();
		eva.setModel(new DefaultComboBoxModel(evaluation.values()));
		eva.setBounds(621, 615, 87, 24);
		contentPane.add(eva);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 1080, 350);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		gameid = new JTextField();
		gameid.setBounds(446, 576, 113, 24);
		contentPane.add(gameid);
		gameid.setColumns(10);
		
		JButton Buy = new JButton("Buy");
		Buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gamename = gameid.getText();
				dbu.buy(con, dbu.getgameid(con, gamename), ac);
			}
		});
		Buy.setBounds(595, 575, 113, 27);
		contentPane.add(Buy);
		
		JLabel lblBuyTheGame = new JLabel("buy the game(input gamename)");
		lblBuyTheGame.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblBuyTheGame.setBounds(82, 579, 273, 18);
		contentPane.add(lblBuyTheGame);
		
		JLabel lblSearchType = new JLabel("search type");
		lblSearchType.setBounds(14, 94, 99, 18);
		contentPane.add(lblSearchType);
		
		comment = new JTextField();
		comment.setBounds(197, 613, 286, 24);
		contentPane.add(comment);
		comment.setColumns(10);
		
		JLabel lblMakeComment = new JLabel("make comment");
		lblMakeComment.setBounds(82, 616, 143, 18);
		contentPane.add(lblMakeComment);
		
		JButton btnWishlist = new JButton("wishlist");
		btnWishlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopgui.this.dispose();
				wishlistgui.runrun();
				
			}
		});
		btnWishlist.setBounds(967, 0, 113, 27);
		contentPane.add(btnWishlist);
		
		JButton btnCommit = new JButton("commit");
		btnCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c = comment.getText();
				String ev = new String();
				String gamename = gameid.getText();
				if(eva.getSelectedItem().toString().equals("good"))
					ev = "good";
				else if(eva.getSelectedItem().toString().equals("soso"))
					ev = "soso";
				else if(eva.getSelectedItem().toString().equals("bad"))
					ev = "bad";
				dbu.insertcom( con, ac,dbu.getgameid(con, gamename), c, ev);
			}
		});
		btnCommit.setBounds(759, 612, 113, 27);
		contentPane.add(btnCommit);
		
		JButton btnComments = new JButton("comments");
		btnComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopgui.this.dispose();
				comgui.runrun();
			}
		});
		btnComments.setBounds(967, 612, 113, 27);
		contentPane.add(btnComments);
		
		JButton btnNewButton_1 = new JButton("addwishlist");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gamename = gameid.getText();
				int a = dbu.whetherinlib(con, gamename, ac);
				int b = dbu.whetherinwish(con, gamename, ac);
				if(a == 0 && b == 0) {
					dbu.insertwish(con, ac, dbu.getgameid(con, gamename));
				}
				else {
					JOptionPane.showMessageDialog(null, "Exist in orders or wishlist!");
				}
			}
		});
		btnNewButton_1.setBounds(749, 575, 143, 27);
		contentPane.add(btnNewButton_1);
		
		JLabel lblAttentionYouCan = new JLabel("Attention: You can make a comment if you buy the game ");
		lblAttentionYouCan.setBounds(577, 58, 473, 18);
		contentPane.add(lblAttentionYouCan);
		

		
		JLabel lblEvaluation = new JLabel("evaluation");
		lblEvaluation.setBounds(505, 616, 102, 18);
		contentPane.add(lblEvaluation);
	}
}
