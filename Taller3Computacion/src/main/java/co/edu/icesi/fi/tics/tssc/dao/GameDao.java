package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Repository
@Scope("singleton")
public class GameDao implements IGameDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveGame(TsscGame game) throws Exception {
		entityManager.persist(game);
	}

	@Override
	public void editGame(TsscGame game) throws Exception {
		entityManager.merge(game);
	}

	@Override
	public void delete(TsscGame del) {
		entityManager.remove(del);
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
	public List<TsscGame> findByDate(LocalDate localDate) {
		String jpql = "Select a from TsscGame a WHERE a.scheduleDate between fechaInicio and fechaFin'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate localDate, LocalTime localTime) {
		String jpql = "Select a from TsscGame a where a.scheduleDate between fechaInicio and fechaFin and a.scheduleTime between horaInicio and horaFinal'";
		return entityManager.createQuery(jpql).getResultList();		
	}

	@Override
	public List<TsscGame> findByScheduledGame(LocalDate localDate) {
		String query =  "SELECT *"+ 
						"FROM TsscTopic n"+ 
						"WHERE n.scheduleDate = "+localDate+
						"AND n.tsscStories > 9"+
						"AND n.tsscTimecontrols > 0";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public TsscGame saveGameTopic(TsscGame game, long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscGame saveGameWithTopic(TsscGame tsscGame, long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
