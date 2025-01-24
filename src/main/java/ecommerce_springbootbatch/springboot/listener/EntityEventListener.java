package ecommerce_springbootbatch.springboot.listener;

import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderElasticsearchRepository;
import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductElasticsearchRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityEventListener {

    private final ProductElasticsearchRepository productElasticsearchRepository;
    private final OrderElasticsearchRepository orderElasticsearchRepository;

    public EntityEventListener(ProductElasticsearchRepository productElasticsearchRepository,
                               OrderElasticsearchRepository orderElasticsearchRepository) {
        this.productElasticsearchRepository = productElasticsearchRepository;
        this.orderElasticsearchRepository = orderElasticsearchRepository;
    }

    // Listener para Product
    @EventListener
    public void onProductSaved(Product product) {
        ProductDocument productDocument = convertToProductDocument(product);
        productElasticsearchRepository.save(productDocument);
    }

    // Listener para Order
    @EventListener
    public void onOrderSaved(Order order) {
        OrderDocument orderDocument = convertToOrderDocument(order);
        orderElasticsearchRepository.save(orderDocument);
    }

    // Conversión de Product a ProductDocument
    private ProductDocument convertToProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId().longValue());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        return productDocument;
    }

    // Conversión de Order a OrderDocument
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

