package ecommerce_springbootbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/*@EnableJpaRepositories inicilaiza los repositorios JPA
* Busca interfaces que extienden JpaRepository (u otros repositorios específicos de JPA) en los paquetes que especifiques en basePackages
* Sólo se necesita incluir los paquetes donde se encuentran tus repositorios JPA, porque es aquí donde Spring busca las interfaces relacionadas con la base de datos SQL*/
@EnableJpaRepositories(basePackages = {
		"ecommerce_springbootbatch.springboot.ordersboot.repositoryorders",
		"ecommerce_springbootbatch.springboot.productsboot.repositoryproducts"
})
/*@EntityScan le dice a Spring dónde buscar las clases anotadas con @Entity. Estas clases representan las tablas en tu base de datos.
* Busca clases con anotaciones JPA como @Entity, @Embeddable o @MappedSuperclass en los paquetes que especifiques.
* Si no se define los paquetes correctos, Spring no podrá detectar las entidades que tienen el código que necesita para interactuar con la base de datos*/
@EntityScan(basePackages = {
		"ecommerce_springbootbatch.springboot.ordersboot.entityorders",
		"ecommerce_springbootbatch.springboot.productsboot.entityproducts"
})
/*@ComponentScan le dice a Spring dónde buscar los componentes, servicios, controladores y otros beans anotados con @Component, @Service, @Controller o @RestController
* Escanea los paquetes especificados en basePackages para encontrar estas clases y registrarlas como beans en el contexto de Spring.*/
@ComponentScan(basePackages = {
		"ecommerce_springbootbatch.springbatch.productsbatch",
		"ecommerce_springbootbatch.springbatch.ordersbatch",
		"ecommerce_springbootbatch.springbatch.ordersbatch.readers",
		"ecommerce_springbootbatch.springboot.ordersboot.controllerorders",
		"ecommerce_springbootbatch.springboot.productsboot.controllerproducts",
		"ecommerce_springbootbatch.springboot.config",
		"ecommerce_springbootbatch.springboot.serviceproducts",
		"ecommerce_springbootbatch.springboot.serviceorders",
		"ecommerce_springbootbatch.springboot"
})
/*
@EnableElasticsearchRepositories esta anotación le indica a Spring dónde buscar repositorios de Elasticsearch*/
@EnableElasticsearchRepositories(basePackages = {
		"ecommerce_springbootbatch.springboot.ordersboot.elasticsearchorder",
		"ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct"
})
public class EcommerceSpringBootBatch {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceSpringBootBatch.class, args);
	}
}
