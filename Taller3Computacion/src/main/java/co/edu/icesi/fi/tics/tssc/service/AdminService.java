package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Service
public interface AdminService {
	public TsscAdmin saveTsscAdmin(TsscAdmin tsscAdmin);
	public TsscAdmin editTsscAdmin(TsscAdmin tsscAdmin);
	public void deleteTsscAdmin(TsscAdmin tsscAdmin);
}
