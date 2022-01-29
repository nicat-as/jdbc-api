package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.Employee;
import az.developia.repository.EmployeeRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final Logger LOG = Logger.getLogger(EmployeeRepositoryImpl.class.getName());

    @Override
    public Employee save(Employee e) {
        var sql = "  ";
        try (
                var conn = DbConfig.instance();
                var statement = conn.prepareStatement(sql);
                var resultSet = statement.executeQuery();
        ) {

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "save error: ", ex);
        } finally {
            return e;
        }
    }

    @Override
    public void updateHireDate(String firstName, String lastName, LocalDate hireDate) {
        LOG.log(Level.INFO, "updateHireDate.start");
        LOG.setLevel(Level.FINER);
        var sql = " update employees " +
                " set hire_date = ? " +
                " where first_name = ? and last_name = ?; ";
        var conn = DbConfig.instance();
        try (var statement = conn.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(hireDate));
            statement.setString(2, firstName);
            statement.setString(3, lastName);

            var affected = statement.executeUpdate();
            LOG.log(Level.FINER, "affected: " + affected);

            conn.commit();
            LOG.log(Level.INFO, "updateHireDate.end");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
