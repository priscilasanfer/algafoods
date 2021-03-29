package com.priscilasanfer.algafood.domain.service;

import com.priscilasanfer.algafood.domain.exception.EntidadeEmUsoException;
import com.priscilasanfer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.priscilasanfer.algafood.domain.model.Estado;
import com.priscilasanfer.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_NAO_ENCONTRADA = "Não existe um cadastro de estado com código %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADA, estadoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscasOuFalhar (Long id){
        return estadoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADA, id)));
    }

}
