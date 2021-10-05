package com.priscilasanfer.algafood.api.assembler;

import com.priscilasanfer.algafood.api.model.input.FormaPagamentoInput;
import com.priscilasanfer.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInputInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInputInput, formaPagamento);
    }
}
