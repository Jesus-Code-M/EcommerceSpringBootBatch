package ecommerce_springbootbatch.springboot.productsboot.controllerproducts;

import ecommerce_springbootbatch.springboot.productsboot.elasticsearchproduct.ProductDocument;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductElasticsearchRepository;
import ecommerce_springbootbatch.springboot.productsboot.repositoryproducts.ProductJpaRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    private final ProductJpaRepository productJpaRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;
    private final JobLauncher jobLauncher;
    @Qualifier("processProductsJob")
    private final Job processProductsJob;

    public ProductoController(ProductJpaRepository productJpaRepository,
                              ProductElasticsearchRepository productElasticsearchRepository,
                              JobLauncher jobLauncher,
                              @Qualifier("processProductsJob") Job processProductsJob) {
        this.productJpaRepository = productJpaRepository;
        this.productElasticsearchRepository = productElasticsearchRepository;
        this.jobLauncher = jobLauncher;
        this.processProductsJob = processProductsJob;
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public List<Product> getAllProducts() {
        return productJpaRepository.findAll();
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productJpaRepository.save(product);

        // Guardar también en Elasticsearch
        ProductDocument productDocument = convertToProductDocument(savedProduct);
        productElasticsearchRepository.save(productDocument);

        return ResponseEntity.ok(savedProduct);
    }

    // Endpoint para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productJpaRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            Product savedProduct = productJpaRepository.save(product);

            // Actualizar también en Elasticsearch
            ProductDocument productDocument = convertToProductDocument(savedProduct);
            productElasticsearchRepository.save(productDocument);

            return ResponseEntity.ok(savedProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // Endpoint para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productJpaRepository.deleteById(id);

        // Eliminar también de Elasticsearch
        productElasticsearchRepository.deleteById(String.valueOf(id));

        return ResponseEntity.noContent().build();
    }

    // Endpoint para ejecutar el batch
    @PostMapping("/batch")
    public ResponseEntity<String> runProductsBatch() {
        try {
            JobExecution jobExecution = jobLauncher.run(processProductsJob, new JobParameters());
            return ResponseEntity.ok("Products job executed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Products job failed to execute!");
        }
    }

    // Helper para convertir Product a ProductDocument
    private ProductDocument convertToProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId().longValue());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());
        return productDocument;
    }
}

