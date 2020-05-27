package datebase_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javabin.comment;
import javabin.dlclist;
import javabin.gameshop;
import javabin.libray;
import javabin.order;
import javabin.top1000;
import javabin.umod;
import javabin.users;
import javabin.wishlist;



public class dbutil {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*public ArrayList<users> qurryusersall(Connection con){
		ArrayList<users> arlist = new ArrayList<users>();
		
			try {
				ps = con.prepareStatement("select * from users ");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				users ar = new users();
				ar.setId(rs.getString(1));
				ar.setPassword(rs.getString(2));
				ar.setSex(rs.getString(3));
				ar.setCountry(rs.getString(4));
				ar.setBirthday(rs.getString(5));
				ar.setWallet(rs.getFloat(6));
				ar.setUserimage(rs.getString(7));
				arlist.add(ar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arlist;
	}*/
	public String login(Connection con, String Ac){
		String Pa = new String();
		try {
			ps = con.prepareStatement("select passwords from users where id = '" + Ac + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				Pa = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return Pa;
	}
	
	public int whetherhasid(Connection con, String ac){
		int i = 0;
		try {
			ps = con.prepareStatement("select count(id) from users group by id having id = '" + ac + "'" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
	public void insertusers(Connection con,String id,String password,String sex,String country,String birthday) {
		int i = 0;
		try {
			ps = con.prepareStatement("insert into users values('"+ id +"', " + "'"+password+"', '"+sex+"', '"+country+"', '"+birthday+"', '', '')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change1 fail!");
		}
		if(i == 1)
			JOptionPane.showMessageDialog(null, "Register successfully!");
		else
			JOptionPane.showMessageDialog(null, "Register fail!");
		
	}
	
	public float getwallet(Connection con, String ac) {
		float i = 0;
		try {
			ps = con.prepareStatement("select wallet from users where id = '" + ac + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
	public ArrayList<libray> getlib(Connection con,String ac){
		ArrayList<libray> arlist = new ArrayList<libray>();
		try {
			ps = con.prepareStatement("select gamename,downloadweb from gameshop,libray where libray.gameid = gameshop.gamedlcid and libray.id = '" + ac +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				libray li = new libray();
				li.setGamename(rs.getString(1));
				li.setDownloadurl(rs.getString(2));
				arlist.add(li);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<dlclist> getdlclist(Connection con,String ac){
		ArrayList<dlclist> arlist = new ArrayList<dlclist>();
		try {
			ps = con.prepareStatement("select gamename,downloadweb from gameshop,dlclist where dlclist.dlcid = gameshop.gamedlcid and dlclist.id = '" + ac +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				dlclist di = new dlclist();
				di.setDlcname(rs.getString(1));
				di.setDownloadurl(rs.getString(2));
				arlist.add(di);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<order> getorder(Connection con,String ac){
		ArrayList<order> arlist = new ArrayList<order>();
		try {
			ps = con.prepareStatement("select gamename,saledate,paymoney from gameshop,orders where gameshop.gamedlcid = orders.gamedlcid and orders.id = '" + ac +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				order o = new order();
				o.setGamedlcname(rs.getString(1));
				o.setSaledate(rs.getString(2));
				o.setPaymoney(rs.getFloat(3));
				arlist.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<umod> getumodbyname(Connection con,String condition){
		ArrayList<umod> arlist = new ArrayList<umod>();
		try {
			ps = con.prepareStatement("select modname,id,gamename,decribe,umod.downloadweb from umod,gameshop where umod.gameid = gameshop.gamedlcid and umod.modname = '" + condition +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				umod u = new umod();
				u.setModname(rs.getString(1));
				u.setId(rs.getString(2));
				u.setGamename(rs.getString(3));
				u.setDescribe(rs.getString(4));
				u.setDownloadweb(rs.getString(5));
				arlist.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<umod> getumodbygame(Connection con,String condition){
		ArrayList<umod> arlist = new ArrayList<umod>();
		try {
			ps = con.prepareStatement("select modname,id,gamename,decribe,umod.downloadweb from umod,gameshop where umod.gameid = gameshop.gamedlcid and gameshop.gamename = '" + condition +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				umod u = new umod();
				u.setModname(rs.getString(1));
				u.setId(rs.getString(2));
				u.setGamename(rs.getString(3));
				u.setDescribe(rs.getString(4));
				u.setDownloadweb(rs.getString(5));
				arlist.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<umod> getumod(Connection con){
		ArrayList<umod> arlist = new ArrayList<umod>();
		try {
			ps = con.prepareStatement("select modname,id,gamename,decribe,umod.downloadweb from umod,gameshop where umod.gameid = gameshop.gamedlcid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				umod u = new umod();
				u.setModname(rs.getString(1));
				u.setId(rs.getString(2));
				u.setGamename(rs.getString(3));
				u.setDescribe(rs.getString(4));
				u.setDownloadweb(rs.getString(5));
				arlist.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	public void insertmod(Connection con,String ac,String modname,String describe,String downloadweb,String gamename,String makedate) {
		try {
			ps = con.prepareStatement("insert into umod values('"+ac+makedate+"','"+makedate+"','"+ac+"',(select gamedlcid from gameshop where gamename ='"+gamename+"'),'"+describe+"','"+modname+"','','"+downloadweb+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			int i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		
	}
	
	public int whethermod(Connection con,String ac,String makedate) {
		int i = 0;
		try {
			ps = con.prepareStatement("select count(*) from umod where umod.modid = '" + ac + makedate + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
	public ArrayList<gameshop> getgameall(Connection con){
		ArrayList<gameshop> arlist = new ArrayList<gameshop>();
		try {
			ps = con.prepareStatement("select gamename,systemneed,evaluation,price,agelim,proname,presentage,class,gameshop.introduction,downloadweb,ondate from gameshop,producers where gameshop.proid = producers.proid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				gameshop g = new gameshop();
				g.setGamename(rs.getString(1));
				g.setSystemneed(rs.getString(2));
				g.setEvaluation(rs.getString(3));
				g.setPrice(rs.getInt(4));
				g.setAgelim(rs.getInt(5));
				g.setProducer(rs.getString(6));
				g.setPresentage(rs.getFloat(7));
				g.setClasses(rs.getString(8));
				g.setIntroduction(rs.getString(9));
				g.setDownloadweb(rs.getString(10));
				g.setOndate(rs.getString(11));
				arlist.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<gameshop> getgamebyname(Connection con, String name){
		ArrayList<gameshop> arlist = new ArrayList<gameshop>();
		try {
			ps = con.prepareStatement("select gamename,systemneed,evaluation,price,agelim,proname,presentage,class,gameshop.introduction,downloadweb,ondate from gameshop,producers where gameshop.proid = producers.proid and gameshop.gamename = '" + name + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				gameshop g = new gameshop();
				g.setGamename(rs.getString(1));
				g.setSystemneed(rs.getString(2));
				g.setEvaluation(rs.getString(3));
				g.setPrice(rs.getInt(4));
				g.setAgelim(rs.getInt(5));
				g.setProducer(rs.getString(6));
				g.setPresentage(rs.getFloat(7));
				g.setClasses(rs.getString(8));
				g.setIntroduction(rs.getString(9));
				g.setDownloadweb(rs.getString(10));
				g.setOndate(rs.getString(11));
				arlist.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
		
	}
	
	public ArrayList<gameshop> getgamebynew(Connection con){
		ArrayList<gameshop> arlist = new ArrayList<gameshop>();
		try {
			ps = con.prepareStatement("select gamename,systemneed,evaluation,price,agelim,proname,presentage,class,gameshop.introduction,downloadweb,ondate from gameshop,producers where gameshop.proid = producers.proid order by gameshop.ondate desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				gameshop g = new gameshop();
				g.setGamename(rs.getString(1));
				g.setSystemneed(rs.getString(2));
				g.setEvaluation(rs.getString(3));
				g.setPrice(rs.getInt(4));
				g.setAgelim(rs.getInt(5));
				g.setProducer(rs.getString(6));
				g.setPresentage(rs.getFloat(7));
				g.setClasses(rs.getString(8));
				g.setIntroduction(rs.getString(9));
				g.setDownloadweb(rs.getString(10));
				g.setOndate(rs.getString(11));
				arlist.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public ArrayList<gameshop> gettop10game(Connection con){
		ArrayList<gameshop> arlist = new ArrayList<gameshop>();
		try {
			ps = con.prepareStatement("select top 10  gamename,systemneed,evaluation,price,agelim,proname,presentage,class,gameshop.introduction,downloadweb,ondate  from orders,gameshop,producers where DATEDIFF(day,orders.saledate,GETDATE()) <= 30 and gameshop.gamedlcid = orders.gamedlcid and gameshop.proid = producers.proid group by orders.gamedlcid,gameshop.gamename,systemneed,evaluation,price,agelim,proname,presentage,class,gameshop.introduction,downloadweb,ondate order by count(orders.gamedlcid) desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				gameshop g = new gameshop();
				g.setGamename(rs.getString(1));
				g.setSystemneed(rs.getString(2));
				g.setEvaluation(rs.getString(3));
				g.setPrice(rs.getInt(4));
				g.setAgelim(rs.getInt(5));
				g.setProducer(rs.getString(6));
				g.setPresentage(rs.getFloat(7));
				g.setClasses(rs.getString(8));
				g.setIntroduction(rs.getString(9));
				g.setDownloadweb(rs.getString(10));
				g.setOndate(rs.getString(11));
				arlist.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public String getgameid(Connection con,String gamename) {
		String gameid = new String();
		try {
			ps = con.prepareStatement("select gameshop.gamedlcid from gameshop where gameshop.gamename = '" + gamename + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				gameid = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return gameid;
	}
	
	public void buy(Connection con,String gamedlcid,String ac) {
		int i = 0;
		try {
			ps = con.prepareStatement("exec Buy '" + gamedlcid + "','" + ac +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i  = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		if(i == -1)
			JOptionPane.showMessageDialog(null, "No enough money or age limit or has exist in your libray!");
		else {
			JOptionPane.showMessageDialog(null, "Buy successfully!");
		}
	}
	
	public int whetherinlib(Connection con, String gamename,String ac) {
		int i = 0;
		try {
			ps = con.prepareStatement("select count(gamedlcid) from orders where orders.id = '"+ ac +"' group by gamedlcid having orders.gamedlcid = (select gamedlcid from gameshop where gameshop.gamename = '"+gamename+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
	public int whetherinwish(Connection con,String gamename,String ac) {
		int i = 0;
		try {
			ps = con.prepareStatement("select count(gamedlcid) from wishlist where wishlist.id = '"+ac+"' group by gamedlcid having wishlist.gamedlcid = (select gamedlcid from gameshop where gameshop.gamename = '"+gamename+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
	public void insertwish(Connection con,String ac,String gamedlcid) {
		int i = 0;
		try {
			ps = con.prepareStatement("insert into wishlist values('"+gamedlcid+ac+"','"+ac+"','"+gamedlcid+"',getdate())");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		if(i == 1)
			JOptionPane.showMessageDialog(null, "add to wishlist successfully!");
	}
	
	public void insertcom(Connection con,String ac,String gamedlcid,String comment,String evaluation) {
		int i = 0;
		try {
			ps = con.prepareStatement("exec makecomment '"+gamedlcid+"','"+comment+"','"+ac+"','"+evaluation+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		if(i == 1)
			JOptionPane.showMessageDialog(null, "make comment successfully!");
		else
			JOptionPane.showMessageDialog(null, "make comment fail!");
	}
	
	public ArrayList<wishlist> getwishlist(Connection con,String ac){
		ArrayList<wishlist> arlist = new ArrayList<wishlist>();
		try {
			ps = con.prepareStatement("select gamename,price,proname,presentage,adddate from gameshop,wishlist,producers where wishlist.gamedlcid = gameshop.gamedlcid and gameshop.proid = producers.proid and wishlist.id = '"+ac+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				wishlist w = new wishlist();
				w.setGamename(rs.getString(1));
				w.setPrice(rs.getInt(2));
				w.setProducer(rs.getString(3));
				w.setPresentage(rs.getFloat(4));
				w.setAdddate(rs.getString(5));
				arlist.add(w);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		
		return arlist;
	}
	
	public ArrayList<comment> getcomment(Connection con,String gamedlcid){
		ArrayList<comment> arlist =  new ArrayList<comment>();
		try {
			ps = con.prepareStatement("select commentid,id,comtime,comtext,prizenum,happynum from comment,gameshop where gameshop.gamedlcid = comment.gamedlcid and comment.gamedlcid = '"+gamedlcid+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				comment c = new comment();
				c.setCommentid(rs.getString(1));
				c.setId(rs.getString(2));
				c.setComtime(rs.getString(3));
				c.setComtext(rs.getString(4));
				c.setPrizenum(rs.getInt(5));
				c.setHappynum(rs.getInt(6));
				arlist.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		
		return arlist;
	}
	
	public void feelhappy(Connection con, String ac, String commentid) {
		int i = 0;
		try {
			ps = con.prepareStatement("exec happy '"+commentid+"','"+ac+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		if(i == 1)
			JOptionPane.showMessageDialog(null, "review successfully!");
		else
			JOptionPane.showMessageDialog(null, "review fail!");
	}
	
	public void feelvaluable(Connection con, String ac, String commentid) {
		int i = 0;
		try {
			ps = con.prepareStatement("exec valuable '"+commentid+"','"+ac+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "change fail!");
		}
		if(i == 1)
			JOptionPane.showMessageDialog(null, "review successfully!");
		else
			JOptionPane.showMessageDialog(null, "review fail!");
	}
	
	public ArrayList<top1000> getgamesituation(Connection con){
		ArrayList<top1000> arlist = new ArrayList<top1000>();
		try {
			ps = con.prepareStatement("select * from gamesituation");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				top1000 t = new top1000();
				t.setGamename(rs.getString(1));
				t.setTotalamount(rs.getInt(2));
				t.setTotalpay(rs.getInt(3));
				arlist.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return arlist;
	}
	
	public int gettotalpay(Connection con) {
		int i = 0;
		try {
			ps = con.prepareStatement("select * from thirtydaystotalsale");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return i;
	}
	
		public String getuimage(Connection con,String ac) {
		String im = new String();
		try {
			ps = con.prepareStatement("select usersimage from users where id = '"+ac+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		try {
			while(rs.next()) {
				im = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "qurry fail!");
		}
		return im;
	}
}


