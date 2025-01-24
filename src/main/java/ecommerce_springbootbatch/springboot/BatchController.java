package ecommerce_springbootbatch.springboot;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/batch")
class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("processOrdersJob") // Especificar el bean para este campo
    private Job processOrdersJob;

    @Autowired
    @Qualifier("processProductsJob") // Especificar el bean para este campo
    private Job processProductsJob;

    @PostMapping("orders")
    public ResponseEntity<String> runOrdersJob(@RequestParam(value = "jobParam", defaultValue = "default") String jobParam) {
        try {
            jobLauncher.run(processOrdersJob, new org.springframework.batch.core.JobParametersBuilder()
                    .addString("jobParam", jobParam)
                    .toJobParameters());
            return ResponseEntity.ok("Orders job executed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Orders job failed to execute!");
        }
    }

    @PostMapping("products")
    public ResponseEntity<String> runProductsJob(@RequestParam(value = "jobParam", defaultValue = "default") String jobParam) {
        try {
            jobLauncher.run(processProductsJob, new org.springframework.batch.core.JobParametersBuilder()
                    .addString("jobParam", jobParam)
                    .toJobParameters());
            return ResponseEntity.ok("Products job executed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Products job failed to execute!");
        }
    }
}

