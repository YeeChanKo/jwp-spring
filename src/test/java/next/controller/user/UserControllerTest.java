package next.controller.user;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import next.dao.UserDao;
import next.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private UserDao userDao;

	private UserController controller;

	@Before
	public void setup() {
		controller = new UserController(userDao);
	}

	@Test
	public void create() throws Exception {
		User user = new User("javajigi", "password", "name", "");
		controller.create(user);

		verify(userDao).insert(user);
	}

	@Test
	public void profile() throws Exception {
		when(userDao.findByUserId("javajigi"))
				.thenReturn(new User("javajigi", "password", "name", ""));

		ExtendedModelMap model = new ExtendedModelMap();
		controller.profile("javajigi", model);

		assertNotNull(model.get("user"));
	}
}
