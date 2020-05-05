package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class TopicDao implements ITopicDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveTopic(TsscTopic topic) throws Exception {
		entityManager.persist(topic);
	}

	@Override
	public void editTopic(TsscTopic topic) throws Exception {
		entityManager.merge(topic);
	}

	@Override
	public void delete(TsscTopic del) {
		entityManager.remove(del);
	}
	
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select a FROM TsscTopic a";
		return entityManager.createQuery(jpql, TsscTopic.class).getResultList();
	}

	@Override
	public List<TsscTopic> findById(long id) {
		return (List<TsscTopic>) entityManager.find(TsscTopic.class, id);
	}
	
	@Override
	public List<TsscTopic> findByName(String name) {
		return entityManager.createQuery("SELECT n FROM TsscTopic n WHERE n.name = '"+name+"'").getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		return entityManager.createQuery("SELECT n FROM TsscTopic n WHERE n.description = '"+description+"'").getResultList();
	}

	@Override
	public List<TsscTopic> findByDate(LocalDate localDate) {	
		String query = "SELECT *"+ 
						"FROM TsscTopic n"+ 
						"WHERE n.scheduleDate = "+localDate+
						"ORDER BY n.scheduleTime";	
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public void deleteAll() {
		entityManager.createQuery("DELETE From TsscTopic").executeUpdate();		
	}
}
