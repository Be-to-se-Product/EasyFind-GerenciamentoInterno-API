package easyfind.gerenciamento.interno.service;

import easyfind.gerenciamento.interno.model.MetodoPagamento;
import easyfind.gerenciamento.interno.repository.MetodoPagamentoRepository;
import easyfind.gerenciamento.interno.service.evento.MetodoPagamentoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {
    private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final KafkaTemplate<String, MetodoPagamentoEvent> kafkaTemplate;

    public MetodoPagamento cadastrarMetodoPagamento(MetodoPagamento metodoPagamento){
        String key = UUID.randomUUID().toString();
        MetodoPagamentoEvent event = new MetodoPagamentoEvent();

        MetodoPagamento metodoSalvo = metodoPagamentoRepository.save(metodoPagamento);

        event.setTipoModificacao("CRIACAO");
        event.setMetodoPagamento(metodoSalvo);

        kafkaTemplate.send("atualizacao-metodo-pagamento", key, event);
        return metodoSalvo;
    }

    public List<MetodoPagamento> cadastrarListaMetodoPagamento(List<MetodoPagamento> metodosPagamento){
        String key = UUID.randomUUID().toString();
        MetodoPagamentoEvent event = new MetodoPagamentoEvent();
        List<MetodoPagamento> metodosSalvos = metodoPagamentoRepository.saveAll(metodosPagamento);

        metodosSalvos.forEach(metodo -> {
            event.setTipoModificacao("CRIACAO");
            event.setMetodoPagamento(metodo);

            kafkaTemplate.send("atualizacao-metodo-pagamento", key, event);
        });

        return metodosSalvos;
    }

    public MetodoPagamento atualizarMetodoPagamento(Long id, MetodoPagamento metodoPagamento){
        MetodoPagamento metodo = metodoPagamentoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pagamento não encontrado")
        );

        metodo.setDescricao(metodoPagamento.getDescricao());
        MetodoPagamento metodoAtualizado = metodoPagamentoRepository.save(metodo);

        String key = UUID.randomUUID().toString();
        MetodoPagamentoEvent event = new MetodoPagamentoEvent();

        event.setTipoModificacao("ATUALIZACAO");
        event.setMetodoPagamento(metodoAtualizado);

        kafkaTemplate.send("atualizacao-metodo-pagamento", key, event);
        return metodoAtualizado;
    }

    public void deletarMetodoPagamento(Long id){
        MetodoPagamento metodo = metodoPagamentoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pagamento não encontrado")
        );
        metodo.setDeleted(true);
        metodoPagamentoRepository.save(metodo);

        String key = UUID.randomUUID().toString();
        MetodoPagamentoEvent event = new MetodoPagamentoEvent();

        event.setTipoModificacao("DELECAO");
        event.setMetodoPagamento(metodo);

        kafkaTemplate.send("atualizacao-metodo-pagamento", key, event);
    }
}
