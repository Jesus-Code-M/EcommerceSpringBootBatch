package ecommerce_springbootbatch.springboot.ordersboot.repositoryorders;

import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
