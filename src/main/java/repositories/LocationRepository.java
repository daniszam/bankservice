package repositories;

import models.LocationUser;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepository implements Repository<LocationUser>, Location {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SELECT_CITY_BY_COUNTRY = "SELECT city_name, country_name FROM location WHERE country_name=?";

    //language=SQL
    private static final String SELECT_COUNTRY = "SELECT DISTINCT ON(country_name) * FROM location";

    public LocationRepository (DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<LocationUser> locationUserRowMapper = ((resultSet, i) -> LocationUser.builder()
            .city(resultSet.getString("city_name"))
            .country(resultSet.getString("country_name"))
            .build());


    @Override
    public Optional<LocationUser> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(LocationUser model) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<LocationUser> findAll() {
        return null;
    }

    @Override
    public void update(LocationUser model) {

    }

    @Override
    public List<LocationUser> getCitys(LocationUser country) {
        try{
            System.out.println(country.getCountry());
            return jdbcTemplate.query(SELECT_CITY_BY_COUNTRY, locationUserRowMapper, country.getCountry());
        }catch (IncorrectResultSizeDataAccessException e){
            return new ArrayList<>();
        }
    }

    @Override
    public List<LocationUser> getCountrys() {
        try{
            return jdbcTemplate.query(SELECT_COUNTRY, locationUserRowMapper);
        }catch (IncorrectResultSizeDataAccessException e){
            return new ArrayList<>();
        }
    }
}
