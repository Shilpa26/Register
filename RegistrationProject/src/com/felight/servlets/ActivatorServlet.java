

package com.felight.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActivatorServlet 
 * 
 */
public class ActivatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int activationcode=Integer.parseInt(request.getParameter("activationcode"));
	Connection connection=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager
		         .getConnection("jdbc:mysql://localhost:3306/webpage","root","smart");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 Statement statement;
	try {
		statement = connection.createStatement();
		ResultSet res = statement.executeQuery("SELECT activationcode,activationkey FROM userdetails");
		while(res.next()) {
			if(res.getInt(1)==activationcode&&res.getInt(2)==0) {
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE userdetails SET activationkey = 1 WHERE activationcode='"+activationcode+"'");
			}else {
				continue;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
	rd.forward(request, response);
     
	
	}

}
