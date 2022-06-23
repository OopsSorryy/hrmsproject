package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobSeekerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.JobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobSeeker")
public class JobSeekerController {

    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<JobSeekerDto>> jobSeekers = jobSeekerService.getAll();
        return ResponseEntity.ok(jobSeekers);
    }

    @GetMapping("/getByJobSeekerId")
    public ResponseEntity<?> getByJobSeekerId(@RequestParam int jobSeekerId) {
        DataResult<JobSeekerDto> jobSeeker = jobSeekerService.getByJobSeekerId(jobSeekerId);
        return ResponseEntity.ok(jobSeeker);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeekerDto jobSeekerDto) {

        return ResponseEntity.ok(this.jobSeekerService.add(jobSeekerDto));

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int jobSeekerId, @Valid @RequestBody JobSeekerDto jobSeekerDto) {
        return ResponseEntity.ok(this.jobSeekerService.update(jobSeekerId,jobSeekerDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int jobSeekerId) {
        Result status = jobSeekerService.delete(jobSeekerId);
        return ResponseEntity.ok(status);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }
}
