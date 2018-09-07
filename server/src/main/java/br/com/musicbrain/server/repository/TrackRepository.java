package br.com.musicbrain.server.repository;

import java.util.List;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.musicbrain.server.domain.Track;


@CacheConfig(cacheNames = "tracks")
@RepositoryRestResource(collectionResourceRel = "track", path = "track")
public interface TrackRepository extends CrudRepository<Track, Integer> {

	@Query(value="SELECT t FROM Track t "+
				 "JOIN FETCH t.recording r "+
				 "JOIN FETCH t.medium m "+
				 "WHERE t.name = :name")
	List<Track> findByName(@Param("name") String name);


	@Query(value="SELECT DISTINCT NEW map(t.id as id, t.name as name) FROM Track t "+
				 "WHERE t.id = :id")
	Map<Integer,String> findIDByName(@Param("id") Integer id);

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