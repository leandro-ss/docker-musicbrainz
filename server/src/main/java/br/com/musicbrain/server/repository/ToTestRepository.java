package br.com.musicbrain.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.musicbrain.server.domain.ToTest;

@RepositoryRestResource(collectionResourceRel = "toTest", path = "toTest")
public interface ToTestRepository extends CrudRepository<ToTest, Integer> {
}