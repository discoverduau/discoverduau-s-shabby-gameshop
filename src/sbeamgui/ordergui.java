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
import javabin.order;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ordergui extends JFrame {

	private JPanel contentPane;
	String ac = fileutil.readTxt();
	dbutil dbu = new dbutil();
	DBfact db = new DBfact();
	Connection con = null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void runrun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ordergui frame = new ordergui();
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
	public ordergui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblNewLabel.setText(ac + "'s history orders");
		lblNewLabel.setBounds(320, 56, 308, 92);
		contentPane.add(lblNewLabel);
		
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordergui.this.dispose();
				usergui.runrun();
			}
		});
		back.setBounds(668, 56, 113, 27);
		contentPane.add(back);
		
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = db.getConnection();
				if(con == null)
					JOptionPane.showMessageDialog(null, "Network Error!");
				else {
					Vector<String> v = new Vector<String>();
					v.add("gamenames");
					v.add("saledate");
					v.add("paymoney");
					DefaultTableModel df = new DefaultTableModel(v,0);
					ArrayList<order> or = new ArrayList<order>();
					or = dbu.getorder(con, ac);
					for(order o : or) {
						String[] line = {o.getGamedlcname(),o.getSaledate(),o.getPaymoney()+""};
						df.addRow(line);
					}
					table.setModel(df);
				}
				
			}
		});
		refresh.setBounds(668, 121, 113, 27);
		contentPane.add(refresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 842, 426);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
