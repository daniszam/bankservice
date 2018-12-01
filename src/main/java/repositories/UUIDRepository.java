package repositories;

import lombok.NoArgsConstructor;
import models.UUIDUser;
import models.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
public class UUIDRepository implements Repository<UUIDUser> {
    private JdbcTemplate jdbcTemplate;


    //language=SQL
    private static final String SQL_INSERT_UUID = "INSERT INTO uuid_user_cookie (user_id, uuid) VALUES (?,?)";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_USER_ID= "SELECT * FROM uuid_user_cookie WHERE user_id=?";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_USER_UUID= "SELECT * FROM uuid_user_cookie WHERE uuid=?";

    public UUIDRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<UUIDUser> uuidRepositoryRowMapper = ((resultSet, i) -> UUIDUser.builder()
            .user(User.builder().id(resultSet.getLong("user_id")).build())
            .uuid(resultSet.getString("uuid"))
            .build());


    @Override
    public Optional<UUIDUser> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(UUIDUser model) {
        jdbcTemplate.update(SQL_INSERT_UUID, model.getUser().getId(), model.getUuid());
        return true;
    }


    public Optional<UUIDUser> getOneByUUID(String uuid) {
       try{
          return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_ALL_BY_USER_UUID, uuidRepositoryRowMapper, uuid));
       }catch (IncorrectResultSizeDataAccessException e){
           return Optional.empty();
       }catch (IncorrectResultSetColumnCountException e){
           return Optional.empty();
       }catch (IllegalArgumentException e){
           return Optional.empty();
       }


    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UUIDUser> findAll() {
        return null;
    }

    @Override
    public void update(UUIDUser model) {

    }
}
