package br.com.musicbrain.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.musicbrain.server.domain.ArtistCredit;
import br.com.musicbrain.server.domain.Medium;
import br.com.musicbrain.server.repository.ArtistCreditRepository;
import br.com.musicbrain.server.repository.MediumRepository;
import br.com.musicbrain.server.repository.ToTestRepository;

@BasePathAwareController
public class ApiController {

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private ArtistCreditRepository repository;

    @Autowired
    private ToTestRepository testRepository;
    
    @RequestMapping(path="api/medium", method=RequestMethod.GET, produces="application/hal+json")
    public @ResponseBody Medium medium() {

        return mediumRepository.getMediumById(1);
    }

    @RequestMapping(path="api/artist", method=RequestMethod.GET, produces="application/hal+json")
    public @ResponseBody List<ArtistCredit> artistCredit() {

        return repository.findByName("Rouse");
    }

    @RequestMapping(path="api/artistSum", method=RequestMethod.GET, produces="application/hal+json")
    public @ResponseBody List<? extends Object> artistCreditSum() {

        return repository.sumCreditsByName("Rouse");
    }

    @RequestMapping(path="toTestInsert", method=RequestMethod.GET, produces="application/hal+json")
    public void toTestInsert() {
        testRepository.findAll();
    }

}