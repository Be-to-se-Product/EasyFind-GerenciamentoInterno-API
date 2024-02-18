package easyfind.gerenciamento.interno.controller.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTagDto {
    @NotBlank
    private String descricao;
}
