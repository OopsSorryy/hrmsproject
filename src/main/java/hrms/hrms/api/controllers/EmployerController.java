package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.EmployerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<EmployerDto>>> getAll() {
        DataResult<List<EmployerDto>> employers = employerService.getAll();
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/getByEmployerId")
    public ResponseEntity<DataResult<EmployerDto>> getByEmployerId(@RequestParam int employerId) {
        DataResult<EmployerDto> employer = employerService.getByEmployerId(employerId);
        return ResponseEntity.ok(employer);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployerDto employerDto) {
        Result res = this.employerService.add(employerDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int employerId, @Valid @RequestBody EmployerDto employerDto) {
        Result res = this.employerService.update(employerId,employerDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int employerId) {
        Result status = employerService.delete(employerId);
        return ResponseEntity.ok(status);

    }

}
