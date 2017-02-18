package com.p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Connection con; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet()  {
    	try {
			con=DatabaseConnection.getInstance();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("userID");
		String password=request.getParameter("password");
		try {
			String drl1="select id,password,isadmin from user where id='"+id+"'and password='"+password+"'";
			PreparedStatement pst1=con.prepareStatement(drl1);
			ResultSet rs1=pst1.executeQuery();
			if(rs1.first()){
				if(rs1.getBoolean(3)){
					response.sendRedirect("admin.html");
				}
				else{
					response.sendRedirect("user.html");
				}
			}
			else{
				request.setAttribute("name",new String("UserServlet"));
				request.getRequestDispatcher("error.jsp").forward(request, response);
			 }
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
