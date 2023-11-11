package mg.s5stock.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "stock_in")
public class StockIn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "initial_quantity", nullable = false)
    private double initialQuantity;

    @Column(name = "reminding_quantity", nullable = false)
    private double remindingQuantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    public double getRemindingPrice() {
        return getRemindingQuantity() * getUnitPrice();
    }

    public double getTotalPrice() {
        return getInitialQuantity() * getUnitPrice();
    }
}