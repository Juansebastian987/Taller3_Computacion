package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Service
public interface IGameService {

	public TsscGame saveGame(TsscGame game) throws Exception;
	public TsscGame editGame(TsscGame game, long id) throws Exception;
	public TsscGame saveGameTopic(TsscGame game, long id) throws Exception;
	//public TsscGame saveGameTopic2(TsscTopic topic, long id) throws Exception;
	public TsscGame saveGameWithTopic(TsscGame tsscGame, long id) throws Exception;	
	public Iterable<TsscGame> findAll();
	public void delete(TsscGame del);
	public Optional<TsscGame> findById(long id);
}
