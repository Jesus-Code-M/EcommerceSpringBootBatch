package ecommerce_springbootbatch.springboot.productsboot.serviceproducts;

import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductElasticsearchRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventService {

    private final ProductElasticsearchRepository productElasticsearchRepository;

    public ProductEventService(ProductElasticsearchRepository productElasticsearchRepository) {
        this.productElasticsearchRepository = productElasticsearchRepository;
    }

    @EventListener
    public void onProductSaved(Product product) {
        ProductDocument productDocument = convertToProductDocument(product);
        productElasticsearchRepository.save(productDocument);
    }

    private ProductDocument convertToProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId().longValue());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        return productDocument;
    }
}

