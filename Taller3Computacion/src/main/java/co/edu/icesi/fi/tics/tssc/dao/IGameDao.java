package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface IGameDao {
	public void saveGame(TsscGame game) throws Exception;
	public void editGame(TsscGame game) throws Exception;
	public void delete(TsscGame del);
	public List<TsscGame> findById(long id);
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public List<TsscGame> findByDate(LocalDate localDate);
	public List<TsscGame> findByDateAndTimeRange(LocalDate localDate, LocalTime localTime);
	public List<TsscGame> findByScheduledGame(LocalDate localDate);
	public TsscGame saveGameTopic(TsscGame game, long id) throws Exception;
	public TsscGame saveGameWithTopic(TsscGame tsscGame, long id) throws Exception;	
	public Iterable<TsscGame> findAll();
	
}
