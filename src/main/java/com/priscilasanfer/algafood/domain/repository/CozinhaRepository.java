package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	List<Cozinha> findTodasByNomeContaining(String nome);
	boolean existsByNome(String nome);
	Optional<Cozinha> findByNome(String nome);
}