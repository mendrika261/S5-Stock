package mg.s5stock.repository;

import mg.s5stock.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCodeLikeIgnoreCaseOrderByCodeAsc(String code);
}