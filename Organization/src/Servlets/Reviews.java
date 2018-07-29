package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import JDBC.ConnectionFactory;
import JDBC.DAO;
import JDBC.OrgRev;
import JDBC.ReviewDAO;


@WebServlet("/Reviews")
public class Reviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		out.println("<h2>Reviews of St.Anthony</h2><hr>");
		out.println("<head>");
		out.println( "<link rel=stylesheet type=text/css href=style.css>");
		out.println("<link rel=stylesheet href=https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css integrity=sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u crossorigin=anonymous>");
		 out.println("<nav class=navbar navbar-default><div class=container-fluid><ul class=nav navbar-nav>"
		 		+ "<li><a href=googleMap.html>Home</a></li><li class=active><a href=#>Experience</a></li>"
		 		+ "<li><a href=reward.html>Reward</a></li><li><a href=myProfile.html>My Profile</a></li></ul> </div>  </nav></head>");
		
	  
		out.println("<body>");
		
			
		
		
		String p2=request.getParameter("review");
		String p3=request.getParameter("star");
		
	
		
		session.setAttribute("Review", p2);
		session.setAttribute("Stars", p3);
		
		try {
			
	
		String x2=String.valueOf(p2);
		Integer x3=Integer.parseInt(p3);
		System.out.println("Saint Anthony"+" "+x2+" "+x3);
		out.println("Review for "+"Saint Anthony"+" created"+"-"+x2+" "+x3);

		DAO dao=new ReviewDAO();
		OrgRev review=new OrgRev("Saint Anthony",x2,x3);
		dao.saveReview(review);
		
		
		
		System.out.println("DB saved");
		} catch (Exception e) {
			System.err.println("block  failed");
			 out.println("Error:Enter word, word");
	
		
		}
		
		
		try {
			  Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
			String sql="SELECT OrgName,review,stars from OrgDB" ;//mysql
			ResultSet rs=st.executeQuery(sql);  //mysql
			String str="<table border=1><tr><th>Reviews</th><th>Stars</th><tr>";
		
			while (rs.next()) {
			 String OrgName=rs.getString("OrgName");
		    String review=rs.getString("review");
			String stars=String.valueOf(rs.getInt("stars"));
			str+="<tr><td>"+review+"</td><td>"+stars+"</td>";
			
			}
			str+="</table>";
			out.println(str);
			con.close();
			
		} catch (Exception e) {
			System.err.println(e);
			
		}
		
	
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
