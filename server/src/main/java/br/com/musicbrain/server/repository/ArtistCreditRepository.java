package br.com.musicbrain.server.repository;

import java.util.List;

import br.com.musicbrain.server.domain.ArtistCredit;

public interface ArtistCreditRepository {

    List<ArtistCredit> findByName(String name) ;
    List<? extends Object> sumCreditsByName(String name) ;
}