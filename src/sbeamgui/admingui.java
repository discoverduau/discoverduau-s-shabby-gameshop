package sbeamgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datebase_interface.DBfact;
import datebase_interface.dbutil;
import javabin.fileutil;
import javabin.top1000;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class admingui extends JFrame {

	private JPanel contentPane;
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
					admingui frame = new admingui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public admingui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		con = db.getConnection();
		if(con == null)
			JOptionPane.showMessageDialog(null, "Network Error!");
		
		JLabel lblAdminastratorsGui = new JLabel("Adminastrator's gui");
		lblAdminastratorsGui.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		lblAdminastratorsGui.setBounds(282, 13, 380, 122);
		contentPane.add(lblAdminastratorsGui);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 204, 877, 364);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> v = new Vector<String>();
				v.add("gamename");
				v.add("totalamount");
				v.add("totalpay");
				DefaultTableModel df = new DefaultTableModel(v,0);
				ArrayList<top1000> tp = new ArrayList<top1000>();
				tp = dbu.getgamesituation(con);
				for(top1000 t :tp) {
					String[] line = {t.getGamename(),t.getTotalamount()+"",t.getTotalpay()+""};
					df.addRow(line);
				}
				table.setModel(df);
			}
		});
		btnRefresh.setBounds(431, 148, 113, 27);
		contentPane.add(btnRefresh);
		
		JLabel lblShowTheTop = new JLabel("show the top 1000 total amount games");
		lblShowTheTop.setBounds(64, 152, 313, 18);
		contentPane.add(lblShowTheTop);
		
		JLabel number = new JLabel();
		number.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		number.setBounds(204, 613, 568, 47);
		number.setText("Recent 30 day's total sale is "+dbu.gettotalpay(con)+"");
		contentPane.add(number);
	}
}
