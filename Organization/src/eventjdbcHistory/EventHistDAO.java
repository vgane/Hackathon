package eventjdbcHistory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import JDBC.ConnectionFactory;
import JDBC.OrgRev;

public class EventHistDAO implements DAOeh {
	private Statement st=null;
	public EventHistDAO() throws Exception {
	  Connection con=ConnectionFactory.getConnection();
		st=con.createStatement();
	
	}
	@Override
	public void saveHist(EventHist his) throws Exception {
		String sql="insert into EventHistory values('"+his.getEventName()+"','"+his.getOrgName()+"','"+his.getEventDate()+"',"+his.getZipcode()+",'"+his.getSignature()+"',"+his.getHours()+")";//mysql
			int n=st.executeUpdate(sql);//mysql
			
			if(n>0) {//mysql
			System.out.println("History saved:"+n);//mysql
			}	
		
	}
	@Override
	public void showHist() throws Exception {

		String sql="SELECT EventName,Orgname,Date,zipcode,signature,Hours from eventhistory" ;//mysql
		ResultSet rs=st.executeQuery(sql);  //mysql

	
		while (rs.next()) {
		 String eventname=rs.getString("EventName");
	    String orgname=rs.getString("Orgname");
		String date=rs.getString("Date");
		int zipcode=rs.getInt("zipcode");
		String signature=rs.getString("signature");
		int hours=rs.getInt("hours");
		
		System.out.println(eventname);
		}
		
	}

}
