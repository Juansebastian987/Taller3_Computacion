package co.edu.icesi.fi.tics.tssc.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.IStoryRepository;

@Service
public class StoryServiceImp implements IStoryService {


	@Autowired
	public IStoryRepository storyRepository;
	
	@Override
	public TsscStory saveStory(TsscStory story, long id) throws Exception {
		if (story != null) {
			if(story.getTsscGame().getNGroups() >0 && story.getTsscGame().getNSprints() >0) {
						storyRepository.save(story);								
				}
		}
		else if(story.getClass()==null) {
			throw new Exception();
		}
		 else if (story.getPriority().compareTo(new BigDecimal(0)) == 0 || story.getPriority().compareTo(new BigDecimal(0)) == -1) {
				throw new Exception();
			}
		else if (story.getBusinessValue().compareTo(new BigDecimal(0)) == 0 || story.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new Exception();

		} else if (story.getInitialSprint().compareTo(new BigDecimal(0)) == 0 || story.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new Exception();

		}
		else {
			throw new Exception("Sprints y grupos es mayor a cero.");
		}
		
		return story;
	}

	@Override
	public TsscStory editStory(TsscStory story, long id) throws Exception {
		if(story.getTsscGame().getNGroups() >0 && story.getTsscGame().getNSprints() >0) {	
			if(story.getClass()==null) {
				throw new Exception("El objeto al cual estas accediendo no existe");
			}else if(storyRepository.findById(story.getId()).get()!=null){
				storyRepository.save(story);			
			}			
		}
		else if(story.getClass()==null) {
			throw new Exception();
		}
		 else if (story.getPriority().compareTo(new BigDecimal(0)) == 0 || story.getPriority().compareTo(new BigDecimal(0)) == -1) {
				throw new Exception();
			}
		else if (story.getBusinessValue().compareTo(new BigDecimal(0)) == 0 || story.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new Exception();

		} else if (story.getInitialSprint().compareTo(new BigDecimal(0)) == 0 || story.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new Exception();

		}
		else {
			throw new Exception("Sprints y grupos es mayor a cero.");
		}	
		return story;
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return storyRepository.findAll();
	}

	@Override
	public void delete(TsscStory del) {
		storyRepository.delete(del);
	}

	@Override
	public Optional<TsscStory> findById(long id) {
		return storyRepository.findById(id);
	}

}