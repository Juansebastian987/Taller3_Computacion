package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Repository
@Scope("singleton")
public class GameDao implements IGameDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscGame game) throws Exception {
		entityManager.persist(game);
	}

	@Override
	public void edit(TsscGame game) throws Exception {
		entityManager.merge(game);
	}

	@Override
	public void delete(TsscGame del) {
		entityManager.remove(del);
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select a FROM TsscGame a";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}
	
	@Override
	public List<TsscGame> findById(long id) {
		return (List<TsscGame>) entityManager.find(TsscGame.class, id);		
	}

	@Override
	public List<TsscGame> findByName(String name) {
		return entityManager.createQuery("SELECT n FROM TsscGame n WHERE n.name = '"+name+"'").getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		return entityManager.createQuery("SELECT n FROM TsscGame n WHERE n.description = '"+description+"'").getResultList();
	}

	@Override
	public List<TsscGame> findByDate(LocalDate localDate, LocalDate localDate2) {		
		return entityManager.createQuery("Select a from TsscGame a WHERE a.scheduledDate BETWEEN :localDate AND :localDate2", TsscGame.class)
				.setParameter("localDate", localDate).setParameter("localDate2", localDate2)
				.getResultList();
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate localDate, LocalTime localTime) {
		return entityManager.createQuery("Select a from TsscGame a WHERE a.scheduledDate BETWEEN :localDate AND :localDate2", TsscGame.class)
				.setParameter("localDate", localDate).setParameter("localDate2", localTime)
				.getResultList();	
	}

	@Override
	public List<TsscGame> dateChronometer(LocalDate localDate) {
		
		String query = "Select a from TsscGame a Where "+ "(a.scheduledDate =:scheduledDate AND (("
				+ "(SELECT Count(b) FROM TsscTimecontrol b WHERE b.tsscGame.id = a.id) = 0) OR "+
		"(SELECT Count(s) FROM TsscStory s WHERE s.tsscGame.id = a.id ) < 10))";
		
		TypedQuery<TsscGame> q = entityManager.createQuery(query, TsscGame.class).setParameter("scheduledDate", localDate);
		
		return q.getResultList();
	}

	@Override
	public List<Object[]> findByScheduledGame(LocalDate localDate) {
		String query = "Select a, Count(s) FROM TsscTopic a JOIN TsscGame s ON a.id = s.id.tsscTopic.id WHERE s.scheduledDate = date ORDER BY s.scheduledTime ASC";
		TypedQuery<Object[]> queryResult = entityManager.createQuery(query, Object[].class);
		queryResult.setParameter("date", localDate);
		return queryResult.getResultList();
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery("DELETE From TsscGame").executeUpdate();		
	}

}
