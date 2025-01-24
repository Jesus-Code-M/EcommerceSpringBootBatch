package ecommerce_springbootbatch.springbatch.ordersbatch.readers;

import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderItemProcessor implements ItemProcessor<Order, Order> {

    @Override
    public Order process(Order order) throws Exception {
        // Lógica de negocio para actualizar el pedido
        order.setStatus("COMPLETED");
        return order;
    }
}