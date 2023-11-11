package mg.s5stock.model.core;

import lombok.Getter;
import lombok.Setter;
import mg.s5stock.model.entity.Product;
import mg.s5stock.model.entity.Store;

import java.time.LocalDateTime;

@Getter
@Setter
public class Stock {
    Product product;
    Store store;
    double initialQuantity;
    double remainingQuantity;
    double unitPrice;
    double totalPrice;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Stock(Product product, Store store, LocalDateTime startDate, LocalDateTime endDate) {
        setProduct(product);
        setStore(store);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public double getUnitPrice() {
        return getTotalPrice() / getRemainingQuantity();
    }

    public void setEndDate(LocalDateTime endDate) {
        if(getStartDate() == null)
            throw new IllegalArgumentException("La date de début doit être définie avant la date de fin");
        if(endDate.isBefore(getStartDate()))
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
    }

    public void setInitialQuantity(double initialQuantity) {
        if (initialQuantity < 0)
            throw new IllegalArgumentException("La quantité initiale doit être positive");
        this.initialQuantity = initialQuantity;
    }

    public void setRemainingQuantity(double remainingQuantity) {
        if (remainingQuantity < 0)
            throw new IllegalArgumentException("La quantité restante doit être positive");
        this.remainingQuantity = remainingQuantity;
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice < 0)
            throw new IllegalArgumentException("Le prix total doit être positif");
        this.totalPrice = totalPrice;
    }

    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0)
            throw new IllegalArgumentException("Le prix unitaire doit être positif");
        this.unitPrice = unitPrice;
    }
}
