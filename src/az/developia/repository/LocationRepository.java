package az.developia.repository;

import java.util.Optional;

import az.developia.domain.dto.LocationDto;

public interface LocationRepository {

    Optional<LocationDto> findByPostalCode(String postalCode);
    
    void updateStAddress(String postalCode);

    boolean deleteById(Long id);


}


