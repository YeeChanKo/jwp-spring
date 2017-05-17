package next.dao;

import java.sql.SQLException;
import java.util.List;

import next.model.User;

public interface UserDao {

	void insert(User user);

	User findByUserId(String userId);

	List<User> findAll() throws SQLException;

	void update(User user);

}