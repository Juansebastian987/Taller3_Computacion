package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.dao.ITopicDao;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.ITopicRepository;

@Service
public class TopicServiceImp implements ITopicService{

	@Autowired
	public ITopicDao topicDao;

	@Override
	public TsscTopic saveTopic(TsscTopic topic) throws Exception{	
		if(topic.getDefaultGroups() >0 && topic.getDefaultSprints() >0) {
			topicDao.save(topic);			
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
		return topic;
	}

	@Override
	public TsscTopic editTopic(TsscTopic topic, long id) throws Exception {	
		if(topic.getDefaultGroups() >0 && topic.getDefaultSprints() >0) {	
			if(topic.getClass()==null) {
				throw new Exception("El objeto al cual estas accediendo no existe");
			}else if(topicDao.findById( topic.getId()).get((int)id)!=null){
				topicDao.save(topic);			
			}else {
				throw new Exception("El objeto no fue encontrado");
			}			
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
		return topic;
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return topicDao.findAll();
	}

	@Override
	public void delete(TsscTopic del) {
		topicDao.delete(del);
	}

	@Override
	public Optional<TsscTopic> findById(long id) {
		return null;
	}
}