package in.ashokit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	@Autowired
	private MsgService msgService;
     
	 @GetMapping("/welcome")
	public String getMsg() {
		 return msgService.getWelcomeMsg();
		
	}
}
