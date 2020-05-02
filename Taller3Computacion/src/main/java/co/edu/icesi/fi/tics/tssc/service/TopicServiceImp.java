package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.ITopicRepository;

@Service
public class TopicServiceImp implements ITopicService{

	@Autowired
	public ITopicRepository topicReposity;

	@Override
	public TsscTopic saveTopic(TsscTopic topic) throws Exception{	
		if(topic.getDefaultGroups() >0 && topic.getDefaultSprints() >0) {
			topicReposity.save(topic);			
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
			}else if(topicReposity.findById( topic.getId()).get()!=null){
				topicReposity.save(topic);			
			}else {
				throw new Exception("El objeto no fue encontrado");
			}			
		}
		else if(topicReposity.findById( topic.getId()).get().getClass()==null) {
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
		return topic;
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return topicReposity.findAll();
	}

	@Override
	public void delete(TsscTopic del) {
		topicReposity.delete(del);
	}

	@Override
	public Optional<TsscTopic> findById(long id) {
		return topicReposity.findById(id);
	}
}