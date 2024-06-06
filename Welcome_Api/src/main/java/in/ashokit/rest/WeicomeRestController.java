package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeicomeRestController {

	
	@Autowired
	private Environment env;
	
	@GetMapping("/welcome")
	public String WelcomeRestController() {
		String port = env.getProperty("server.port");
		
		String msg="WElcome to ashok it (" + port+ ")";
		return msg;
		
		
	}
}
