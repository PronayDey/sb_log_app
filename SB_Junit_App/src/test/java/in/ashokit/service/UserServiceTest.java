package in.ashokit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import in.ashokit.dao.UserDao;

@SpringBootTest
@ExtendWith(value = { MockitoExtension.class })
public class UserServiceTest {

	@MockBean
	private UserDao userDao;

	@InjectMocks
	private UserService userService;

	@Test
	public void testGetUsername() {
		
		when(userDao.findName(101)).thenReturn("Ashok");
		
		String username = userService.getUsername(101);
		
		assertEquals("Ashok",username);
	}
}
