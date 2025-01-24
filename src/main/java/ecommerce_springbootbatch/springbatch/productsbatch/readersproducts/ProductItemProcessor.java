package ecommerce_springbootbatch.springbatch.productsbatch.readersproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product product) throws Exception {
        // Lógica de negocio para actualizar los productos
        product.setDescription(product.getDescription().toUpperCase());
        return product;
    }
}
