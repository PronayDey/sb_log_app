package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService cservice;
	@Autowired
	private EnquiryService enqService;
		
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "redirect:/";
	}
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("counsellor",new Counsellor());
		return "registerView";
	}
	@PostMapping("/register")
	public String handleRegister(Counsellor c,Model model) {
		boolean status = cservice.saveCounsellor(c);
		if(status) {
		model.addAttribute("smsg","Counsellor saved");
		}else {
			model.addAttribute("smsg","failed to save");
		}
		return "registerView";
	}
	
	@GetMapping("/")
	public String login(Model model) {
		
		model.addAttribute("counsellor", new Counsellor());
		return "index";
	}
     @PostMapping("/login")
	public String handleLogin(Counsellor counsellor,HttpServletRequest req,   Model model) {
		
		Counsellor c = cservice.getCounsellor(counsellor.getEmail(), counsellor.getPwd());
		
		if(c==null) {
			model.addAttribute("emsg", "Invalid credentials");
			return "index";
		}else
		{
			HttpSession session = req.getSession(true);
			session.setAttribute("cid", c.getCounsellorId());
			
			return "redirect:/dashboard";
		}
		
	}
     @GetMapping("/dashboard")
     public String buildDashboard(HttpServletRequest req, Model model) {
    	 HttpSession session = req.getSession(false);
		 Integer cid = (Integer) session.getAttribute("cid");
    	 
		 Dashboard dbInfo = enqService.getDashboardInfo(cid);
			
			model.addAttribute("dashboard", dbInfo);
			return "dashboard";
     }
	
	
	
}
