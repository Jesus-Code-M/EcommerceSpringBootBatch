package ecommerce_springbootbatch.springbatch.productsbatch.readersproducts;

import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ProductCsvItemReader {

    @Bean(name = "productCsvReader")
    public FlatFileItemReader<Product> productReader() { //Lee y analiza archivos planos.
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data/products.csv"));//Ruta para ir a leer el archivo
        reader.setLinesToSkip(1); // Configurar el lector para ignorar la primera línea (header)
        reader.setLineMapper(new DefaultLineMapper<Product>() {{ //Convierte a Stringen el Object archivo o viceversa
            setLineTokenizer(new DelimitedLineTokenizer(";") {{
                setNames("id", "description",  "name",  "price");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Product.class);
            }});
        }});
        return reader;
    }
}
