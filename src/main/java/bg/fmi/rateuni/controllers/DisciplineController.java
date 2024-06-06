package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.services.business.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<List<DisciplineResponse>> getAllFaculties() {
        return ResponseEntity.ok(disciplineService.getAllDisciplines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineResponse> getUniversityById(@PathVariable UUID id) {
        return ResponseEntity.ok(disciplineService.getDisciplineById(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createDiscipline(@RequestBody CreateDisciplineRequest createDisciplineRequest) {
        disciplineService.createUpdateDiscipline(createDisciplineRequest);
        return ResponseEntity.ok(new BaseResponse("Discipline created successfully"));
    }

}
