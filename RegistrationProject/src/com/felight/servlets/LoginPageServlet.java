package com.felight.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;


/**
 * Servlet implementation class LoginPageServlet
 */
@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname=request.getParameter("fullname");
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager
			         .getConnection("jdbc:mysql://localhost:3306/webpage","root","smart");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		try {
			boolean i=true;
				RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
			    Statement statement = connection.createStatement();
	            ResultSet res = statement.executeQuery("SELECT fullname,email,password,activationkey FROM userdetails");
	   
			while(res.next()) {	 
				if((res.getString(1).equals(fullname))&&res.getString(2).equals(email)&&(res.getString(3).equals(password))) {
					if(res.getInt(4)==1) {
						rd = request.getRequestDispatcher("DashBoard.jsp");
						rd.forward(request, response);
						break;
					}else {
						response.getWriter().println("<font color=red>Please activate your account by the link which is sent to email ID</font>");
						rd.include(request, response);
						i=false;
						break;
					}
					
				}else {
					continue;
				}
			}
			if(i) {
			PrintWriter out = response.getWriter();
					out.println("<font color=red>Please enter valid details</font>");
			rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
