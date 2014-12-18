package server.core.sql;

import java.util.concurrent.ExecutorService;

public class MySQLHandler extends SQLHandler {

	private static final String CONNECTION_URI = "jdbc:mysql://";

	public MySQLHandler(ExecutorService service, String file) {
		super(service, CONNECTION_URI + file);
	}

	@Override
	public void initializeDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

}
