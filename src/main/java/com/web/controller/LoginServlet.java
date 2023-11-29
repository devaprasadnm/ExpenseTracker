package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.web.bean.Auth;
import com.web.bean.Expense;
import com.web.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String email,password;
		email = req.getParameter("email");
		password = req.getParameter("password");
		HttpSession session = req.getSession();
		try {
			if(UserDao.validation(email, password)) {
				Auth obj = UserDao.getUserAuthData(email);
				String name,status;
				name = obj.getName();
				status = obj.getStatus();
				
				ArrayList<Expense> arr = new ArrayList<Expense>();
				
				String[] today = TodayServlet.getToday();
				
				
				arr = UserDao.getExpenseDetailsToday(email,today[0],today[1],today[2]);
				double income = UserDao.getIncome(email);
				double spent = UserDao.getSpent();
				 
				 session.setAttribute("arr", arr);
				 session.setAttribute("name", name);
				 session.setAttribute("status", status);
				 session.setAttribute("email", email);
				 session.setAttribute("income", income);
				 session.setAttribute("spent", spent);
				
//				req.setAttribute("arr", arr);
//				req.setAttribute("name", name);
//				req.setAttribute("status", status);
//				req.setAttribute("email", email);
				
				
				
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}else {
				String error = "Invalid Credentials";
//				req.setAttribute("error", error);
				session.setAttribute("error", error);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		try {
			UserDao.logout(email);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
