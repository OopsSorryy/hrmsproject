package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.EmployerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.EmployerDto;
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
@RequestMapping("/api/employer")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<EmployerDto>> employers = employerService.getAll();
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/getByEmployerId")
    public ResponseEntity<?> getByEmployerId(@RequestParam int employerId) {
        DataResult<EmployerDto> employer = employerService.getByEmployerId(employerId);
        return ResponseEntity.ok(employer);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployerDto employerDto) {
         return ResponseEntity.ok(this.employerService.add(employerDto));

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int employerId, @Valid @RequestBody EmployerDto employerDto) {
        return ResponseEntity.ok(this.employerService.update(employerId,employerDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int employerId) {
        Result status = employerService.delete(employerId);
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
