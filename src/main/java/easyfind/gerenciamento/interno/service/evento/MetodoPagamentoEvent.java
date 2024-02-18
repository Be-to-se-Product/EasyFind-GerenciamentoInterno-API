package easyfind.gerenciamento.interno.service.evento;

import easyfind.gerenciamento.interno.model.MetodoPagamento;
import lombok.Data;

@Data
public class MetodoPagamentoEvent {
    private String tipoModificacao;
    private MetodoPagamento metodoPagamento;
}
