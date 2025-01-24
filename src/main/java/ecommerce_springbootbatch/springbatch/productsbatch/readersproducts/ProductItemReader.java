package ecommerce_springbootbatch.springbatch.productsbatch.readersproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductItemReader extends JpaPagingItemReader<Product> {

    @Autowired
    public ProductItemReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT p FROM Product p");
    }
}