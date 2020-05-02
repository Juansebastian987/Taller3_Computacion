package co.edu.icesi.fi.tics.tssc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
public interface AdminRepository extends CrudRepository<TsscAdmin, Long> {
	List<TsscAdmin> findByUsername(String name);
}
