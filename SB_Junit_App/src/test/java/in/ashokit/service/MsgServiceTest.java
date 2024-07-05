package in.ashokit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MsgServiceTest {

	@Autowired
	private MsgService msgService;

	@Test
	public void testWelcomeMsg() {
		String welcomeMsg = msgService.getWelcomeMsg();
		assertNotNull(welcomeMsg);
	}

	@Test
	public void testGreetMsg() {
		String welcomeMsg = msgService.getGreetMsg();
		assertNotNull(welcomeMsg);
	}

}