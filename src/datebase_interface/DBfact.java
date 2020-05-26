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
	             //加载数据库com.microsoft.sqlserver.jdbc.SQLServerDriver类
	             Class.forName(driverName);
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	     }
	     public  Connection getConnection() {
	         Connection connection = null;
	         //DatabaseName为数据库的名称
	         String url = "jdbc:sqlserver://localhost:1433; DatabaseName=sbeam";
	         //连接数据库所用的用户名和密码
	         String user = "sa";
	         String password = "sa";
	 
	        try {
	            connection = DriverManager.getConnection(url, user, password);
	            //connection.setAutoCommit(false);// 此处用了则无法插入数据
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    
	     }

}
