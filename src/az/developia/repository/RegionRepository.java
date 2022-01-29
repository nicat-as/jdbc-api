package az.developia.repository;

import az.developia.domain.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepository {
    Optional<Region> findById(long id);

    List<Region> deleteByName();
}
