package eventjdbcHistory;


public interface DAOeh {
	void saveHist( EventHist his) throws Exception;
	void showHist() throws Exception;
}
