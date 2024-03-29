package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
