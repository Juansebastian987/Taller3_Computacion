package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITopicDao {
	public void saveTopic(TsscTopic topic) throws Exception;
	public void editTopic(TsscTopic topic) throws Exception;
	public void delete(TsscTopic del);
	public List<TsscTopic> findById(long id);
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	public List<TsscTopic> findByDate(LocalDate localDate);
	public List<TsscTopic> findAll();
	public void deleteAll();

}
