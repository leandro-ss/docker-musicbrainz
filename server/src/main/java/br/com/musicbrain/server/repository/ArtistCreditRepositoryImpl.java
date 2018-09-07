package br.com.musicbrain.server.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.musicbrain.server.domain.ArtistCredit;

@Repository
@CacheConfig(cacheNames = "finds")
public class ArtistCreditRepositoryImpl implements ArtistCreditRepository {

    private static final Logger logger = LoggerFactory.getLogger(ArtistCreditRepositoryImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Cacheable
    public List<ArtistCredit> findByName(String name) {
        logger.debug("findByName");

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ArtistCredit> query = builder.createQuery(ArtistCredit.class);
        Root<ArtistCredit> root = query.from(ArtistCredit.class);

        root.fetch("recordingSet").fetch("trackSet").fetch("medium");

        query.select(root);
        query.distinct(true);
        query.where(builder.like(root.get("name"), name));
        query.orderBy(builder.asc(root.get("name")));

        TypedQuery<ArtistCredit> q = entityManager.createQuery(query);

        List<ArtistCredit> result = q.getResultList();
        
        for( ArtistCredit artist : result){

            artist.getRecordingSet().forEach(
                 rec -> rec.getTrackSet().forEach( 
                     tr -> tr.setRecording(null)
                )
            );
        }

        return result;
    }

    @Cacheable
    public List<? extends Object>  sumCreditsByName(String name) {
        logger.debug("findByName");

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<?> query = builder.createQuery();
        Root<ArtistCredit> root = query.from(ArtistCredit.class);

        query.multiselect(root.get("name"), builder.sum(root.get("artistCount")));
        query.where(builder.like(root.get("name"), name));
        query.groupBy(root.get("name"));

        TypedQuery<? extends Object> q = entityManager.createQuery(query);

        List<? extends Object> result = q.getResultList();

        return result;
    }

    @Cacheable
    public List<? extends Object>  projectionsCreditsByName(String name) {
        logger.debug("findByName");

        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<?> query = builder.createQuery();
        Root<ArtistCredit> root = query.from(ArtistCredit.class);

        query.multiselect(root.get("name"), builder.sum(root.get("artistCount")));
        query.where(builder.like(root.get("name"), name));
        query.groupBy(root.get("name"));

        TypedQuery<? extends Object> q = entityManager.createQuery(query);

        List<? extends Object> result = q.getResultList();

        return result;
    }

    @Cacheable
    public void redByName(String name) {
        logger.debug("findByName");
    }
}