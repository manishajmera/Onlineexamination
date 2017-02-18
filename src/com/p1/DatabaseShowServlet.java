package com.p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseShowServlet
 */
public class DatabaseShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseShowServlet() {
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
		if(request.getHeader("Referer").contains("/student.html")){
			request.setAttribute("con",con);
			RequestDispatcher rd=request.getRequestDispatcher("studentresultdb.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
