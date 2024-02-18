package easyfind.gerenciamento.interno.service.evento;

import easyfind.gerenciamento.interno.model.Tag;
import lombok.Data;

@Data
public class TagEvent {
    private String tipoModificacao;
    private Tag tag;
}
