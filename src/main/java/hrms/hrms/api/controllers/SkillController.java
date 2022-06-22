package hrms.hrms.api.controllers;

import hrms.hrms.business.abstracts.SkillService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<SkillDto>>> getAll() {
        DataResult<List<SkillDto>> skills = skillService.getAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/getBySkillId")
    public ResponseEntity<DataResult<SkillDto>> getBySkillId(@RequestParam int skillId) {
        DataResult<SkillDto> skills = skillService.getBySkillId(skillId);
        return ResponseEntity.ok(skills);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody SkillDto skillDto) {
        Result res = this.skillService.add(skillDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int skillId) {
        Result status = skillService.delete(skillId);
        return ResponseEntity.ok(status);

    }
}
