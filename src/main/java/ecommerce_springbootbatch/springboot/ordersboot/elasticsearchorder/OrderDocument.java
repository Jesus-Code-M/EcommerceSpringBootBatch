package ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


/*Esta entidad está diseñada para ser utilizadas con Elasticsearch, donde los datos se almacenan como documentos en un índice, no en una tabla*/
@Document(indexName = "orders")
public class OrderDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private Double totalAmount;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date orderDate;


    //Getters y Setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
