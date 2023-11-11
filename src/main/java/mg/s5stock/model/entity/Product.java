package mg.s5stock.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.s5stock.model.core.ProductType;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "productType", nullable = false)
    private String productType; // LIFO or FIFO
}