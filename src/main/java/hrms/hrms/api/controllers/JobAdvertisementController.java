package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobAdvertisementService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.dtos.JobAdvertisementDto;
import hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobAdvertisement")
public class JobAdvertisementController {

    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobAdvertisementDto>>> getAll() {
        DataResult<List<JobAdvertisementDto>> jobAdvertisement = jobAdvertisementService.getAll();
        return ResponseEntity.ok(jobAdvertisement);
    }

    @GetMapping("/getJobAdvertisementWithEmployerName")
    public DataResult<List<JobAdvertisementWithEmployerDto>> getJobAdvertisementWithEmployerName(){
        return this.jobAdvertisementService.getJobAdvertisementWithEmployerName();
    }

    @GetMapping("/getByJobId")
    public ResponseEntity<DataResult<JobAdvertisementDto>> getByJobId(@RequestParam int jobAdvertisementId) {
        DataResult<JobAdvertisementDto> jobAdvertisement = jobAdvertisementService.getByJobId(jobAdvertisementId);
        return ResponseEntity.ok(jobAdvertisement);
    }
    @PutMapping("/{employerId}/employers/{jobId}")
    public ResponseEntity<?> addJobAdvertisementInEmployer(@PathVariable int employerId, @PathVariable int jobId){
        Result res = this.jobAdvertisementService.addJobAdvertisementInEmployer(employerId,jobId);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisementDto jobAdvertisementDto) {
        Result res = this.jobAdvertisementService.add(jobAdvertisementDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int jobAdvertisementId) {
        Result status = jobAdvertisementService.delete(jobAdvertisementId);
        return ResponseEntity.ok(status);

    }

    @GetMapping("/getAllSorted")
    public ResponseEntity<DataResult<List<JobAdvertisement>>> getAllSorted() {
        DataResult<List<JobAdvertisement>> jobAdvertisement = jobAdvertisementService.getAllSorted();
        return ResponseEntity.ok(jobAdvertisement);
    }
}