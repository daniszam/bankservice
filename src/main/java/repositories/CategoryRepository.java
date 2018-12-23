package repositories;

import lombok.NoArgsConstructor;
import models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
public class CategoryRepository implements Repository<Category> {

    private JdbcTemplate jdbcTemplate;


    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM category";

    //language=SQL
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO category(name) VALUES (?)";

    //language=SQL
    public static final String SQL_SELECT_ALL_LIKE = "SELECT * FROM category WHERE name LIKE (?)";


    public CategoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Category> categoryRowMapper = ((resultSet, i) -> Category
            .builder()
            .name(resultSet.getString("name"))
            .id(resultSet.getLong("id"))
            .img(resultSet.getString("icon"))
            .color(new Color(resultSet.getInt("color")))
            .build());

    @Override
    public Optional<Category> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Category model) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY, model.getName());
        return true;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, categoryRowMapper);
    }

    @Override
    public void update(Category model) {

    }

    public List<Category> findByName(Category category){
        return jdbcTemplate.query(SQL_SELECT_ALL_LIKE, categoryRowMapper, "%"+category.getName()+"%");
    }

}
