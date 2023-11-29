package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class TodayServlet
 */
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			PrintWriter out = resp.getWriter();
			
			Date currentDate = new Date();
            SimpleDateFormat dateformat =new SimpleDateFormat("dd MM yyyy");
            String formatDate = dateformat.format(currentDate);
            String[] dateArray = getToday();
			
			String category,desc,userId;
			double amount;
			
			category = req.getParameter("category");
			desc = req.getParameter("desc");
			amount = Double.parseDouble(req.getParameter("amnt"));
			userId = req.getParameter("useremail");
			
			Expense obj = new Expense(0, userId, category, desc, amount, dateArray[0], dateArray[1], dateArray[2]);
			
			
			try {
				int value = UserDao.saveExpense(obj);
				if(value>0) {
					Auth obj2 = UserDao.getUserAuthData(userId);
					String name,status;
					name = obj2.getName();
					status = obj2.getStatus();
					
					ArrayList<Expense> arr = new ArrayList<Expense>();
					
					String[] today = TodayServlet.getToday();
					
					
					arr = UserDao.getExpenseDetailsToday(userId,today[0],today[1],today[2]);
					
//					req.setAttribute("arr", arr);
//					req.setAttribute("name", name);
//					req.setAttribute("status", status);
//					req.setAttribute("email", userId);
					
					double income = UserDao.getIncome(userId);
					double spent = UserDao.getSpent();
					
					HttpSession session = req.getSession();
			        session.setAttribute("status", status);
			        session.setAttribute("name", name);
			        session.setAttribute("arr", arr);
			        session.setAttribute("email", userId);
			        session.setAttribute("income", income);
					session.setAttribute("spent", spent);
					
					
					resp.sendRedirect("home.jsp");
					
					
//					req.getRequestDispatcher("home.jsp").forward(req, resp);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
	
	}
	
	public static String[] getToday() {
		Date currentDate = new Date();
        SimpleDateFormat dateformat =new SimpleDateFormat("dd MM yyyy");
        String formatDate = dateformat.format(currentDate);
        String[] dateArray = formatDate.split("\\s+");
        return dateArray;
	}


}
