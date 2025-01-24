package ecommerce_springbootbatch.springboot.productsboot.repositoryproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}

