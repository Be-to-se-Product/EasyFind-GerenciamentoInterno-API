package easyfind.gerenciamento.interno;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	NewTopic tagAtualizada() {
		return TopicBuilder.name("atualizacao-tag").partitions(1).replicas(3).build();
	}

	@Bean
	NewTopic metodoPagamentoAtualizado() {
		return TopicBuilder.name("atualizacao-metodo-pagamento").partitions(1).replicas(3).build();
	}

}
