package hrms.hrms.api.controllers;
import hrms.hrms.business.abstracts.EducationService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.EducationDto;
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
@RequestMapping("/api/education")
public class EducationController {

    private EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<EducationDto>> educations = educationService.getAll();
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/getByEducationId")
    public ResponseEntity<?> getByEducationId(@RequestParam int educationId) {
        DataResult<EducationDto> education = educationService.getByEducationId(educationId);
        return ResponseEntity.ok(education);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EducationDto educationDto) {

        return ResponseEntity.ok(this.educationService.add(educationDto));

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int educationId) {
        Result status = educationService.delete(educationId);
        return ResponseEntity.ok(status);

    }
    @PutMapping("/{cvId}/educations/{educationId}")
    public ResponseEntity<?> addEducationToCv(@PathVariable int cvId, @PathVariable int educationId){
         return ResponseEntity.ok(this.educationService.addEducationToCv(cvId,educationId));

    }
    @GetMapping("/getAllDesc")
    public ResponseEntity<?>getAllSorted() {
        return ResponseEntity.ok(this.educationService.getAllSorted());
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
