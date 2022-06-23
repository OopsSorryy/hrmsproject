package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.JobAdvertisementService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.dtos.JobAdvertisementDto;
import hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto;
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
@RequestMapping("/api/jobAdvertisement")
public class JobAdvertisementController {

    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<JobAdvertisementDto>> jobAdvertisement = jobAdvertisementService.getAll();
        return ResponseEntity.ok(jobAdvertisement);
    }

    @GetMapping("/getJobAdvertisementWithEmployerName")
    public DataResult<?> getJobAdvertisementWithEmployerName(){
        return this.jobAdvertisementService.getJobAdvertisementWithEmployerName();
    }

    @GetMapping("/getByJobId")
    public ResponseEntity<?> getByJobId(@RequestParam int jobAdvertisementId) {
        DataResult<JobAdvertisementDto> jobAdvertisement = jobAdvertisementService.getByJobId(jobAdvertisementId);
        return ResponseEntity.ok(jobAdvertisement);
    }
    @PutMapping("/{employerId}/employers/{jobId}")
    public ResponseEntity<?> addJobAdvertisementInEmployer(@PathVariable int employerId, @PathVariable int jobId){

        return ResponseEntity.ok(this.jobAdvertisementService.addJobAdvertisementInEmployer(employerId,jobId));

    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisementDto jobAdvertisementDto) {

       return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisementDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int jobAdvertisementId) {
        Result status = jobAdvertisementService.delete(jobAdvertisementId);
        return ResponseEntity.ok(status);

    }

    @GetMapping("/getAllSorted")
    public ResponseEntity<?> getAllSorted() {
        DataResult<List<JobAdvertisement>> jobAdvertisement = jobAdvertisementService.getAllSorted();
        return ResponseEntity.ok(jobAdvertisement);
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