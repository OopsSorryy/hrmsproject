package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.ForeignLanguageService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.ForeignLanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/foreignLanguage")
public class ForeignLanguageController {

    private ForeignLanguageService foreignLanguageService;

    @Autowired
    public ForeignLanguageController(ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageService = foreignLanguageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<ForeignLanguageDto>>> getAll() {
        DataResult<List<ForeignLanguageDto>> foreignLanguages = foreignLanguageService.getAll();
        return ResponseEntity.ok(foreignLanguages);
    }

    @GetMapping("/getByForeignLanguageId")
    public ResponseEntity<DataResult<ForeignLanguageDto>> getByForeignLanguageId(@RequestParam int foreignLanguageId) {
        DataResult<ForeignLanguageDto> foreignLanguage = foreignLanguageService.getByForeignLanguageId(foreignLanguageId);
        return ResponseEntity.ok(foreignLanguage);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ForeignLanguageDto foreignLanguageDto) {
        Result res = this.foreignLanguageService.add(foreignLanguageDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int foreignLanguageId) {
        Result status = foreignLanguageService.delete(foreignLanguageId);
        return ResponseEntity.ok(status);

    }
}
