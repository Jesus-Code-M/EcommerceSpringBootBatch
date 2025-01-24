package ecommerce_springbootbatch.springboot.productsboot.entityproducts;

import jakarta.persistence.*;


/* @Entity marca la clase como una entidad JPA. Esto significa que Hibernate (u otro proveedor JPA) la tratará como una tabla en la base de datos.
Por sí sola, @Entity le indica a JPA que debe mapear esta clase a una tabla en la base de datos con el mismo nombre que la clase*/
@Entity
/*Proporciona configuración adicional sobre cómo se mapeará la clase a la tabla de la base de datos. Con @Table, puedes:
Especificar el nombre exacto de la tabla en la base de datos con name.
Definir esquemas de base de datos con schema.
Aplicar restricciones únicas o índices con uniqueConstraints e indexes*/
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

