package az.developia;

import az.developia.repository.impl.DepartmentRepositoryImpl;
import az.developia.repository.impl.RegionRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        var departmentRepo = new DepartmentRepositoryImpl();
        departmentRepo.updateDepartmentName(6L, "Information Technology");
    }
}
