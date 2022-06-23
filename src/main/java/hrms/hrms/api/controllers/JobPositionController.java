package hrms.hrms.api.controllers;


import hrms.hrms.business.abstracts.JobPositionService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;

import hrms.hrms.entities.dtos.JobPositionDto;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobPosition")
public class JobPositionController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<JobPositionDto>> jobPositions = jobPositionService.getAll();
        return ResponseEntity.ok(jobPositions);
    }

    @GetMapping("/getByJobPositionId")
    public ResponseEntity<?> getByJobPositionId(@RequestParam int jobPositionId) {
        DataResult<JobPositionDto> jobPosition = jobPositionService.getByJobPositionId(jobPositionId);
        return ResponseEntity.ok(jobPosition);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobPositionDto jobPositionDto) {
        return ResponseEntity.ok(this.jobPositionService.add(jobPositionDto));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int jobPositionId, @Valid @RequestBody JobPositionDto jobPositionDto) {

        return ResponseEntity.ok(this.jobPositionService.update(jobPositionId,jobPositionDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int jobPositionId) {
        Result status = jobPositionService.delete(jobPositionId);
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
