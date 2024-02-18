package easyfind.gerenciamento.interno.controller.dto.metodoPagamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMetodoPagamentoDto {
    @NotBlank
    private String descricao;
}
