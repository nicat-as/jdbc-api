package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.Region;
import az.developia.repository.RegionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegionRepositoryImpl implements RegionRepository {
    private static final Logger LOG = Logger.getLogger(RegionRepositoryImpl.class.getName());

    @Override
    public Optional<Region> findById(long id) {
        LOG.info("findById.start");
        var sql = " select * from regions r where region_id = ?; ";
        Region region = null;
        try (
                var conn = DbConfig.instance();
                var statement = conn.prepareStatement(sql);
        ) {
            statement.setLong(1, id);

            try (var resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    var name = resultSet.getString("region_name");
                    region = new Region(id, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.info("findById.end");
        return Optional.ofNullable(region);
    }

    @Override
    public boolean deleteByName(String name) {
        LOG.info("deleteByName.start");
        boolean isDeleted = false;
        var conn = DbConfig.instance();
        var sql = " delete from regions where region_name = ?; ";
        try (
                var statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            var affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                isDeleted = true;
            }
            conn.commit();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "delete exception: ", e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LOG.info("deleteByName.end");
        return isDeleted;
    }
}
