package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.repository.DepartmentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final Logger LOG = Logger.getLogger(DepartmentRepositoryImpl.class.getName());

    @Override
    public void updateDepartmentName(Long id, String departmentName) {
        LOG.log(Level.INFO, "updateDepartmentName.start");
        LOG.setLevel(Level.FINER);
        var sql = """
                update departments
                set department_name= ?
                where department_id= ?;            
                """;
        var conn = DbConfig.getConnectionWithDriverManager();
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, departmentName);
            statement.setLong(2, id);

            var affected = statement.executeUpdate();
            LOG.log(Level.FINER, "affected: " + affected);

            conn.commit();
            LOG.log(Level.INFO, "updateDepartmentName.end");
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
