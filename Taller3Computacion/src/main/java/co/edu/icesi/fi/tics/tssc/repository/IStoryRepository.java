package co.edu.icesi.fi.tics.tssc.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface IStoryRepository extends CrudRepository<TsscStory, Long> {

}
