package repositories;

import lombok.NoArgsConstructor;
import models.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@org.springframework.stereotype.Repository
public class IconRepository implements Repository<Icon> {

    private JdbcTemplate jdbcTemplate ;

    //language=SQL
    private static final String SELECT_ALL_ICON = "SELECT * FROM icon";

    //language=SQL
    private static final String SELECT_BY_NAME = " SELECT * FROM icon WHERE icon_name=?";

    @Autowired
    public IconRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Icon> iconRowMapper = ((resultSet, i) -> Icon.builder()
            .id(resultSet.getLong("id"))
            .path(resultSet.getString("path"))
            .build());


    public Optional<Icon> findOneByName(String name){
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_NAME, iconRowMapper, name));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

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
