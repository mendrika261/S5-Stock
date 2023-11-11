package mg.s5stock.controller;

import mg.s5stock.model.core.StockState;
import mg.s5stock.model.entity.Store;
import mg.s5stock.service.StockService;
import mg.s5stock.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/stockouts/create")
    public String createStockOut(Model model) {
        Store[] stores = storeService.getStores();

        model.addAttribute("stores", stores);
        model.addAttribute("error", model.asMap().get("error"));
        model.addAttribute("productCode", model.asMap().get("productCode"));
        model.addAttribute("storeId", model.asMap().get("storeId"));
        model.addAttribute("quantity", model.asMap().get("quantity"));
        model.addAttribute("date", model.asMap().get("date"));

        return "createStockOut";
    }

    @PostMapping("/stockouts/create")
    public String createStockOut(
            @RequestParam String productCode,
            @RequestParam long storeId,
            @RequestParam(defaultValue = "0.0") double quantity,
            @RequestParam LocalDateTime date,
            RedirectAttributes redirectAttributes) {
        try {
            stockService.createStockOut(productCode, storeId, quantity, date);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("productCode", productCode);
            redirectAttributes.addFlashAttribute("storeId", storeId);
            redirectAttributes.addFlashAttribute("quantity", quantity);
            redirectAttributes.addFlashAttribute("date", date);
        }
        return "redirect:/stockouts/create";
    }
}
