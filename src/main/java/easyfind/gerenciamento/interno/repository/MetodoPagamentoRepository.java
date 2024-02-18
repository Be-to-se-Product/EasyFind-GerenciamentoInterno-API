package easyfind.gerenciamento.interno.repository;

import easyfind.gerenciamento.interno.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long> {
    Optional<MetodoPagamento> findById(Long id);
}
