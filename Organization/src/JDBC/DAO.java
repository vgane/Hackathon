package JDBC;


public interface DAO {
	void saveReview(OrgRev review) throws Exception;
	void showReview() throws Exception;
	
}
