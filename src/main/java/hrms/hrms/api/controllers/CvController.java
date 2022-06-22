package hrms.hrms.api.controllers;


import hrms.hrms.business.abstracts.CvService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.CvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    private CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<CvDto>>> getAll() {
        DataResult<List<CvDto>> cvs = cvService.getAll();
        return ResponseEntity.ok(cvs);
    }

    @GetMapping("/getByCvId")
    public ResponseEntity<DataResult<CvDto>> getByCvId(@RequestParam int cvId) {
        DataResult<CvDto> cv = cvService.getByCvId(cvId);
        return ResponseEntity.ok(cv);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CvDto cvDto) {
        Result res = this.cvService.add(cvDto);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam int cvId) {
        Result status = cvService.delete(cvId);
        return ResponseEntity.ok(status);

    }

    @PutMapping("/{cvId}/jobSeekers/{jobSeekerId}")
    public ResponseEntity<?> addCvInJobSeeker(@PathVariable int cvId, @PathVariable int jobSeekerId){
        Result res = this.cvService.addCvInJobSeeker(cvId,jobSeekerId);
        if (res.isSuccess()) return ResponseEntity.status(HttpStatus.CREATED).body(res);
        return ResponseEntity.badRequest().body(res);
    }
}
