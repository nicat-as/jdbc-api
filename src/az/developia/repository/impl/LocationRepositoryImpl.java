package az.developia.repository.impl;

import az.developia.config.DbConfig;
import az.developia.domain.dto.LocationDto;
import az.developia.repository.LocationRepository;
import java.sql.SQLException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationRepositoryImpl implements LocationRepository {
    private static final Logger LOG = Logger.getLogger(LocationRepositoryImpl.class.getName());

    @Override
    public Optional<LocationDto> findByPostalCode(String postal_code) {

        LOG.info("findByPostalCode.start");

        var sql = """
                select *
                from locations
                where postal_code = ?
                """;

        try (
                var conn = DbConfig.instance();
                var statement = conn.prepareStatement(sql);

        ) {
            statement.setString(1, postal_code);

            LocationDto locationDto = null;
            try (var resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    locationDto = new LocationDto();
                    locationDto.setCity(resultSet.getString("city"));
                    locationDto.setPostalCode(resultSet.getString("postal_code"));
                    locationDto.setStreetAddress(resultSet.getString("street_address"));
                    locationDto.setId(resultSet.getLong("location_id"));
                }

            }
            return Optional.of(locationDto);
        }
                 catch (SQLException e) {
                System.out.println("---------------------------");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }



    @Override
    public void updateStAddress(String postalCode) {
        LOG.log(Level.INFO, "updateStAddress.start");
        LOG.setLevel(Level.FINER);

        var sql = """
                update locations
                set street_address = 'unknown'
                where postal_code = ?
                """;
        var conn = DbConfig.instance();

        try(var statement = conn.prepareStatement(sql)){
        //    statement.setString(1, street_address);
            statement.setString(1,"postalCode");

            var affected = statement.executeUpdate();
            LOG.log(Level.INFO, "affected: " + affected);

            conn.commit();
            LOG.log(Level.INFO, "updateStAddress.end");

        } catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex){
                ex.printStackTrace();
            }

        } finally {
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean deleteById(Long id) {
        LOG.info("deleteById.start");

        boolean isDeleted = false;
        var conn = DbConfig.instance();
        var sql = """ 
                delete from locations
                where location_id = ?
                """;

        try(
                var statement = conn.prepareStatement(sql);

                ){
            statement.setLong(1, id);
            var affectedRow = statement.executeUpdate();
            if(affectedRow > 0){
                isDeleted = true;
            }
            conn.commit();
        }catch (SQLException e){
            LOG.log(Level.SEVERE, "delete exception: ", e);
            try{
                conn.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        LOG.info("deleteById.end");
        return isDeleted;
    }

}
