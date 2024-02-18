package easyfind.gerenciamento.interno.controller;

import easyfind.gerenciamento.interno.controller.dto.metodoPagamento.CreateMetodoPagamentoDto;
import easyfind.gerenciamento.interno.controller.dto.metodoPagamento.MetodoPagamentoMapper;
import easyfind.gerenciamento.interno.controller.dto.metodoPagamento.ResponseMetodoPagamentoDto;
import easyfind.gerenciamento.interno.controller.dto.tag.CreateTagDto;
import easyfind.gerenciamento.interno.controller.dto.tag.ResponseTagDto;
import easyfind.gerenciamento.interno.controller.dto.tag.TagMapper;
import easyfind.gerenciamento.interno.model.MetodoPagamento;
import easyfind.gerenciamento.interno.model.Tag;
import easyfind.gerenciamento.interno.service.MetodoPagamentoService;
import easyfind.gerenciamento.interno.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerenciamento-interno")
@RequiredArgsConstructor
public class GerenciamentoInternoController {
    private final MetodoPagamentoService metodoPagamentoService;
    private final TagService tagService;

    @PostMapping("/metodo-pagamento")
    public ResponseEntity<ResponseMetodoPagamentoDto> cadastrarMetodoPagamento(@Valid @RequestBody CreateMetodoPagamentoDto metodoPagamento){
        MetodoPagamento metodoPagamentoCadastrado = metodoPagamentoService.cadastrarMetodoPagamento(MetodoPagamentoMapper.of(metodoPagamento));

        return ResponseEntity.ok(MetodoPagamentoMapper.of(metodoPagamentoCadastrado));
    }

    @PostMapping("/metodo-pagamento/lista")
    public ResponseEntity<List<ResponseMetodoPagamentoDto>> cadastrarListaMetodoPagamento(@Valid @RequestBody List<CreateMetodoPagamentoDto> metodosPagamento){
        List<MetodoPagamento> metodosPagamentoCadastrados = metodoPagamentoService.cadastrarListaMetodoPagamento(
                metodosPagamento.stream().map(MetodoPagamentoMapper::of).toList()
        );

        return ResponseEntity.ok(metodosPagamentoCadastrados.stream().map(MetodoPagamentoMapper::of).toList());
    }

    @PutMapping("/metodo-pagamento/{id}")
    public ResponseEntity<ResponseMetodoPagamentoDto> atualizarMetodoPagamento(@PathVariable Long id, @Valid @RequestBody CreateMetodoPagamentoDto metodoPagamento){
        MetodoPagamento metodoPagamentoAtualizado = metodoPagamentoService.atualizarMetodoPagamento(id, MetodoPagamentoMapper.of(metodoPagamento));

        return ResponseEntity.ok(MetodoPagamentoMapper.of(metodoPagamentoAtualizado));
    }

    @DeleteMapping("/metodo-pagamento/{id}")
    public ResponseEntity<Void> deletarMetodoPagamento(@PathVariable Long id){
        metodoPagamentoService.deletarMetodoPagamento(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tag")
    public ResponseEntity<ResponseTagDto> cadastrarTag(@Valid @RequestBody CreateTagDto tag){
        Tag tagCadastrada = tagService.cadastrarTag(TagMapper.of(tag));

        return ResponseEntity.ok(TagMapper.of(tagCadastrada));
    }

    @PostMapping("/tag/lista")
    public ResponseEntity<List<ResponseTagDto>> cadastrarListaTag(@Valid @RequestBody List<CreateTagDto> tags){
        List<Tag> tagsCadastradas = tagService.cadastrarListaTag(
                tags.stream().map(TagMapper::of).toList()
        );

        return ResponseEntity.ok(tagsCadastradas.stream().map(TagMapper::of).toList());
    }

    @PutMapping("/tag/{id}")
    public ResponseEntity<ResponseTagDto> atualizarTag(@PathVariable Long id, @Valid @RequestBody CreateTagDto tag){
        Tag tagAtualizada = tagService.atualizarTag(id, TagMapper.of(tag));

        return ResponseEntity.ok(TagMapper.of(tagAtualizada));
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<Void> deletarTag(@PathVariable Long id){
        tagService.deletarTag(id);

        return ResponseEntity.noContent().build();
    }
}
