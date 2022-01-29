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
        var conn = DbConfig.instance();
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

    /**
     * Ayxan - Country - select by country id, update country name, delete country by id
     * Tagi  - Department - select by id, update department name from prev, delete department by id
     * Zakir - Employee - select employee by id, update email of employee, delete employee by id
     * Ates - Job - select job by title, update job min and max salary by job title, delete job by id
     * Ilahe - Location - select location by postal code, update location street address by postal code, delete location by id
     * Sehram - Region - select region by id, (Employee) update hire date by firstname and lastname, delete region by name
     */
}
