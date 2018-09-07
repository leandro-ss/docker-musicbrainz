package br.com.musicbrain.server.repository;

import br.com.musicbrain.server.domain.Medium;

public interface MediumRepository {

    Medium getMediumById(int id);
}
