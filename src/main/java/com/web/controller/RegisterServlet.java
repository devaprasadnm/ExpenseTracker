package com.web.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import com.web.bean.Auth;
import com.web.bean.User;
import com.web.dao.UserDao;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String name,password,email,income;
		double salary ;
		
		name =  req.getParameter("name");
		email = req.getParameter("email");
		password = req.getParameter("password");
		income = req.getParameter("income");
		salary = Double.parseDouble(income);
		
		try {
			if(UserDao.isEmailExist(email)) {
				System.out.println("email already exist");
				String error = "Email already exist";
				req.setAttribute("error", error);
				req.getRequestDispatcher("registration.jsp").forward(req, resp);
			}else {
				User obj = new User(name, email, password, salary);
				Auth objAuth = new Auth(name, email, password, "inactive");
				
				try {
					int userStatus = UserDao.saveUser(obj);
					int authStatus = UserDao.saveAuth(objAuth);
					if (userStatus>0 && authStatus>0) {
						System.out.println("user saved to UserTable from Servlet");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
					else {
						System.out.println("user not saved to UserTable from Servlet");
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("user not saved to UserTable from Servlet due to "+e.getMessage());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
