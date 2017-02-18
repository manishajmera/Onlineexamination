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
 * Servlet implementation class StudentUpdationServlet
 */
public class StudentUpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdationServlet() {
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
		if(request.getHeader("Referer").contains("/studentreg.html")){
			String id=request.getParameter("StudentID").toLowerCase();
			String pass=request.getParameter("password");
			try {
				String drl1="select * from student where id='"+id+"'";
				PreparedStatement pst1=con.prepareStatement(drl1);
				ResultSet rs1=pst1.executeQuery();
					if(rs1.first()){
						String drl2="select * from  user where id='"+id+"'";
						PreparedStatement pst2=con.prepareStatement(drl2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs2=pst2.executeQuery();
						if(!rs2.first()){
							String dml1="insert into user values(?,?,?,?,?)";
							PreparedStatement pst3=con.prepareStatement(dml1);
							pst3.setString(1,id);
							pst3.setString(2,rs1.getString(2));
							pst3.setString(3,pass);
							pst3.setBoolean(4, false);
							pst3.setString(5,rs1.getString(3));
							int i=pst3.executeUpdate();
							if(i!=0){
								request.setAttribute("name",new String("UserInsertion"));
								request.getRequestDispatcher("success.jsp").forward(request, response);
							}
						}
						else{
							rs2.updateString(3,pass);
							rs2.updateRow();
							request.setAttribute("name",new String("UserPass"));
							request.getRequestDispatcher("success.jsp").forward(request, response);
							
						}
					}
					else {
						request.setAttribute("name",new String("UserInsertion"));
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getHeader("Referer").contains("/studentdelete.html")){
			String id=request.getParameter("StudentID").toLowerCase();
			try {
				String drl3="select id from user where id='"+id+"'";
				PreparedStatement pst5=con.prepareStatement(drl3);
				ResultSet rs3=pst5.executeQuery();
					if(rs3.first()){
						String dml3="delete from user where id='"+id+"'";
						PreparedStatement pst6=con.prepareStatement(dml3);
						int i=pst6.executeUpdate();
						if(i!=0){
							request.setAttribute("name",new String("StudentDeletion"));
							request.getRequestDispatcher("success.jsp").forward(request, response);
						}
					}
					else {
						request.setAttribute("name",new String("StudentDeletion"));
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
