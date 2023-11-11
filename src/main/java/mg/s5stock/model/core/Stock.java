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
}
