package az.developia.repository;

import az.developia.domain.Employee;

public interface EmployeeRepository {
    Employee save(Employee e);
    void updateHireDate(String firstName, String lastName);
}
