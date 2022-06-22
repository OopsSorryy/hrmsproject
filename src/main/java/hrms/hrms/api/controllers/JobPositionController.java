package hrms.hrms.api.controllers;


import hrms.hrms.business.abstracts.JobPositionService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;

import hrms.hrms.entities.dtos.JobPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobPosition")
public class JobPositionController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<JobPositionDto>>> getAll() {
        DataResult<List<JobPositionDto>> jobPositions = jobPositionService.getAll();
        return ResponseEntity.ok(jobPositions);
    }

    @GetMapping("/getByJobPositionId")
    public ResponseEntity<DataResult<JobPositionDto>> getByJobPositionId(@RequestParam int jobPositionId) {
        DataResult<JobPositionDto> jobPosition = jobPositionService.getByJobPositionId(jobPositionId);
        return ResponseEntity.ok(jobPosition);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobPositionDto jobPositionDto) {
        Result res = this.jobPositionService.add(jobPositionDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int jobPositionId, @Valid @RequestBody JobPositionDto jobPositionDto) {
        Result res = this.jobPositionService.update(jobPositionId,jobPositionDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int jobPositionId) {
        Result status = jobPositionService.delete(jobPositionId);
        return ResponseEntity.ok(status);

    }

}
