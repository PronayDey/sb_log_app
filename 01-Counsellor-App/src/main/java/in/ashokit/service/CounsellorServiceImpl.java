package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepository;


@Service
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired
	private CounsellorRepository repo;

	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		
		Counsellor findByEmail = repo.findByEmail(counsellor.getEmail());
		if(findByEmail !=null) {
			return false;
		} 
		else {
		
		Counsellor save = repo.save(counsellor);
		return save.getCounsellorId()!=null;
	}
	}
	@Override
	public Counsellor getCounsellor(String email, String pwd) {
		
		return repo.findByEmailAndPwd(email,pwd);
		
	}

}
