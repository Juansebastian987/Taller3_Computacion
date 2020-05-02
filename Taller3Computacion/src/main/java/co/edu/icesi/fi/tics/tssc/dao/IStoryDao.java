package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface IStoryDao {
	public void saveStory(TsscStory story) throws Exception;
	public void editStory(TsscStory story) throws Exception;
	public void delete(TsscStory del);
	public List<TsscStory> findById(long id);
	public Iterable<TsscStory> findAll();
}