package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobExperienceService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.JobExperienceDto;
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
@RequestMapping("/api/jobExperience")
public class JobExperienceController {

    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperienceController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<JobExperienceDto>> jobExperiences = jobExperienceService.getAll();
        return ResponseEntity.ok(jobExperiences);
    }

    @GetMapping("/getByJobExperienceId")
    public ResponseEntity<?> getByJobExperienceId(@RequestParam int jobExperienceId) {
        DataResult<JobExperienceDto> jobExperience = jobExperienceService.getByJobExperienceId(jobExperienceId);
        return ResponseEntity.ok(jobExperience);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobExperienceDto jobExperienceDto) {

        return ResponseEntity.ok(this.jobExperienceService.add(jobExperienceDto));

    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int jobExperienceId) {
        Result status = jobExperienceService.delete(jobExperienceId);
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
