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
 * Servlet implementation class QuestionBankUpdationServlet
 */
public class QuestionBankUpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionBankUpdationServlet() {
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
		Integer id=Integer.parseInt(request.getParameter("TestID"));
		if(request.getHeader("Referer").contains("/newpaperindex.html")){
			String name=request.getParameter("name");
			try {
						String drl1="select id from test where id="+id;
						String dml1="insert into test values(?,?)";
						PreparedStatement pst1=con.prepareStatement(drl1);
						PreparedStatement pst2=con.prepareStatement(dml1);
						ResultSet rs1=pst1.executeQuery();
						if(!rs1.first()){
							pst2.setInt(1, id);
							pst2.setString(2,name);
							int i=pst2.executeUpdate();
							if(i!=0){
								request.setAttribute("name",new String("PaperInsertion"));
								request.getRequestDispatcher("success.jsp").forward(request, response);
							}
						}
						else{
							request.setAttribute("name",new String("IDFound"));
							request.getRequestDispatcher("error.jsp").forward(request, response);
						}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getHeader("Referer").contains("/updatepaperindex.html")){
			if(request.getParameter("act").equals("delete paper")){
				try {
					String drl2="select id from test where id="+id;
					PreparedStatement pst3=con.prepareStatement(drl2);
					ResultSet rs2=pst3.executeQuery();
					if(rs2.first()){
						String dml2="delete from test where id="+id;
						PreparedStatement pst4=con.prepareStatement(dml2);
						int i=pst4.executeUpdate();
						if(i!=0){
							request.setAttribute("name",new String("PaperDeletion"));
							request.getRequestDispatcher("success.jsp").forward(request, response);
						}
					}
					else{
						request.setAttribute("name",new String("TestIDNotFound"));
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(request.getParameter("act").equals("update paper")) {
				try {
					String drl3="select id from test where id="+id;
					PreparedStatement pst5= con.prepareStatement(drl3);
					ResultSet rs3=pst5.executeQuery();
					if(rs3.first()){
						request.setAttribute("TestID",id);
						request.getRequestDispatcher("updatepaper.jsp").forward(request, response);
					}
					else{
						request.setAttribute("name",new String("TestIDNotFound"));
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(request.getParameter("act").equals("insert question in paper")){
				try {
					String drl4="select id from test where id="+id;
					PreparedStatement pst6= con.prepareStatement(drl4);
					ResultSet rs4=pst6.executeQuery();
					if(rs4.first()){
						request.getRequestDispatcher("questioninsertion.jsp").forward(request, response);
					}
					else{
						request.setAttribute("name",new String("TestIDNotFound"));
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				}
			}
		else if(request.getParameter("page").equals("questioninsertion.jsp")){
			try {
				String dml3="insert into questions(question,option1,option2,option3,option4,ans,testid) values(?,?,?,?,?,?,?)";
				PreparedStatement pst7=con.prepareStatement(dml3);
				int testid=Integer.parseInt(request.getParameter("TestID"));
				String question=request.getParameter("question");
				pst7.setString(1,request.getParameter("question"));
				pst7.setString(2,request.getParameter("option1"));
				pst7.setString(3,request.getParameter("option2"));
				pst7.setString(4,request.getParameter("option3"));
				pst7.setString(5,request.getParameter("option4"));
				pst7.setString(6,request.getParameter("ans"));
				pst7.setInt(7,Integer.parseInt(request.getParameter("TestID")));
				String drl5="select question from questions where question='"+question+"'"+"and testid="+testid;
				PreparedStatement pst8=con.prepareStatement(drl5);
				ResultSet rs5=pst8.executeQuery();
				if(!rs5.first()){
					int i=pst7.executeUpdate();
					if(i!=0){
						request.setAttribute("name",new String("QuestionInsertion"));
						request.getRequestDispatcher("success.jsp").forward(request, response);
					}
				}
				else{
					request.setAttribute("name",new String("QuestionFound"));
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("page").equals("updatepaper.jsp")){
			if(request.getParameter("act").equals("click")){
				try {
					String drl6="select * from questions where testid="+id;
					PreparedStatement pst8=con.prepareStatement(drl6);
					ResultSet rs6=pst8.executeQuery();
					int row=Integer.parseInt(request.getParameter("QNO"));
					if(rs6.absolute(row)){
						request.setAttribute("QID",rs6.getInt(1));
						request.setAttribute("question",rs6.getString(2));
						request.setAttribute("option1",rs6.getString(3));
						request.setAttribute("option2",rs6.getString(4));
						request.setAttribute("option3",rs6.getString(5));
						request.setAttribute("option4",rs6.getString(6));
						request.setAttribute("ans",rs6.getString(7));
						request.getRequestDispatcher("updatepaper.jsp").forward(request, response);
					}
					else{
						request.setAttribute("name","RowNotFound");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			 if(request.getParameter("act").equals("update")){
				int qid=Integer.parseInt(request.getParameter("QID"));
				String drl7="select * from questions where id="+qid;
				try {
					PreparedStatement pst9=con.prepareStatement(drl7, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs7=pst9.executeQuery();
					if(rs7.first()){
						rs7.updateString(2,request.getParameter("question"));
						rs7.updateString(3,request.getParameter("option1"));
						rs7.updateString(4,request.getParameter("option2"));
						rs7.updateString(5,request.getParameter("option3"));
						rs7.updateString(6,request.getParameter("option4"));
						rs7.updateInt(7,Integer.parseInt(request.getParameter("ans")));
						rs7.updateRow();
						request.setAttribute("name","RowUpdate");
						request.getRequestDispatcher("success.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
