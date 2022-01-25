package az.developia.repository;

import az.developia.domain.Department;

public interface DepartmentRepository {
    void updateDepartmentName(Long id, String departmentName);
}
