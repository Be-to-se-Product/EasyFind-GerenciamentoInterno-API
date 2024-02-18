package easyfind.gerenciamento.interno.controller.dto.metodoPagamento;

import easyfind.gerenciamento.interno.model.MetodoPagamento;

public class MetodoPagamentoMapper {
    public static MetodoPagamento of(CreateMetodoPagamentoDto dto){
        MetodoPagamento metodoPagamento = new MetodoPagamento();
        metodoPagamento.setDescricao(dto.getDescricao());
        return metodoPagamento;
    }

    public static ResponseMetodoPagamentoDto of(MetodoPagamento metodoPagamento){
        ResponseMetodoPagamentoDto dto = new ResponseMetodoPagamentoDto();
        dto.setId(metodoPagamento.getId());
        dto.setDescricao(metodoPagamento.getDescricao());
        return dto;
    }
}
