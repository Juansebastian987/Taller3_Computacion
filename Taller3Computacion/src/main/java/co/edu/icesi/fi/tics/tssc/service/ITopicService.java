package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Service
public interface ITopicService {

	public TsscTopic saveTopic(TsscTopic topic) throws Exception;
	public TsscTopic editTopic(TsscTopic topic, long id) throws Exception;
	public Iterable<TsscTopic> findAll();
	public void delete(TsscTopic del);
	public Optional<TsscTopic> findById(long id);
	
}