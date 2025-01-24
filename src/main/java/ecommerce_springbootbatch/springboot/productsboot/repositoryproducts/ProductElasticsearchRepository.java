package ecommerce_springbootbatch.springboot.productsboot.repositoryproducts;

import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDocument, String> {
}
