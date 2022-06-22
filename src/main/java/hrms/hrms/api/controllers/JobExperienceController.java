package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobExperienceService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobExperience")
public class JobExperienceController {

    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperienceController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobExperienceDto>>> getAll() {
        DataResult<List<JobExperienceDto>> jobExperiences = jobExperienceService.getAll();
        return ResponseEntity.ok(jobExperiences);
    }

    @GetMapping("/getByJobExperienceId")
    public ResponseEntity<DataResult<JobExperienceDto>> getByJobExperienceId(@RequestParam int jobExperienceId) {
        DataResult<JobExperienceDto> jobExperience = jobExperienceService.getByJobExperienceId(jobExperienceId);
        return ResponseEntity.ok(jobExperience);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobExperienceDto jobExperienceDto) {
        Result res = this.jobExperienceService.add(jobExperienceDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int jobExperienceId) {
        Result status = jobExperienceService.delete(jobExperienceId);
        return ResponseEntity.ok(status);

    }
}
