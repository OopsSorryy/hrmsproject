package hrms.hrms.api.controllers;


import hrms.hrms.business.abstracts.CvService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.CvAddDto;
import hrms.hrms.entities.dtos.CvDto;
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
@RequestMapping("/api/cv")
public class CvController {

    private CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<CvDto>> cvs = cvService.getAll();
        return ResponseEntity.ok(cvs);
    }

    @GetMapping("/getByCvId")
    public ResponseEntity<?> getByCvId(@RequestParam int cvId) {
        DataResult<CvDto> cv = cvService.getByCvId(cvId);
        return ResponseEntity.ok(cv);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CvAddDto cvAddDto) {

      return ResponseEntity.ok(this.cvService.add(cvAddDto));

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int cvId) {
        Result status = cvService.delete(cvId);
        return ResponseEntity.ok(status);

    }

    @PutMapping("/{cvId}/jobSeekers/{jobSeekerId}")
    public ResponseEntity<?> addCvInJobSeeker(@PathVariable int cvId, @PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.cvService.addCvInJobSeeker(cvId,jobSeekerId));

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
