package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ReviewDAO implements DAO{

	
		
		private Statement st=null;
		public ReviewDAO() throws Exception {
		  Connection con=ConnectionFactory.getConnection();
			st=con.createStatement();
		
		}
	@Override
	public void saveReview(OrgRev review) throws Exception {
		String sql="insert into OrgDB values('"+review.getOrgname()+"','"+review.getReview()+"',"+review.getStars()+")";//mysql
		int n=st.executeUpdate(sql);//mysql
		
		if(n>0) {//mysql
		System.out.println("Review saved:"+n);//mysql
		}	
		
	}
	@Override
	public void showReview() throws Exception {
		String sql="SELECT OrgName,review,stars from OrgDB" ;//mysql
		ResultSet rs=st.executeQuery(sql);  //mysql

	
		while (rs.next()) {
		 String OrgName=rs.getString("OrgName");
	    String review=rs.getString("review");
		String stars=String.valueOf(rs.getInt("stars"));
		
		System.out.println(review+" " +stars);
		}
		
		
	}

}
