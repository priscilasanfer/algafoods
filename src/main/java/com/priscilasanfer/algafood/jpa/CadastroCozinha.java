package com.priscilasanfer.algafood.jpa;

import com.priscilasanfer.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> lista (){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha cadastrar(Cozinha cozinha){
        return manager.merge(cozinha);
    }
}
