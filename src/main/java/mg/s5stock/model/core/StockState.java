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

    public void setEndDate(LocalDateTime endDate) {
        if(getStartDate() == null)
            throw new IllegalArgumentException("La date de début doit être définie avant la date de fin");
        if(endDate.isBefore(getStartDate()))
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice < 0)
            throw new IllegalArgumentException("Le prix total doit être positif");
        this.totalPrice = totalPrice;
    }
}
