package br.com.musicbrain.server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.musicbrain.server.domain.Recording;

@RepositoryRestResource(collectionResourceRel = "recording", path = "recording")
public interface RecordingRepository extends CrudRepository<Recording, Integer> {

	List<Recording> findByName(@Param("name") String name);

	@Override
    @RestResource(exported = false)
    <S extends Recording> S save(S s);

    @Override
    @RestResource(exported = false)
	void delete(Recording t);
	
	@Override
	@RestResource(exported = false)
	Iterable<Recording> findAll();
}