package mg.s5stock.controller;

import mg.s5stock.model.core.Stock;
import mg.s5stock.model.core.StockState;
import mg.s5stock.model.entity.Store;
import mg.s5stock.service.StockService;
import mg.s5stock.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class StockController {
    private final StockService stockService;
    private final StoreService storeService;

    public StockController(StockService stockService, StoreService storeService) {
        this.stockService = stockService;
        this.storeService = storeService;
    }

    @GetMapping("/stocks")
    public String getStock(Model model,
                           @RequestParam(required = false) String productCode,
                           @RequestParam(required = false) LocalDateTime startDate,
                           @RequestParam(required = false) LocalDateTime endDate) {
        Store[] stores = storeService.getStores();

        StockState stockState = null;
        if(startDate != null && endDate != null)
            stockState = stockService.getStockState(Objects.requireNonNullElse(productCode, "%"), startDate, endDate);

        model.addAttribute("stockState", stockState);
        model.addAttribute("stores", stores);

        return "stockState";
    }
}
