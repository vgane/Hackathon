package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JDBC.ConnectionFactory;
import eventjdbcHistory.DAOeh;
import eventjdbcHistory.EventHist;
import eventjdbcHistory.EventHistDAO;







/**
 * Servlet implementation class EventHistory
 */
@WebServlet("/EventHistory")
public class EventHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out=response.getWriter();
		out.println("<h2>User Event History</h2><hr>");
		HttpSession session=request.getSession();
	
		
	
	
		String p5=request.getParameter("u7");
		String p6=request.getParameter("u8");
		
	
	
		session.setAttribute("Signature", p5);
		session.setAttribute("Hours", p6);
		
		try {
			
		String x1="Tech Lab";
		String x2="St.Anthony";
		String x3="10:30 7/30/2018";
		Integer x4=Integer.parseInt("94111");
		String x5=String.valueOf(p5);
		Integer x6=Integer.parseInt(p6);
		
		System.out.println(x1+" "+x2+" "+x3+" "+x4+" "+x5+" "+x6);
		out.println("History for "+x1+" created");
		
		DAOeh dao=new EventHistDAO();
		EventHist his=new EventHist(x1,x2,x3,x4,x5,x6);
		dao.saveHist(his);
		
		System.out.println("DB saved");
		} catch (Exception e) {
			System.err.println("block  failed");
			 out.println("Error:Enter word, word");
	
			
			
		}
		
		try {
		
				  Connection con=ConnectionFactory.getConnection();
					Statement st=con.createStatement();
			String sql="SELECT EventName,Orgname,Date,zipcode,signature,Hours from eventhistory" ;//mysql
			ResultSet rs=st.executeQuery(sql);  //mysql
			String str="<table border=1><tr><th>Event Name</th><th>Organization</th><th>Date</th><th>Zipcode</th><th><Hours></th><tr>";

		
			while (rs.next()) {
			 String eventname=rs.getString("EventName");
		    String orgname=rs.getString("Orgname");
			String date=rs.getString("Date");
			int zipcode=rs.getInt("zipcode");
			String signature=rs.getString("signature");
			int hours=rs.getInt("hours");
		str+="<tr><td>"+eventname+"</td><td>"+orgname+"</td><td>"+ date+"</td><td>"+zipcode+"</td><td>"+hours+"</td>";
		
		
		}
		str+="</table>";
		out.println(str);
		con.close();
			
		}catch (Exception e){
			System.err.println("block  failed");
			 out.println("Error:Enter word, word");
	
		}
		out.println("<html>");
		out.println("<form action=reward.html> <h2>");
		out.println("<input type=submit value=SeeRewards>");
		out.println("</form></html>");
		out.println("<html>");
		out.println("<form action=googleMap.html> <h2>");
		out.println("<input type=submit value=Home>");
		out.println("</form></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
