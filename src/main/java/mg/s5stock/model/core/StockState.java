package mg.s5stock.model.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StockState {
    LocalDateTime startDate;
    LocalDateTime endDate;
    List<Stock> stock = new ArrayList<>();
    double totalPrice;

    public StockState(LocalDateTime startDate, LocalDateTime endDate, List<Stock> stock) {
        setStartDate(startDate);
        setEndDate(endDate);
        setStock(stock);
    }

    public StockState(LocalDateTime startDate, LocalDateTime endDate) {
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for(Stock stock : getStock()) {
            totalPrice += stock.getTotalPrice();
        }
        return totalPrice;
    }
}
