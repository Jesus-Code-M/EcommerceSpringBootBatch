package ecommerce_springbootbatch.springbatch.productsbatch;

import ecommerce_springbootbatch.springbatch.productsbatch.listenersproducts.JobCompletionNotificationListener;
import ecommerce_springbootbatch.springbatch.productsbatch.readersproducts.ProductCsvItemReader;
import ecommerce_springbootbatch.springbatch.productsbatch.readersproducts.ProductItemProcessor;
import ecommerce_springbootbatch.springbatch.productsbatch.readersproducts.ProductItemReader;
import ecommerce_springbootbatch.springbatch.productsbatch.readersproducts.ProductItemWriter;
import ecommerce_springbootbatch.springboot.productsboot.entityproducts.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class ProductBatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final ProductItemReader productItemReader;
    private final ProductItemProcessor productItemProcessor;
    private final ProductItemWriter productItemWriter;
    private final ProductCsvItemReader productCsvItemReader;
    private final JobRepository jobRepository;

    public ProductBatchConfig(JobRepository jobRepository,
                              PlatformTransactionManager transactionManager,
                              ProductItemReader productItemReader,
                              ProductItemProcessor productItemProcessor,
                              ProductItemWriter productItemWriter,
                              ProductCsvItemReader productCsvItemReader) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.productItemReader = productItemReader;
        this.productItemProcessor = productItemProcessor;
        this.productItemWriter = productItemWriter;
        this.productCsvItemReader = productCsvItemReader;
    }

    @Bean
    public Job processProductsJob() {
        return new JobBuilder("processProductsJob", jobRepository)
                .start(processProductsStep())
                .build();
    }

    @Bean
    public Step processProductsStep() {
        return new StepBuilder("processProductsStep", jobRepository)
                .<Product, Product>chunk(10, transactionManager) //chunk(10, transactionManager) asegura la transacción para el proceso de lectura, procesamiento y escritura.
                .reader(productItemReader)
                .processor(productItemProcessor)
                .writer(productItemWriter)
                .build();
    }

    @Bean
    public Job importProductsJob(JobCompletionNotificationListener listener) {
        return new JobBuilder("importProductsJob", jobRepository)
                .listener(listener)
                .start(importProductsStep())
                .build();
    }

    @Bean
    public Step importProductsStep() {
        return new StepBuilder("importProductsStep", jobRepository)
                .<Product, Product>chunk(10, transactionManager)
                .reader(productCsvItemReader.productReader())
                .writer(productItemWriter)
                .build();
    }
}

