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

    public void setInitialQuantity(double initialQuantity) {
        if (initialQuantity < 0) {
            throw new IllegalArgumentException("La quantité initiale ne peut pas être négative");
        }
        this.initialQuantity = initialQuantity;
    }

    public void setRemindingQuantity(double remindingQuantity) {
        if (remindingQuantity < 0) {
            throw new IllegalArgumentException("La quantité restante ne peut pas être négative");
        }
        this.remindingQuantity = remindingQuantity;
    }

    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Le prix unitaire ne peut pas être négatif");
        }
        this.unitPrice = unitPrice;
    }
}