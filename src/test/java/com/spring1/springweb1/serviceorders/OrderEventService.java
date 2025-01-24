package com.spring1.springweb1.serviceorders;

import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderElasticsearchRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventService {

    private final OrderElasticsearchRepository orderElasticsearchRepository;

    public OrderEventService(OrderElasticsearchRepository orderElasticsearchRepository) {
        this.orderElasticsearchRepository = orderElasticsearchRepository;
    }

    @EventListener
    public void onOrderSaved(Order order) {
        OrderDocument orderDocument = convertToOrderDocument(order);
        orderElasticsearchRepository.save(orderDocument);
    }

    private OrderDocument convertToOrderDocument(Order order) {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setId(order.getId().longValue());
        orderDocument.setUserEmail(order.getUserEmail());
        orderDocument.setTotalAmount(order.getTotalAmount());
        orderDocument.setStatus(order.getStatus());
        orderDocument.setOrderDate(order.getOrderDate());
        return orderDocument;
    }
}

