package ecommerce_springbootbatch.springboot;

import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderElasticsearchRepository;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderJpaRepository;
import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductElasticsearchRepository;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EcommerceService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final OrderElasticsearchRepository orderElasticsearchRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EcommerceService(
            ProductJpaRepository productJpaRepository,
            ProductElasticsearchRepository productElasticsearchRepository,
            OrderJpaRepository orderJpaRepository,
            OrderElasticsearchRepository orderElasticsearchRepository,
            ApplicationEventPublisher eventPublisher) {
        this.productJpaRepository = productJpaRepository;
        this.productElasticsearchRepository = productElasticsearchRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.orderElasticsearchRepository = orderElasticsearchRepository;
        this.eventPublisher = eventPublisher;
    }

    // Ejemplo para guardar un producto
    public void saveProduct(Product product) {
        productJpaRepository.save(product);

        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId().longValue());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());

        productElasticsearchRepository.save(productDocument);
        eventPublisher.publishEvent(product);
    }

    // Ejemplo para guardar una orden
    public void saveOrder(Order order) {
        orderJpaRepository.save(order);

        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setId(order.getId().longValue());
        orderDocument.setOrderDate(order.getOrderDate());
        orderDocument.setStatus(order.getStatus());
        orderDocument.setTotalAmount(order.getTotalAmount());
        orderDocument.setUserEmail(order.getUserEmail());

        orderElasticsearchRepository.save(orderDocument);
        eventPublisher.publishEvent(order);
    }

}

