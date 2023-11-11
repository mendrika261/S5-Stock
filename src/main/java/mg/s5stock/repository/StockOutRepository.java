package mg.s5stock.repository;

import mg.s5stock.model.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StockOutRepository extends JpaRepository<StockOut, Long> {
    @Query("select sum(s.quantity) from StockOut s where s.product.id = ?1 and s.store.id = ?2 and s.date < ?3")
    Optional<Double> getQuantity(long productId, long storeId, LocalDateTime date);

    @Query("select sum(s.quantity * s.stockIn.unitPrice) from StockOut s where s.product.id = ?1 and s.store.id = ?2 and s.date < ?3")
    Optional<Double> getPrice(long productId, long storeId, LocalDateTime date);

}