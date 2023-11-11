package mg.s5stock.repository;

import mg.s5stock.model.entity.Product;
import mg.s5stock.model.entity.StockIn;
import mg.s5stock.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Long>,
        CrudRepository<StockIn, Long> {
    @Query("select sum(s.initialQuantity) from StockIn s where s.product.id = ?1 and s.store.id = ?2 and s.date < ?3")
    Optional<Double> getQuantity(long productId, long storeId, LocalDateTime date);

    @Query("select sum(s.initialQuantity * s.unitPrice) from StockIn s where s.product.id = ?1 and s.store.id = ?2 and s.date < ?3")
    Optional<Double> getPrice(long productId, long storeId, LocalDateTime date);

    List<StockIn> findByProductAndStoreAndRemindingQuantityNotOrderByDateAsc(Product product, Store store, double remindingQuantity);

    List<StockIn> findByProductAndStoreAndRemindingQuantityNotOrderByDateDesc(Product product, Store store, double remindingQuantity);
}