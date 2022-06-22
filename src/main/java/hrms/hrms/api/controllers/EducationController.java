package hrms.hrms.api.controllers;
import hrms.hrms.business.abstracts.EducationService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.EducationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<EducationDto>>> getAll() {
        DataResult<List<EducationDto>> educations = educationService.getAll();
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/getByEducationId")
    public ResponseEntity<DataResult<EducationDto>> getByEducationId(@RequestParam int educationId) {
        DataResult<EducationDto> education = educationService.getByEducationId(educationId);
        return ResponseEntity.ok(education);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EducationDto educationDto) {
        Result res = this.educationService.add(educationDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int educationId) {
        Result status = educationService.delete(educationId);
        return ResponseEntity.ok(status);

    }
}
