package co.edu.icesi.fi.tics.tssc.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface IGameRepository extends CrudRepository<TsscGame, Long> {

}
