package mg.s5stock.repository;

import mg.s5stock.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCodeLikeIgnoreCaseOrderByCodeAsc(String code);

    Optional<Product> findByCodeLikeIgnoreCase(String productCode);
}