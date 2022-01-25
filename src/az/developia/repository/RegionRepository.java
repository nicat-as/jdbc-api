package az.developia.repository;

import az.developia.domain.Region;

import java.util.List;

public interface RegionRepository {
    List<Region> findAll();
}
