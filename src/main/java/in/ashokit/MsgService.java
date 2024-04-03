package in.ashokit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	
 private Logger logger=	LoggerFactory.getLogger(MsgService.class); 
public String getWelcomeMsg() {
	logger.trace("this ids trace message");
	logger.debug("this is debugging message");
	logger.info("getWelcomeMsg()-(info)started...");
	String msg="welcome to Ashok it";
	logger.info("getWelcomeMsg()-(info)ended...");
	logger.warn("this is warning message");
	logger.error("this is error message");

	return msg;
}
}
