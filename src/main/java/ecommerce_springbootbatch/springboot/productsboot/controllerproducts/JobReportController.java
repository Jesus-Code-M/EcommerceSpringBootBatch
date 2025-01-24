package ecommerce_springbootbatch.springboot.productsboot.controllerproducts;

import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobReportController {

    private final JobExplorer jobExplorer;

    public JobReportController(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    @GetMapping("api/batch/reports")
    public ResponseEntity<?> getJobReports() {
        return ResponseEntity.ok(jobExplorer.getJobNames());
    }
}
