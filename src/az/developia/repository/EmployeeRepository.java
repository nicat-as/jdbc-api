package az.developia.repository;

import az.developia.domain.Employee;

import java.time.LocalDate;

public interface EmployeeRepository {
    Employee save(Employee e);

    void updateHireDate(String firstName, String lastName, LocalDate hireDate);
}
