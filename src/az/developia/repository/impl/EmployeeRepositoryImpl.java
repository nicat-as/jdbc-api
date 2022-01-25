package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.Employee;
import az.developia.repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final Logger LOG = Logger.getLogger(EmployeeRepositoryImpl.class.getName());

    @Override
    public Employee save(Employee e) {
        var sql = """
                
                """;
        try (
                var conn = DbConfig.getConnectionWithDriverManager();
                var statement = conn.prepareStatement(sql);
                var resultSet = statement.executeQuery();
        ) {

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "save error: ", ex);
        } finally {
            return e;
        }
    }
}
