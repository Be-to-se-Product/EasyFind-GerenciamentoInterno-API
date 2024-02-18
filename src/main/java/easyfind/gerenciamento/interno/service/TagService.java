package easyfind.gerenciamento.interno.service;

import easyfind.gerenciamento.interno.model.Tag;
import easyfind.gerenciamento.interno.repository.TagRepository;
import easyfind.gerenciamento.interno.service.evento.TagEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repository;
    private final KafkaTemplate<String, TagEvent> kafkaTemplate;

    public Tag cadastrarTag(Tag tag){
        String key = UUID.randomUUID().toString();
        TagEvent event = new TagEvent();

        Tag tagSalva = repository.save(tag);

        event.setTipoModificacao("CRIACAO");
        event.setTag(tagSalva);

        kafkaTemplate.send("atualizacao-tag", key, event);
        return tagSalva;
    }

    public List<Tag> cadastrarListaTag(List<Tag> tags){
        List<Tag> tagsSalvas = repository.saveAll(tags);

        tagsSalvas.forEach(tag -> {
            String key = UUID.randomUUID().toString();
            TagEvent event = new TagEvent();

            event.setTipoModificacao("CRIACAO");
            event.setTag(tag);

            kafkaTemplate.send("atualizacao-tag", key, event);
        });

        return tagsSalvas;
    }

    public Tag atualizarTag(Long id, Tag tag){
        Tag tagEncontrada = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag não encontrada")
        );
        tagEncontrada.setDescricao(tag.getDescricao());
        Tag tagAtualizada = repository.save(tagEncontrada);

        String key = UUID.randomUUID().toString();
        TagEvent event = new TagEvent();

        event.setTipoModificacao("ATUALIZACAO");
        event.setTag(tagAtualizada);

        kafkaTemplate.send("atualizacao-tag", key, event);
        return tagAtualizada;
    }

    public void deletarTag(Long id){
        Tag tag = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag não encontrada")
        );
        tag.setDeleted(true);
        Tag tagSalva = repository.save(tag);

        String key = UUID.randomUUID().toString();
        TagEvent event = new TagEvent();

        event.setTipoModificacao("DELECAO");
        event.setTag(tagSalva);

        kafkaTemplate.send("atualizacao-tag", key, event);
    }
}
