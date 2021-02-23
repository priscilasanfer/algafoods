package com.priscilasanfer.algafood.domain.repository;

import com.priscilasanfer.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Restaurante restaurante);

}
