package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Service
public interface IStoryService {
	public TsscStory saveStory(TsscStory story, long id) throws Exception;
	public TsscStory editStory(TsscStory story, long id) throws Exception;
	public Iterable<TsscStory> findAll();
	public void delete(TsscStory del);
	public Optional<TsscStory> findById(long id);
}
