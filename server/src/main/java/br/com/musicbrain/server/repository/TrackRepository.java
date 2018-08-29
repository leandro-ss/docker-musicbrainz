package br.com.musicbrain.server.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.musicbrain.server.domain.Track;


@CacheConfig(cacheNames = "tracks")
@RepositoryRestResource(collectionResourceRel = "track", path = "track")
public interface TrackRepository extends CrudRepository<Track, Integer> {

	@Cacheable
	@Query(name="FROM Track t JOIN FETCH Recording r WHERE t.name = :name")
	List<Track> findByName(@Param("name") String name);

	@Override
    @RestResource(exported = false)
    <S extends Track> S save(S s);

    @Override
    @RestResource(exported = false)
	void delete(Track t);
	
	@Override
	@RestResource(exported = false)
	Iterable<Track> findAll();
}
