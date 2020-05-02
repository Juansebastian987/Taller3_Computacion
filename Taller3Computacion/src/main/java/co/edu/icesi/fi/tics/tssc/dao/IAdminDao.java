package co.edu.icesi.fi.tics.tssc.dao;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface IAdminDao {
	public void saveTsscAdmin(TsscAdmin tsscAdmin);
	public void editTsscAdmin(TsscAdmin tsscAdmin);
	public void deleteTsscAdmin(TsscAdmin tsscAdmin);
}
