package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.Employee;
import az.developia.domain.dto.DepartmentDto;
import az.developia.domain.dto.EmployeeDto;
import az.developia.domain.dto.ManagerDto;
import az.developia.repository.EmployeeRepository;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;
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

    @Override
    public Optional<EmployeeDto> findById(Long id) {
        var sql = """
                select e.employee_id,
                       e.first_name,
                       e.last_name,
                       e.email,
                       e.salary,
                       m.employee_id as manager_id,
                       m.first_name as manager_first_name,
                       m.last_name as manager_last_name,
                       d.department_id,
                       d.department_name
                from employees e
                         left join employees m on m.employee_id = e.manager_id
                         inner join departments d on d.department_id = e.department_id
                where e.employee_id = ?;
                """;
        try (
                var conn = DbConfig.instance();
                var stmnt = conn.prepareStatement(sql);
        ) {
            stmnt.setLong(1, id);

            EmployeeDto employeeDto = null;
            try (var rs = stmnt.executeQuery();) {
                while (rs.next()) {
                    employeeDto = new EmployeeDto();
                    employeeDto.setId(rs.getLong("employee_id"));
                    employeeDto.setFirstName(rs.getString("first_name"));
                    employeeDto.setLastName(rs.getString("last_name"));
                    employeeDto.setEmail(rs.getString("email"));
                    employeeDto.setSalary(rs.getBigDecimal("salary"));

                    var manager = new ManagerDto();
                    manager.setId(rs.getLong("manager_id"));
                    manager.setFirstName(rs.getString("manager_first_name"));
                    manager.setLastName(rs.getString("manager_last_name"));

                    employeeDto.setManager(manager);

                    var departmentDto = new DepartmentDto();
                    departmentDto.setId(rs.getLong("department_id"));
                    departmentDto.setDepartmentName(rs.getString("department_name"));

                    employeeDto.setDepartment(departmentDto);
                }
            }

            return Optional.of(employeeDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
