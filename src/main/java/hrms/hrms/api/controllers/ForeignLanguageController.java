package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.ForeignLanguageService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.ForeignLanguageDto;
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
@RequestMapping("/api/foreignLanguage")
public class ForeignLanguageController {

    private ForeignLanguageService foreignLanguageService;

    @Autowired
    public ForeignLanguageController(ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageService = foreignLanguageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<ForeignLanguageDto>> foreignLanguages = foreignLanguageService.getAll();
        return ResponseEntity.ok(foreignLanguages);
    }

    @GetMapping("/getByForeignLanguageId")
    public ResponseEntity<?> getByForeignLanguageId(@RequestParam int foreignLanguageId) {
        DataResult<ForeignLanguageDto> foreignLanguage = foreignLanguageService.getByForeignLanguageId(foreignLanguageId);
        return ResponseEntity.ok(foreignLanguage);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ForeignLanguageDto foreignLanguageDto) {
         return ResponseEntity.ok(this.foreignLanguageService.add(foreignLanguageDto));

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int foreignLanguageId) {
        Result status = foreignLanguageService.delete(foreignLanguageId);
        return ResponseEntity.ok(status);

    }

    @PutMapping("/{cvId}/foreignLanguages/{foreignLanguageId}")
    public ResponseEntity<?> addForeignLanguageToCv(@PathVariable int cvId, @PathVariable int foreignLanguageId){
        return ResponseEntity.ok(this.foreignLanguageService.addForeignLanguageToCv(cvId,foreignLanguageId));

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
