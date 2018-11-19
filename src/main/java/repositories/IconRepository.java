package repositories;

import models.Icon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class IconRepository implements Repository<Icon> {

    private JdbcTemplate jdbcTemplate ;

    //language=SQL
    public static final String SELECT_ALL_ICON = "SELECT * FROM icon";

    public IconRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Icon> iconRowMapper = ((resultSet, i) -> Icon.builder()
            .id(resultSet.getLong("id"))
            .path(resultSet.getString("path"))
            .build());


    @Override
    public Optional<Icon> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Icon model) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Icon> findAll() {
        return jdbcTemplate.query(SELECT_ALL_ICON, iconRowMapper);
    }

    @Override
    public void update(Icon model) {

    }
}
