package com.web.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import com.web.bean.Auth;
import com.web.bean.Expense;
import com.web.bean.User;

public class UserDao {

	public static Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		final String url = "jdbc:derby:C:\\Users\\devap\\MyDB;create=true";
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Db connected");
		} catch (SQLException e) {
			System.out.println("Db not connected due to : "+e.getMessage());
		}
		
		return conn;
	}
	
	public static int saveUser(User obj) throws SQLException, ClassNotFoundException {
		int value = 0;
		Connection conn = getConnection();
		String sql = "insert into usertable values(?,?,?,?)";
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		preStmnt.setString(1, obj.getName());
		preStmnt.setString(2, obj.getEmail());
		preStmnt.setString(3, obj.getPassword());
		preStmnt.setDouble(4, obj.getSalary());
		
		try {
			value = preStmnt.executeUpdate();
			System.out.println("User added to usertable");
		} catch (Exception e) {
			System.out.println("User not added to usertable"+e.getMessage());
		}
				
		return value;
	}
	
	public static int saveAuth(Auth obj) throws ClassNotFoundException, SQLException {
		int value = 0;
		Connection conn = getConnection();
		String sql = "insert into authtable values(?,?,?,?)";
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		preStmnt.setString(1, obj.getName());
		preStmnt.setString(2, obj.getEmail());
		preStmnt.setString(3, obj.getPassword());
		preStmnt.setString(4, obj.getStatus());
		
		
		try {
			value = preStmnt.executeUpdate();
			System.out.println("User added to authtable");
		} catch (Exception e) {
			System.out.println("User not added to authtable "+e.getMessage());
		}
				
		return value;
	}
	
	public static boolean isEmailExist(String email) throws ClassNotFoundException, SQLException {
		boolean value = false;
		Connection conn = getConnection();		
		String sql = "select * from authtable where email=?";
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		
		preStmnt.setString(1, email);
		
		ResultSet rs = preStmnt.executeQuery();
		
		value =rs.next();
		
		return value;
	}
	
	public static boolean validation(String email,String password) throws ClassNotFoundException, SQLException {
		boolean value = false;
		Connection conn = getConnection();		
		String sql = "select * from authtable where email=? and password=?";
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		
		preStmnt.setString(1, email);
		preStmnt.setString(2, password);
		
		try {
			ResultSet rs = preStmnt.executeQuery();
			value =rs.next();
			System.out.println("fst query execute");
		} catch (Exception e) {
			System.out.println("error from fst query "+e.getMessage());
		}
		
		
		
		
		if(value) {
			String sql2 = "update authtable set status=? where email=?",status="active";
			
			PreparedStatement preStmnt2 = conn.prepareStatement(sql2);
			
			preStmnt2.setString(1,status );
			preStmnt2.setString(2,email );
			System.out.println("2nd q");
			try {
				preStmnt2.execute();
			} catch (Exception e) {
				System.out.println("error from 2nd query "+e.getMessage());
			}
			
			
			
		}
		System.out.println("success "+value);
		return value;
	}
	
	public static Auth getUserAuthData(String email) throws ClassNotFoundException, SQLException {
		Auth obj=null ;
		String name,status;
		
		Connection conn = getConnection();		
		String sql = "select * from authtable where email=?";
		
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		
		preStmnt.setString(1, email);

		
		ResultSet rs = preStmnt.executeQuery();
		
		while(rs.next()) {
			name = rs.getString(1);
			status = rs.getString(4);
			obj = new Auth(name, email, "", status);
		}
		
		return obj;
	}
	
	public static void logout(String email) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();		
		
		String sql = "update authtable set status=? where email=?",status="inactive";
		
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		
		preStmnt.setString(1,status );
		preStmnt.setString(2,email );
		preStmnt.execute();
		
		
		
		}
	
	public static ArrayList<Expense> getExpenseDetailsToday(String userId,String day,String month,String year) throws ClassNotFoundException, SQLException{
		ArrayList<Expense> arr = new ArrayList<Expense>();
		Connection conn = getConnection();
		
		String sql = "select * from expenses where userid = ? and day = ? and month = ? and years = ? order by id desc";
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		preStmnt.setString(1, userId);
		preStmnt.setString(2, day);
		preStmnt.setString(3, month);
		preStmnt.setString(4, year);
		ResultSet rs = preStmnt.executeQuery();
		int id;
		String dd,mm,yy,cat,desc,uid;
		double amnt;
		Expense obj;
		
		while(rs.next()) {
			id = rs.getInt(1);
			uid = rs.getString(2);
			cat = rs.getString(3);
			desc = rs.getString(4);
			amnt = rs.getDouble(5);
			dd = rs.getString(6);
			mm = rs.getString(7);
			yy = rs.getString(8);
			
			obj = new Expense(id, userId, cat, desc, amnt, day, month, year);
			
			arr.add(obj);
		}
		
		return arr;
	}
	
	
	public static int saveExpense(Expense obj) throws ClassNotFoundException, SQLException {
		int value = 0;
		
		Connection conn = getConnection();
		String sql = "insert into expenses(userid,category,description,amount,day,month,years) values (?,?,?,?,?,?,?)";
		
		PreparedStatement preStmnt = conn.prepareStatement(sql);
		preStmnt.setString(1, obj.getUserid());
		preStmnt.setString(2, obj.getCategory());
		preStmnt.setString(3, obj.getDescription());
		preStmnt.setDouble(4, obj.getAmount());
		preStmnt.setString(5, obj.getDay());
		preStmnt.setString(6, obj.getMonth());
		preStmnt.setString(7, obj.getYear());
		
		value = preStmnt.executeUpdate();		
		return value;
	}
	
	public static double getIncome(String email) throws ClassNotFoundException, SQLException {
		double income = 0.0;
		
		Connection conn = getConnection();
		String sql = "select income from userTable where email=?";
		
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, email);
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()) {
			income = rs.getDouble(1);
		}
		return income;
	}
	
	public static double getSpent() throws ClassNotFoundException, SQLException {
		double spent = 0.0;
		
		Connection conn = getConnection();
		String sql = "select sum(amount) from expenses";
		
		PreparedStatement pre = conn.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()) {
			spent = rs.getDouble(1);
		}
		System.out.println("spent = ");
		System.out.print(spent);
		return spent;
	}

	
}
