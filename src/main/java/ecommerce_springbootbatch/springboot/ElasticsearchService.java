package ecommerce_springbootbatch.springboot;

import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderElasticsearchRepository;
import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductElasticsearchRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchService {
/*
    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void saveOrder(Order order) {
        elasticsearchOperations.save(order);
    }*/
private final ProductElasticsearchRepository productElasticsearchRepository;
    private final OrderElasticsearchRepository orderElasticsearchRepository;

    public ElasticsearchService(ProductElasticsearchRepository productElasticsearchRepository,
                                OrderElasticsearchRepository orderElasticsearchRepository) {
        this.productElasticsearchRepository = productElasticsearchRepository;
        this.orderElasticsearchRepository = orderElasticsearchRepository;
    }

    public void saveProduct(ProductDocument productDocument) {
        productElasticsearchRepository.save(productDocument);
    }

    public void saveOrder(OrderDocument orderDocument) {
        orderElasticsearchRepository.save(orderDocument);
    }

    public ProductDocument convertToProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId().longValue());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        return productDocument;
    }

    public OrderDocument convertToOrderDocument(Order order) {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setId(order.getId().longValue());
        orderDocument.setOrderDate(order.getOrderDate());
        orderDocument.setStatus(order.getStatus());
        orderDocument.setTotalAmount(order.getTotalAmount());
        orderDocument.setUserEmail(order.getUserEmail());
        return orderDocument;
    }
}
