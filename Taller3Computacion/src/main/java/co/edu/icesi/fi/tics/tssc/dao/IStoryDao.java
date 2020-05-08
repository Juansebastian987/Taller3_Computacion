package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface IStoryDao {
	public void save(TsscStory story) throws Exception;
	public void edit(TsscStory story) throws Exception;
	public void delete(TsscStory del);
	public List<TsscStory> findById(long id);
	public void deleteAll();
	public List<TsscStory> findAll();

}
