package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
@Scope("singleton")
public class StoryDao implements IStoryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveStory(TsscStory story) throws Exception {
		entityManager.persist(story);		
	}

	@Override
	public void editStory(TsscStory story) throws Exception {
		entityManager.merge(story);		
	}
	
	@Override
	public void delete(TsscStory del) {
		entityManager.remove(del);		
	}

	@Override
	public List<TsscStory> findById(long id) {
		return (List<TsscStory>) entityManager.find(TsscStory.class, id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
