package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface IGameDao {
	public void save(TsscGame game) throws Exception;
	public void edit(TsscGame game) throws Exception;
	public void delete(TsscGame del);
	public List<TsscGame> findById(long id);
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public List<TsscGame> findByDate(LocalDate localDate, LocalDate localDate2);
	public List<TsscGame> findByDateAndTimeRange(LocalDate localDate, LocalTime localTime);
	public List<Object[]> findByScheduledGame(LocalDate localDate);
	public List<TsscGame> dateChronometer(LocalDate localDate);
	public void deleteAll();
	public List<TsscGame> findAll();
	
}
