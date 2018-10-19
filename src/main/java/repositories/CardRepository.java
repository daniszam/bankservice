package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Card;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepository implements Repository<Card>, AllByIDRepository<Card> {


    private Connection connection;

    //language=SQL
    public static final String SQL_SELECT_ALL_CARD_BY_ID =
            "Select id,bank_user_id,card.type_card, bank_account_id, " +
               " t.perzent, t.validity_period, card.balance " +
            "From card " +
            "JOIN card_type t on card.type_card = t.type_card " +
            "WHERE bank_user_id=?;";

    //language=SQL
    public static final String SQL_INSERT_INTO_CARD = "INSERT INTO card (bank_user_id, type_card) " +
            "VALUES (?, ?) ";

    //language=SQL
    public static final String SQL_DELETE_CARD = "DELETE FROM card WHERE id=?";

    //language=SQL
    public static final String SQL_FIND_ALL_CARD = "SELECT * FROM card JOIN card_type c2 on card.type_card = c2.type_card";

    //language=SQL
    public static final String SQL_GET_TYPE_CARD = "SELECT type_card FROM card_type";



    //language=SQL
    public static final String SQL_DELEATE_ALL_BY_USER_ID = "DELETE FROM card WHERE bank_user_id=?";
    public CardRepository(Connection connection){
        this.connection = connection;
    }


    private RowMapper<Card> cardRowMapper = new RowMapper<Card>() {
        @Override
        @SneakyThrows
        public Card rowMap(ResultSet resultSet) {
            BankUserRepository bankUserRepository = new BankUserRepository(connection);
           Optional<User> user = bankUserRepository.findOne(resultSet.getLong("bank_user_id"));
            Card card = Card.builder()
                    .cardType(resultSet.getString("type_card"))
                    .perzent(resultSet.getFloat("perzent"))
                    .user(user.get())
                    .validityPeriod( resultSet.getString("validity_period"))
                    .id(resultSet.getLong("id"))
                    .balance(resultSet.getFloat("balance"))
                    .build();
            return card;
        }
    };

    @Override
    public Optional<Card> findOne(Long id) {
        return null;
    }

    @Override
    @SneakyThrows
    public boolean save(Card card) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CARD);
        preparedStatement.setLong(1, card.getUser().getId());
        preparedStatement.setString(2, card.getCardType());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CARD);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    @SneakyThrows
    public List<Card> findAll() {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CARD);
        if(preparedStatement.execute()){
            List<Card> cards = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                cards.add(cardRowMapper.rowMap(resultSet));
            }
            return cards;
        }
        return null;
    }

    @SneakyThrows
    public List<Card> findAllByUserId(Long id){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_CARD_BY_ID);
        preparedStatement.setLong(1,id);
        if(preparedStatement.execute()){
            List<Card> cards = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                cards.add(cardRowMapper.rowMap(resultSet));
            }
            return cards;
        }

        return null;
    }

    @SneakyThrows
    @Override
    public void deleteAllByUserId(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELEATE_ALL_BY_USER_ID);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }
    @SneakyThrows
    public List<String> getType(){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TYPE_CARD);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<String> items = new ArrayList<>();
            while(resultSet.next()){
                items.add(resultSet.getString("type_card"));
            }
            return items;
        }
        return null;
    }
}
