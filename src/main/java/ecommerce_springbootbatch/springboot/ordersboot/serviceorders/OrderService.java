package ecommerce_springbootbatch.springboot.ordersboot.serviceorders;

import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderJpaRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;
    private final Counter insertCounter;
    private final Counter updateCounter;
    private final Counter deleteCounter;

    @Autowired
    public OrderService(OrderJpaRepository orderJpaRepository, MeterRegistry meterRegistry) {
        this.orderJpaRepository = orderJpaRepository;

        // Configurar las métricas
        this.insertCounter = meterRegistry.counter("database_operations_total", "operation", "insert", "entity", "Order");
        this.updateCounter = meterRegistry.counter("database_operations_total", "operation", "update", "entity", "Order");
        this.deleteCounter = meterRegistry.counter("database_operations_total", "operation", "delete", "entity", "Order");
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            insertCounter.increment();
        } else {
            updateCounter.increment();
        }
        return orderJpaRepository.save(order);
    }

    public void deleteById(Long id) {
        deleteCounter.increment();
        orderJpaRepository.deleteById(id);
    }

    public void delete(Order order) {
        deleteCounter.increment();
        orderJpaRepository.delete(order);
    }
}


