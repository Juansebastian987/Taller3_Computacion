package co.edu.icesi.fi.tics.tssc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
@Scope("singleton")
public class AdminDao implements IAdminDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveTsscAdmin(TsscAdmin tsscAdmin) {
		 entityManager.persist(tsscAdmin);
	}

	@Override
	public void editTsscAdmin(TsscAdmin tsscAdmin) {
		entityManager.merge(tsscAdmin);
	}

	@Override
	public void deleteTsscAdmin(TsscAdmin tsscAdmin) {
		entityManager.remove(tsscAdmin);
	}

}
