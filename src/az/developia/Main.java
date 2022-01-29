package az.developia;

import az.developia.repository.impl.DepartmentRepositoryImpl;
import az.developia.repository.impl.EmployeeRepositoryImpl;
import az.developia.repository.impl.RegionRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //var departmentRepo = new DepartmentRepositoryImpl();
        //departmentRepo.updateDepartmentName(6L, "Information Technology");

        var regionReport = new RegionRepositoryImpl();
//        regionReport.findById(2)
//                .ifPresentOrElse(System.out::println,
//                        () -> System.out.println("region not found")
//                );

        //regionReport.deleteByName().forEach(System.out::println);

//        var updateHireDate = new EmployeeRepositoryImpl();
//        updateHireDate.updateHireDate("Lex", "De Haan", LocalDate.of(2015, 1, 1));

//        var isDeleted = regionReport.deleteByName("Asia");
//        System.out.println(isDeleted);
        var er = new EmployeeRepositoryImpl();
        er.findById(102L).ifPresent(System.out::println);

    }
}