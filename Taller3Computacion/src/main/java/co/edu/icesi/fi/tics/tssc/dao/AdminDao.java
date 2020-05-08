package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class AdminDao implements IAdminDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscAdmin tsscAdmin) {
		 entityManager.persist(tsscAdmin);
	}

	@Override
	public void edit(TsscAdmin tsscAdmin) {
		entityManager.merge(tsscAdmin);
	}

	@Override
	public void delete(TsscAdmin tsscAdmin) {
		entityManager.remove(tsscAdmin);
	}

	@Override
	public List<TsscAdmin> findById(long id) {
		return (List<TsscAdmin>) entityManager.find(TsscAdmin.class, id);		
	}

	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select a FROM TsscAdmin a";
		return entityManager.createQuery(jpql, TsscAdmin.class).getResultList();
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery("DELETE From TsscAdmin").executeUpdate();		
	}
}
