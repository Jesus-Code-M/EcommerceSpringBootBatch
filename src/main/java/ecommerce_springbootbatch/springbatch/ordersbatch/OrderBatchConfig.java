package ecommerce_springbootbatch.springbatch.ordersbatch;

import ecommerce_springbootbatch.springbatch.ordersbatch.readers.OrderItemProcessor;
import ecommerce_springbootbatch.springbatch.ordersbatch.readers.OrderItemReader;
import ecommerce_springbootbatch.springbatch.ordersbatch.readers.OrderItemWriter;
import ecommerce_springbootbatch.springboot.ordersboot.entityorders.Order;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class OrderBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final OrderItemReader orderItemReader;
    private final OrderItemProcessor orderItemProcessor;
    private final OrderItemWriter orderItemWriter;

    public OrderBatchConfig(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            OrderItemReader orderItemReader,
                            OrderItemProcessor orderItemProcessor,
                            OrderItemWriter orderItemWriter) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.orderItemReader = orderItemReader;
        this.orderItemProcessor = orderItemProcessor;
        this.orderItemWriter = orderItemWriter;
    }

    @Bean(name = "processOrdersJob")
    public Job processOrdersJob() {
        return new JobBuilder("processOrdersJob", jobRepository)
                .start(processOrdersStep())
                .build();
    }

    @Bean
    public Step processOrdersStep() {
        return new StepBuilder("processOrdersStep", jobRepository)
                .<Order, Order>chunk(10, transactionManager)
                .reader(orderItemReader)
                .processor(orderItemProcessor)
                .writer(orderItemWriter)
                .build();
    }
}
