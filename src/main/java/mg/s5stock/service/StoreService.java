package mg.s5stock.service;

import mg.s5stock.model.entity.Store;
import mg.s5stock.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store[] getStores() {
        return storeRepository.findAllByOrderByName().toArray(new Store[0]);
    }
}
