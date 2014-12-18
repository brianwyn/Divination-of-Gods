package server.core.sql;

import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * SQLite connectionstring: jdbc:sqlite:./database/database.sqlite
 * 
 * @author tentaquil
 * 
 */
public abstract class SQLHandler {

	private final ObjectPool objectPool;
	private final ConnectionFactory connectionFactory;
	private final PoolableConnectionFactory poolableConnectionFactory;
	private final PoolingDataSource dataSource;

	private final ExecutorService service;

	public SQLHandler(ExecutorService service, String connectionUri) {
		this(service, connectionUri, "root", "password");
	}

	public SQLHandler(ExecutorService service, String connectionUri,
			String username, String password) {
		this.service = service;

		this.objectPool = new GenericObjectPool();
		this.connectionFactory = new DriverManagerConnectionFactory(
				connectionUri, username, password);
		this.poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, objectPool, null, null, false, true);
		this.dataSource = new PoolingDataSource(
				poolableConnectionFactory.getPool());
	}

	public abstract void initializeDriver() throws ClassNotFoundException;

	public Future<?> submit(final SQLJob job) {
		return service.submit(new Callable<Object>() {

			public Object call() throws Exception {
				Object returnValue = null;
				synchronized (dataSource) {
					try {
						final Connection connection = dataSource
								.getConnection();
						returnValue = job.execute(connection);
						connection.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				return returnValue;
			}

		});
	}

}