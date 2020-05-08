package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.dao.IGameDao;
import co.edu.icesi.fi.tics.tssc.dao.ITopicDao;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.ITopicRepository;

@Service
public class GameServiceImp implements IGameService{
	@Autowired
	public IGameDao gameDao ;
	
	@Autowired
	public ITopicDao topicRepository;
	

	@Override
	public TsscGame saveGame(TsscGame tsscGame) throws Exception {
		if(tsscGame.getNGroups() >0 && tsscGame.getNSprints() >0) {		
			gameDao.save(tsscGame);									
		}
		else if(tsscGame.getNGroups() < 0) {
			throw new Exception("La cantidad de grupos debe ser mayor a cero.");
		}
		else if(tsscGame.getTsscTopic() == null) {
			throw new Exception("El Topic no puede ser nulo");
		}
		else if(tsscGame.getNSprints() < 0) {
			throw new Exception("La cantidad de sprints debe ser mayor a cero.");			
		}
		else if(tsscGame.getClass()==null) {
			throw new Exception("El Topic no puede ser nulo");			
		}
		else if(tsscGame.getNGroups()==0 && tsscGame.getNSprints()==0) {
			throw new Exception("La cantidad del elemento no puede ser igual a cero.");
		}
		return tsscGame;
	}

	@Override
	public TsscGame editGame(TsscGame tsscGame, long id) throws Exception {
		if(tsscGame.getNGroups() >0 && tsscGame.getNSprints() >0) {	
			if(tsscGame.getClass()==null) {
				throw new Exception("El objeto al cual estas accediendo no existe");
			}else if(gameDao.findById( tsscGame.getId()).get((int)id)!=null){
				gameDao.save(tsscGame);			
			}			
		}
		else if(tsscGame.getName()==null) {
			throw new Exception("El objeto esta nulo");
		}
		else if(gameDao.findById( tsscGame.getId()).get((int)id).getClass()==null) {
			throw new Exception("El objeto con esa id no existe");
		}
		else if(tsscGame.getNGroups() < 0) {
			throw new Exception("La cantidad de grupos debe ser mayor a cero.");
		}
		else if(tsscGame.getNSprints() < 0) {
			throw new Exception("La cantidad de sprints debe ser mayor a cero.");			
		}
		else if(tsscGame.getClass()==null) {
			throw new Exception("El Topic no puede ser nulo");			
		}
		else if(tsscGame.getNGroups()==0 && tsscGame.getNSprints()==0) {
			throw new Exception("La cantidad del elemento no puede ser igual a cero.");
		}	
		else {
			throw new Exception("Sprints y grupos es mayor a cero.");
		}	
		return tsscGame;
	}

	@Override
	public TsscGame saveGameTopic(TsscGame game, long id) throws Exception {
		
		if(game.getNGroups() >0 && game.getNSprints() >0) {		
			if(game.getTsscTopic()!=null) {
				if (topicRepository.findById( id)==null) {
					throw new Exception("El Topic es nulo");		
				} 
				gameDao.save(game);				
			}			
		}	
		else if(game.getTsscTopic()==null) {
			throw new Exception("El Topic es nulo");		
		}
		else if(game.getNGroups() < 0) {
			throw new Exception("La cantidad de grupos debe ser mayor a cero.");
		}
		else if(topicRepository.findById( id)==null) {
			throw new Exception("El Topic no puede ser nulo");
		}
		else if(game.getNSprints() < 0) {
			throw new Exception("La cantidad de sprints debe ser mayor a cero.");			
		}
		else if(game.getClass()==null) {
			throw new Exception("El Topic no puede ser nulo");			
		}
		else if(game.getNGroups()==0 && game.getNSprints()==0) {
			throw new Exception("La cantidad del elemento no puede ser igual a cero.");
		}
		return game;
		
	}
	/*
	@Override
	public TsscGame saveGameTopic2(TsscTopic topic, long id) throws Exception {
		
		TsscGame element = new  TsscGame();
		
		if(topic.getDefaultGroups() >0 && topic.getDefaultSprints() >0) {	
			if(topic.getClass()==null || topic==null) {
				throw new Exception("El objeto al cual estas accediendo no existe");
			}else if(topicRepository.findById( topic.getId()).get()!=null){
				
				TsscGame tsscGameNew = new TsscGame();
				
				tsscGameNew.setNGroups( (int) topic.getDefaultGroups());
				tsscGameNew.setNSprints( (int) topic.getDefaultSprints());
	
				tsscGameNew.setTsscStories(topic.getTsscStories());
				tsscGameNew.setTsscTimecontrol(topic.getTsscTimecontrol());
				
				element = gameRepository.save(tsscGameNew);
				
				return element;
			}			
		}
		else if(topicRepository.findById( topic.getId()).get().getClass()==null) {
			throw new Exception("El objeto con esa id no existe");
		}
		else if(topic.getDefaultGroups() < 0) {
			throw new Exception("La cantidad de grupos debe ser mayor a cero.");
		}
		else if(topic.getDefaultSprints() < 0) {
			throw new Exception("La cantidad de sprints debe ser mayor a cero.");			
		}
		else if(topic.getClass()==null) {
			throw new Exception("El Topic no puede ser nulo");			
		}
		else if(topic.getDefaultGroups()==0 && topic.getDefaultSprints()==0) {
			throw new Exception("La cantidad del elemento no puede ser igual a cero.");
		}	
		return element;
	}
*/
	@Override
	public TsscGame saveGameWithTopic(TsscGame tsscGame, long id) throws Exception {
		if(tsscGame.getNGroups() >0 && tsscGame.getNSprints() >0) {	
			if(tsscGame.getClass()==null || tsscGame==null) {
				throw new Exception("El objeto al cual estas accediendo no existe");
			}else if(topicRepository.findById(id).get((int)id)!=null){
				
				List<TsscTopic> optional  = topicRepository.findById((int)id);
				TsscTopic topic = optional.get((int)id);
				
				if(topic.getDefaultGroups() > 0 && topic.getDefaultSprints()>0) {
					tsscGame.setNGroups( (int) topic.getDefaultGroups());
					tsscGame.setNSprints( (int) topic.getDefaultSprints());
		
					tsscGame.setTsscStories(topic.getTsscStories());
					tsscGame.setTsscTimecontrol(topic.getTsscTimecontrol());
					
					gameDao.save(tsscGame);
				}else {
					throw new Exception("El Topic tiene el grupo y sprint menos  a 0");
				}

				return tsscGame;
			}			
		}
		else if(topicRepository.findById( tsscGame.getId()).get((int) id).getClass()==null) {
			throw new Exception("El objeto con esa id no existe");
		}
		else if(tsscGame.getNGroups() < 0) {
			throw new Exception("La cantidad de grupos debe ser mayor a cero.");
		}
		else if(tsscGame.getNSprints() < 0) {
			throw new Exception("La cantidad de sprints debe ser mayor a cero.");			
		}
		else if(tsscGame.getClass()==null) {
			throw new Exception("El Topic no puede ser nulo");			
		}
		else if(tsscGame.getNGroups()==0 && tsscGame.getNSprints()==0) {
			throw new Exception("La cantidad del elemento no puede ser igual a cero.");
		}	
		return tsscGame;
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}

	@Override
	public void delete(TsscGame del) {
		gameDao.delete(del);
	}

	@Override
	public Optional<TsscGame> findById(long id) {
		return null;
	}	
}