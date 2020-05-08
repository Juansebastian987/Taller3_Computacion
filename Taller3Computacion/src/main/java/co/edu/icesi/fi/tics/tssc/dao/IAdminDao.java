package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface IAdminDao {
	public void save(TsscAdmin tsscAdmin);
	public void edit(TsscAdmin tsscAdmin);
	public void delete(TsscAdmin tsscAdmin);
	public List<TsscAdmin> findById(long id);
	public List<TsscAdmin> findAll();
	public void deleteAll();


}
