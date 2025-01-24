package ecommerce_springbootbatch.springbatch.productsbatch.listenersproducts;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("✅ Job completed successfully!");
            System.out.println("📅 Start Time: " + jobExecution.getStartTime());
            System.out.println("⏳ End Time: " + jobExecution.getEndTime());
            System.out.println("📊 Total Records Processed: " + jobExecution.getStepExecutions()
                    .stream()
                    .mapToLong(stepExecution -> stepExecution.getWriteCount())
                    .sum());
            System.out.println("❌ Errors: " + jobExecution.getAllFailureExceptions());
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            System.out.println("❌ Job failed with errors!");
            jobExecution.getAllFailureExceptions().forEach(Throwable::printStackTrace);
        }
    }
}
