package mg.s5stock.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.s5stock.model.core.Stock;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "stock_out")
public class StockOut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "stock_in_id")
    private StockIn stockIn;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    public StockOut() {
    }

    public StockOut(Product product, Store store, StockIn stockIn, LocalDateTime date) {
        setProduct(product);
        setStore(store);
        setStockIn(stockIn);
        setDate(date);
    }

    public double getPrice() {
        return getQuantity() * getStockIn().getUnitPrice();
    }

    public void setQuantity(double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        this.quantity = quantity;
    }
}