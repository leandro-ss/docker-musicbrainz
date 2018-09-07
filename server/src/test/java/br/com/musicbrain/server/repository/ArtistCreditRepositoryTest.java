package br.com.musicbrain.server.repository;

import static org.hibernate.testing.transaction.TransactionUtil.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.musicbrain.server.domain.ArtistCredit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArtistCreditRepositoryTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Test
    public void test(){

        ArtistCredit artist = new ArtistCredit();

        artist.setRefCount(Integer.valueOf(1));
        artist.setArtistCount(Short.valueOf("1"));
        artist.setCreated(new Date());
        artist.setName("test");

        doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.persist(artist);

            assertTrue( entityManager.contains( artist ) );

            entityManager.remove(artist);

            assertFalse( entityManager.contains( artist ) );
        } );
    }

    public EntityManagerFactory entityManagerFactory() {
        return this.entityManagerFactory;
    }
}