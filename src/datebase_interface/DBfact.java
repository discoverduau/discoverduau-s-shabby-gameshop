package datebase_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabin.users;

public class DBfact {
	public DBfact() {
		
	}
	PreparedStatement ps = null;
	Connection con = null;
	     ResultSet rs = null;
	     static {       
	         String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	         try {
	             //�������ݿ�com.microsoft.sqlserver.jdbc.SQLServerDriver��
	             Class.forName(driverName);
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	     }
	     public  Connection getConnection() {
	         Connection connection = null;
	         //DatabaseNameΪ���ݿ������
	         String url = "jdbc:sqlserver://localhost:1433; DatabaseName=sbeam";
	         //�������ݿ����õ��û���������
	         String user = "sa";
	         String password = "sa";
	 
	        try {
	            connection = DriverManager.getConnection(url, user, password);
	            //connection.setAutoCommit(false);// �˴��������޷���������
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    
	     }

}
