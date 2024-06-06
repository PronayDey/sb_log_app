package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	@Autowired
	private EnquiryRepository enqRepo;
	
	@GetMapping("/enquiry")
	public String addEnquiry(Enquiry enq, Model model) {
		
		model.addAttribute("enq", new Enquiry());
		return "addEnq";
	}
	@PostMapping("/enquiry")
	 public String saveEnquiry(Enquiry enq,HttpServletRequest req,Model model) {
		 HttpSession session = req.getSession(false);
		 Integer cid = (Integer) session.getAttribute("cid");
		 boolean status= enqService.addEnquiry(enq, cid);
		 
		 if(status) {
			 model.addAttribute("smsg", "enquiry saved");
		 }else {
			 
			 model.addAttribute("emsg", "enquiry not saved");
		 }
		 model.addAttribute("enq", new Enquiry());
		 return "addEnq";
	 }
	 @GetMapping("/enquiries")
	public String getEnquiry(HttpServletRequest req,Model model) {
		 HttpSession session = req.getSession(false);
		 Integer cid = (Integer) session.getAttribute("cid");
		 List<Enquiry> list = enqService.getEnquiries(new Enquiry(), cid);
		 model.addAttribute("enqs", list);
		 model.addAttribute("enq", new Enquiry());
		 
		 return "viewEnquiries";
		 
	 }
	 @PostMapping("/filter-enqs")
	 public String filterEnqs(@ModelAttribute("enq")Enquiry enq,HttpServletRequest req,Model model) {
		 HttpSession session = req.getSession(false);
		 Integer cid = (Integer) session.getAttribute("cid");
		 
		 List<Enquiry> list = enqService.getEnquiries(enq, cid);
		 model.addAttribute("enqs", list);
		 
		 return "viewEnquiries";
		 
		 
		 
	 }
	 @GetMapping("/edit/{enqId}")
	 public String editEnquiry(@PathVariable("enqId") Integer enqId,Model model) {
		
		 Enquiry enquiry = enqService.getEnquiry(enqId);
		
		 model.addAttribute("enq", enquiry);
	
		 return "edit";
		
	 }
	 
	 @RequestMapping("/update/{enqId}")
	public String updateOneLeadData(@PathVariable("enqId") Integer enqId,@ModelAttribute("enq")Enquiry enq, Model model) {
		Enquiry enquiry = enqService.getEnquiry(enqId);
		enquiry.setEnqId(enq.getEnqId());
		enquiry.setStuName(enq.getStuName());
		enquiry.setStuPhno(enq.getStuPhno());
		enquiry.setCourse(enq.getCourse());
		enquiry.setMode(enq.getMode());
		enquiry.setStatus(enq.getStatus());
		enqRepo.save(enquiry);
		 
		return "redirect:/enquiries";
		 
	 }
	 
	
}


