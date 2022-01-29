package az.developia.repository;

import az.developia.domain.Employee;
import az.developia.domain.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee e);

    void updateHireDate(String firstName, String lastName, LocalDate hireDate);

    Optional<EmployeeDto> findById(Long id);
}
