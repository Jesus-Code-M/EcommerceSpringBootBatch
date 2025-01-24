package ecommerce_springbootbatch.springbatch.ordersbatch.readers;

import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderJpaRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemWriter implements ItemWriter<Order> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Override
    public void write(Chunk<? extends Order> orders) throws Exception {
        orderJpaRepository.saveAll(orders);
    }
}