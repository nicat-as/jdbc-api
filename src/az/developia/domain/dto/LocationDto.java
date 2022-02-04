package az.developia.domain.dto;

import java.util.Objects;

public class LocationDto {
    private Long id;
    private String streetAddress;
    private String postalCode;
    private String city;


    public LocationDto(Long id, String streetAddress, String postalCode, String city) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;

    }

    public LocationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(streetAddress, that.streetAddress) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(city, that.city);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetAddress, postalCode, city);
    }

    @Override
    public String toString() {
        return "LocationDto{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
