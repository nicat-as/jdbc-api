package az.developia;

import az.developia.repository.impl.DepartmentRepositoryImpl;
import az.developia.repository.impl.EmployeeRepositoryImpl;
import az.developia.repository.impl.RegionRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        //var departmentRepo = new DepartmentRepositoryImpl();
        //departmentRepo.updateDepartmentName(6L, "Information Technology");

        //var regionReport = new RegionRepositoryImpl();
        //regionReport.findById(2).forEach(System.out::println);
        //regionReport.deleteByName().forEach(System.out::println);

        var updateHireDate = new EmployeeRepositoryImpl();
        updateHireDate.updateHireDate("Lex", "De Haan");

    }
}