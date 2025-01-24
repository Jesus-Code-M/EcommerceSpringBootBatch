package ecommerce_springbootbatch.springboot.productsboot.serviceproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductJpaRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final Counter insertCounter;
    private final Counter updateCounter;
    private final Counter deleteCounter;

    @Autowired
    public ProductService(ProductJpaRepository productJpaRepository, MeterRegistry meterRegistry) {
        this.productJpaRepository = productJpaRepository;

        // Inicializamos los contadores
        this.insertCounter = meterRegistry.counter("database_operations_total", "operation", "insert", "entity", "Product");
        this.updateCounter = meterRegistry.counter("database_operations_total", "operation", "update", "entity", "Product");
        this.deleteCounter = meterRegistry.counter("database_operations_total", "operation", "delete", "entity", "Product");
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            insertCounter.increment();
        } else {
            updateCounter.increment();
        }
        return productJpaRepository.save(product);
    }

    public void deleteById(Long id) {
        deleteCounter.increment();
        productJpaRepository.deleteById(id);
    }

    public void delete(Product product) {
        deleteCounter.increment();
        productJpaRepository.delete(product);
    }

    public Product findById(Long id) {
        return productJpaRepository.findById(id).orElse(null);
    }

    // Otros métodos CRUD pueden delegarse aquí si es necesario
}

