package az.developia;


import az.developia.repository.impl.LocationRepositoryImpl;


public class Main {
    public static void main(String[] args) {

        var locationReport = new LocationRepositoryImpl();
        System.out.println(locationReport.findByPostalCode("26192"));
       locationReport.updateStAddress("26192");
        locationReport.deleteById(1700L);

    }
}