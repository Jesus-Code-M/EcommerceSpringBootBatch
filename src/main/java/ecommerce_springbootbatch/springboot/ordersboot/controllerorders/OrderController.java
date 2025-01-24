package ecommerce_springbootbatch.springboot.ordersboot.controllerorders;

import ecommerce_springbootbatch.springboot.ElasticsearchService;
import ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder.OrderDocument;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import ecommerce_springbootbatch.springboot.ordersboot.repositoryorders.OrderJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders") //La anotación @RequestMapping("/api/orders") a nivel de clase establece que todas las rutas manejadas por este controlador comenzarán con /api/orders.
public class OrderController {

    private final ElasticsearchService elasticsearchService;
    private final OrderJpaRepository orderJpaRepository;

    public OrderController(ElasticsearchService elasticsearchService, OrderJpaRepository orderJpaRepository) {
        this.elasticsearchService = elasticsearchService;
        this.orderJpaRepository = orderJpaRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderJpaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderJpaRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderJpaRepository.findById(id).map(order -> {
            order.setUserEmail(updatedOrder.getUserEmail());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            order.setStatus(updatedOrder.getStatus());
            order.setOrderDate(updatedOrder.getOrderDate());
            Order savedOrder = orderJpaRepository.save(order);
            return ResponseEntity.ok(savedOrder);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/elasticsearch")
    public ResponseEntity<String> saveOrderToElasticsearch(@RequestBody Order order) {
        OrderDocument orderDocument = elasticsearchService.convertToOrderDocument(order);
        elasticsearchService.saveOrder(orderDocument);
        return ResponseEntity.ok("Order saved to Elasticsearch.");
    }

}

