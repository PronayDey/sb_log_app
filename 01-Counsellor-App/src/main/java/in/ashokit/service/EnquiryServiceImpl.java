package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService{
	@Autowired
	private EnquiryRepository enqRepo;
	@Autowired
	private CounsellorRepository repo;

	@Override
	public Dashboard getDashboardInfo(Integer counsellorId) {

		Long totalEnq = enqRepo.getEnquries(counsellorId);
		Long openCnt = enqRepo.getEnquries(counsellorId, "new");
		Long lostCnt = enqRepo.getEnquries(counsellorId, "lost");
		Long enrolledCnt = enqRepo.getEnquries(counsellorId, "enrolled");
		
		Dashboard d =new Dashboard();
		d.setTotalEnqs(totalEnq);
		d.setEnrolledEnqs(enrolledCnt);
		d.setLostEnqs(lostCnt);
		d.setOpenEnqs(openCnt);
		return d;
		
	}
	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer counsellorId) {

		 Counsellor counsellor = repo.findById(counsellorId).orElseThrow();
		 enquiry.setCounsellor(counsellor);
    Enquiry saveEnq = enqRepo.save(enquiry);
		return saveEnq.getEnqId()!=null;
	}

	
	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellorId) {
		 Counsellor counsellor = new Counsellor();
		 counsellor.setCounsellorId(counsellorId);
		//adding filter values to entity
		 
		 Enquiry searchCriteria = new Enquiry();
		 searchCriteria.setCounsellor(counsellor);
		 
		 if(null!=enquiry.getCourse() && !"".equals(enquiry.getCourse())) {
			 
			 searchCriteria.setCourse(enquiry.getCourse());
		 }
		 
      if(null!=enquiry.getMode() && !"".equals(enquiry.getMode())) {
			 
			 searchCriteria.setMode(enquiry.getMode());
		 }
      
      if(null!=enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			 
			 searchCriteria.setCourse(enquiry.getStatus());
		 }
		 
		 
		//dynamic query creation based on the given object
		Example<Enquiry> of =Example.of(searchCriteria);
		return enqRepo.findAll(of);
	
		
	}

	@Override
	public Enquiry getEnquiry(Integer enqId) {
		

      return enqRepo.findById(enqId).orElseThrow();
		
	}
	

}
