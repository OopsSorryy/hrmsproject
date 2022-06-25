package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.SkillService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.SkillDto;
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
@RequestMapping("/api/skill")
public class SkillController {

    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        DataResult<List<SkillDto>> skills = skillService.getAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/getBySkillId")
    public ResponseEntity<?> getBySkillId(@RequestParam int skillId) {
        DataResult<SkillDto> skills = skillService.getBySkillId(skillId);
        return ResponseEntity.ok(skills);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody SkillDto skillDto) {
        return ResponseEntity.ok(this.skillService.add(skillDto));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int skillId) {
        Result status = skillService.delete(skillId);
        return ResponseEntity.ok(status);

    }
    @PutMapping("/{cvId}/skills/{skillId}")
    public ResponseEntity<?> addSkillToCv(@PathVariable int cvId, @PathVariable int skillId){
         return ResponseEntity.ok(this.skillService.addSkillToCv(cvId,skillId));

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
