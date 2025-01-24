package ecommerce_springbootbatch.springboot.ordersboot.repositoryorders;

import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderElasticsearchRepository extends ElasticsearchRepository<OrderDocument, String> {
}
