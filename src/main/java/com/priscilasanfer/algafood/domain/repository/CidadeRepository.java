package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Long id);

}
