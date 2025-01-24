package ecommerce_springbootbatch.springbatch.ordersbatch.readers;

import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;

@Component
public class OrderItemReader extends JpaPagingItemReader<Order> {

    @Autowired
    public OrderItemReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT o FROM Order o WHERE o.status = 'PENDING'");
    }
}
