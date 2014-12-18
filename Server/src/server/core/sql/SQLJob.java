package server.core.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLJob {

	public abstract Object execute(Connection connection) throws SQLException;

}
