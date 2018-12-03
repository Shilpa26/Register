package com.felight.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.felight.services.Activationmail;
import com.felight.services.EmailValidator;
@WebServlet("/Registration")

public class RegistrationPageServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName=request.getParameter("fullName");
		String email=request.getParameter("email");
		String number=request.getParameter("number");;
		String address=request.getParameter("address");
		String dateOfBirth=request.getParameter("dob");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		EmailValidator valid=new EmailValidator();
		RequestDispatcher rd = request.getRequestDispatcher("RegistrationPage.jsp");
		if(fullName.isEmpty()||email.isEmpty()||number.isEmpty()||address.isEmpty()||dateOfBirth.isEmpty()||password.isEmpty()||gender.isEmpty())
		{
			response.getWriter().println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		}else if(number.length()!=10) {
			response.getWriter().println("<font color=red>Please enter valid 10 digit mobie number</font>");
			rd.include(request, response);
		}else if(!valid.validateEmail(email)) {
			response.getWriter().println("<font color=red>Please enter valid email ID</font>");
			rd.include(request, response);
		}else
		{
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
					int activationcode=(int)(Math.random()*100000)+1000000;
					request.setAttribute("activationcode",activationcode);
					
					PreparedStatement statement=connection.prepareStatement("INSERT INTO userdetails VALUES(?,?,?,?,?,?,?,?,?)");
					statement.setString(1,fullName);
					statement.setString(2,email);
					statement.setString(3,number);
					statement.setString(4,address);
					statement.setString(5,password);
					statement.setString(6,gender);
					statement.setString(7,dateOfBirth);
					statement.setInt(8,activationcode);
					statement.setInt(9,0);
					statement.executeUpdate(); 
					Activationmail.sender(activationcode,email,fullName);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			rd = request.getRequestDispatcher("LoginPage.jsp");
			rd.forward(request, response);
		}
	}

}
