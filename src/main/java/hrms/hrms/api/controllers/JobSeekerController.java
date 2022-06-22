package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobSeekerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.JobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobSeeker")
public class JobSeekerController {

    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobSeekerDto>>> getAll() {
        DataResult<List<JobSeekerDto>> jobSeekers = jobSeekerService.getAll();
        return ResponseEntity.ok(jobSeekers);
    }

    @GetMapping("/getByJobSeekerId")
    public ResponseEntity<DataResult<JobSeekerDto>> getByJobSeekerId(@RequestParam int jobSeekerId) {
        DataResult<JobSeekerDto> jobSeeker = jobSeekerService.getByJobSeekerId(jobSeekerId);
        return ResponseEntity.ok(jobSeeker);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeekerDto jobSeekerDto) {
        Result res = this.jobSeekerService.add(jobSeekerDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int jobSeekerId, @Valid @RequestBody JobSeekerDto jobSeekerDto) {
        Result res = this.jobSeekerService.update(jobSeekerId,jobSeekerDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int jobSeekerId) {
        Result status = jobSeekerService.delete(jobSeekerId);
        return ResponseEntity.ok(status);

    }
}
