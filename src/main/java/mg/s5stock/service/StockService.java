package mg.s5stock.service;

import lombok.Getter;
import lombok.Setter;
import mg.s5stock.model.core.Stock;
import mg.s5stock.model.core.StockState;
import mg.s5stock.model.entity.Product;
import mg.s5stock.model.entity.Store;
import mg.s5stock.repository.ProductRepository;
import mg.s5stock.repository.StockInRepository;
import mg.s5stock.repository.StockOutRepository;
import mg.s5stock.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class StockService {

    private final StockInRepository stockInRepository;
    private final StockOutRepository stockOutRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public StockService(StockInRepository stockInRepository,
                        StockOutRepository stockOutRepository,
                        ProductRepository productRepository,
                        StoreRepository storeRepository) {
        this.stockInRepository = stockInRepository;
        this.stockOutRepository = stockOutRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    // Quantity
    public double getQuantity(long productId, long storeId, LocalDateTime date) {
        double inQuantity = stockInRepository.getQuantity(productId, storeId, date).orElse(0.0);
        double outQuantity = stockOutRepository.getQuantity(productId, storeId, date).orElse(0.0);
        return inQuantity - outQuantity;
    }

    // Price
    public double getPrice(long productId, long storeId, LocalDateTime date) {
        double inPrice = stockInRepository.getPrice(productId, storeId, date).orElse(0.0);
        double outPrice = stockOutRepository.getPrice(productId, storeId, date).orElse(0.0);
        return inPrice - outPrice;
    }

    // Stock
    public Stock getStock(long productId, long storeId, LocalDateTime startDate, LocalDateTime endDate) {
        Product product = productRepository.findById(productId).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Stock stock = new Stock(product, store, startDate, endDate);
        stock.setInitialQuantity(getQuantity(productId, storeId, startDate));
        stock.setRemainingQuantity(getQuantity(productId, storeId, endDate));
        stock.setTotalPrice(getPrice(productId, storeId, endDate));

        return stock;
    }

    public List<Stock> getStocks(String productCode, LocalDateTime startDate, LocalDateTime endDate) {
        List<Product> products = productRepository.findByCodeLikeIgnoreCaseOrderByCodeAsc(productCode);
        List<Store> stores = storeRepository.findAll();

        List<Stock> stocks = new ArrayList<>();

        for (Product product : products) {
            for (Store store : stores) {
                Stock stock = getStock(product.getId(), store.getId(), startDate, endDate);
                if(stock.getTotalPrice() > 0)
                    stocks.add(stock);
            }
        }

        return stocks;
    }

    // Stock state

    public StockState getStockState(String productCode, LocalDateTime startDate, LocalDateTime endDate) {
        List<Stock> stocks = getStocks(productCode, startDate, endDate);
        return new StockState(startDate, endDate, stocks);
    }
}
