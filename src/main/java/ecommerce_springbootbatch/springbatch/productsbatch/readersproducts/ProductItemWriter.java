package ecommerce_springbootbatch.springbatch.productsbatch.readersproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductJpaRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductItemWriter implements ItemWriter<Product> {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public void write(Chunk<? extends Product> products) throws Exception {
        productJpaRepository.saveAll(products);
        System.out.println("Saved " + products.size() + " products to the database.");
    }
}
