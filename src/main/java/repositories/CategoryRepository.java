package repositories;

import models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CategoryRepository implements Repository<Category>{

    private JdbcTemplate jdbcTemplate;


    //language=SQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM category";


    public CategoryRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Category> categoryRowMapper = ((resultSet, i) -> Category
            .builder()
            .name(resultSet.getString("name"))
            .id(resultSet.getLong("id"))
            .img(resultSet.getString("icon"))
            .build());

    @Override
    public Optional<Category> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Category model) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, categoryRowMapper);
    }


}
