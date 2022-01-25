package az.developia.config;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class DbConfig {
    private static final Logger LOG = Logger.getLogger(DbConfig.class.getName());

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Hello123@";
    private static final String URL = "jdbc:postgresql://localhost:5432/hr";

    public static Connection getConnectionWithDriverManager() {
        LOG.info("getConnectionWithDriverManager.start");
        try {
            var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            LOG.info("getConnectionWithDriverManager.end");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't connect to database. Reason: " + e.getSQLState());
        }

    }

    public static Connection getConnection() {
        var dataSource = new PGSimpleDataSource();
        try {
            dataSource.setDatabaseName("hr");
            var conn = dataSource.getConnection(USERNAME, PASSWORD);
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }


}
