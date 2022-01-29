package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.Region;
import az.developia.repository.RegionRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RegionRepositoryImpl implements RegionRepository {
    private static final Logger LOG = Logger.getLogger(RegionRepositoryImpl.class.getName());

    @Override
    public List<Region> findAll() {
        LOG.info("findAll.start");
        var arr = new ArrayList<Region>();
        var sql = """
                select *
                from regions;
                """;
        try (
                var conn = DbConfig.instance();
                var statement = conn.prepareStatement(sql);
                var resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                var id = resultSet.getLong("region_id");
                var name = resultSet.getString("region_name");
                arr.add(new Region(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.info("findAll.end");
        return arr;
    }
}
