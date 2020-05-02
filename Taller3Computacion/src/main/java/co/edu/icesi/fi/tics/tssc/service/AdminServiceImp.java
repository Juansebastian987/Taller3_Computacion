package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public TsscAdmin saveTsscAdmin(TsscAdmin tsscAdmin) {
		return adminRepository.save(tsscAdmin);
	}

	@Override
	public TsscAdmin editTsscAdmin(TsscAdmin tsscAdmin) {
		return adminRepository.save(tsscAdmin);
	}

	@Override
	public void deleteTsscAdmin(TsscAdmin tsscAdmin) {
		adminRepository.delete(tsscAdmin);	
	}

}
